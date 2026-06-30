package com.boot.security.server.controller;

import com.boot.security.server.annotation.LogAnnotation;
import com.boot.security.server.dao.EDI210ChargeMapper;
import com.boot.security.server.dao.EDI210ImportLogMapper;
import com.boot.security.server.dao.EDI210InvoiceMapper;
import com.boot.security.server.dao.EDI210PartyMapper;
import com.boot.security.server.dao.EDI210TaxMapper;
import com.boot.security.server.dto.EDI210ExcelRowDTO;
import com.boot.security.server.dto.EDI210InvoiceDetailDTO;
import com.boot.security.server.dto.EDI210ListDTO;
import com.boot.security.server.model.EDI210Charge;
import com.boot.security.server.model.EDI210ImportLog;
import com.boot.security.server.model.EDI210Invoice;
import com.boot.security.server.model.EDI210Party;
import com.boot.security.server.model.EDI210Schema;
import com.boot.security.server.model.EDI210Tax;
import com.boot.security.server.model.FileInfo;
import com.boot.security.server.page.table.PageTableHandler;
import com.boot.security.server.page.table.PageTableHandler.CountHandler;
import com.boot.security.server.page.table.PageTableHandler.ListHandler;
import com.boot.security.server.page.table.PageTableRequest;
import com.boot.security.server.page.table.PageTableResponse;
import com.boot.security.server.utils.DateUtil;
import com.boot.security.server.utils.ExcelUtil;
import com.boot.security.server.utils.FileUtil;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EDI 210 发票模块（AMR/APAC/EMEIA 三 Region 共用一份模板）
 */
@Api(tags = "EDI 210 发票")
@RestController
@RequestMapping("/edi210")
public class EDI210Controller {

    @Autowired
    private EDI210InvoiceMapper invoiceMapper;
    @Autowired
    private EDI210PartyMapper partyMapper;
    @Autowired
    private EDI210ChargeMapper chargeMapper;
    @Autowired
    private EDI210TaxMapper taxMapper;
    @Autowired
    private EDI210ImportLogMapper importLogMapper;

    // ============================== 列表 ==============================

    @GetMapping("list")
    @ApiOperation(value = "EDI210 发票列表（DataTables 服务端分页）")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return invoiceMapper.count(request.getParams());
            }
        }, new ListHandler() {
            @Override
            public List<EDI210ListDTO> list(PageTableRequest request) {
                return invoiceMapper.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    // ============================== 详情 ==============================

    @GetMapping("detail/{id}")
    @ApiOperation(value = "发票详情（含 4 个 Party、N 条 Charge、Tax）")
    public EDI210InvoiceDetailDTO detail(@PathVariable("id") Long id) {
        EDI210InvoiceDetailDTO dto = new EDI210InvoiceDetailDTO();
        dto.setInvoice(invoiceMapper.selectByPrimaryKey(id));
        if (dto.getInvoice() == null) {
            return dto;
        }
        List<EDI210Party> parties = partyMapper.selectByInvoiceId(id);
        for (EDI210Party p : parties) {
            if (EDI210Party.ROLE_BILL_TO.equals(p.getPartyRole())) dto.setBillTo(p);
            else if (EDI210Party.ROLE_BILL_FROM.equals(p.getPartyRole())) dto.setBillFrom(p);
            else if (EDI210Party.ROLE_SHIPPER.equals(p.getPartyRole())) dto.setShipper(p);
            else if (EDI210Party.ROLE_CONSIGNEE.equals(p.getPartyRole())) dto.setConsignee(p);
        }
        dto.setCharges(chargeMapper.selectByInvoiceId(id));
        dto.setTax(taxMapper.selectByInvoiceId(id));
        return dto;
    }

    // ============================== 修改 ==============================

    @LogAnnotation
    @PutMapping
    @ApiOperation(value = "修改发票主表字段")
    public void update(@RequestBody EDI210Invoice invoice) {
        invoiceMapper.updateByPrimaryKeySelective(invoice);
    }

    // ============================== 删除（级联） ==============================

    @LogAnnotation
    @DeleteMapping("/{id}")
    @ApiOperation(value = "级联删除发票")
    @Transactional
    public void delete(@PathVariable("id") Long id) {
        cascadeDelete(id);
    }

    private void cascadeDelete(Long invoiceId) {
        chargeMapper.deleteByInvoiceId(invoiceId);
        partyMapper.deleteByInvoiceId(invoiceId);
        taxMapper.deleteByInvoiceId(invoiceId);
        invoiceMapper.deleteByPrimaryKey(invoiceId);
    }

    // ============================== 导入 ==============================

    @LogAnnotation
    @PostMapping("import")
    @ApiOperation(value = "导入 EDI210 Excel（含 Region 列）")
    @Transactional
    public FileInfo importExcel(MultipartFile file) throws IOException {
        checkFileName(file);
        String batchNo = "EDI210_" + DateUtil.format(new Date(), "yyyyMMddHHmmss");
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        int rows = sheet.getPhysicalNumberOfRows();

        Map<String, Long> invoiceCache = new HashMap<>();
        int total = 0, success = 0, fail = 0;
        StringBuilder failMsg = new StringBuilder();

        // 第 0、1 行为分组表头和列表头，从第 2 行开始读数据
        for (int r = 2; r < rows; r++) {
            XSSFRow row = sheet.getRow(r);
            if (row == null) continue;
            total++;
            try {
                String region = ExcelUtil.getCellValue(row.getCell(EDI210Schema.COL_REGION));
                String scac   = ExcelUtil.getCellValue(row.getCell(EDI210Schema.COL_SCAC));
                String invNo  = ExcelUtil.getCellValue(row.getCell(EDI210Schema.COL_INV_NO));

                if (StringUtils.isBlank(region) || StringUtils.isBlank(scac) || StringUtils.isBlank(invNo)) {
                    throw new IllegalArgumentException("Region/SCAC/INV# 不能为空");
                }

                String key = region + "|" + scac + "|" + invNo;
                Long invoiceId = invoiceCache.get(key);

                if (invoiceId == null) {
                    // 检查 DB 是否已存在 → 删旧插新
                    Long existing = invoiceMapper.selectIdByUk(region, scac, invNo);
                    if (existing != null) {
                        cascadeDelete(existing);
                    }
                    EDI210Invoice inv = parseInvoice(row, region, batchNo);
                    invoiceMapper.insert(inv);
                    invoiceId = inv.getId();

                    // 4 条 Party
                    List<EDI210Party> parties = Arrays.asList(
                            parseParty(row, invoiceId, EDI210Party.ROLE_BILL_TO),
                            parseParty(row, invoiceId, EDI210Party.ROLE_BILL_FROM),
                            parseParty(row, invoiceId, EDI210Party.ROLE_SHIPPER),
                            parseParty(row, invoiceId, EDI210Party.ROLE_CONSIGNEE)
                    );
                    partyMapper.insertBatch(parties);

                    // 1 条 Tax
                    taxMapper.insert(parseTax(row, invoiceId));

                    invoiceCache.put(key, invoiceId);
                }

                // 每行都写一条 Charge
                chargeMapper.insert(parseCharge(row, invoiceId, r));
                success++;
            } catch (Exception e) {
                fail++;
                failMsg.append("Row ").append(r + 1).append(": ").append(e.getMessage()).append(";\n");
                e.printStackTrace();
            }
        }

        // 记录批次日志
        EDI210ImportLog log = new EDI210ImportLog();
        log.setBatchNo(batchNo);
        log.setFileName(file.getOriginalFilename());
        log.setTotalRows(total);
        log.setSuccessRows(success);
        log.setFailRows(fail);
        log.setFailMessage(failMsg.length() > 4000
                ? failMsg.substring(0, 4000) : failMsg.toString());
        importLogMapper.insert(log);

        return getFileInfo(file);
    }

    // ============================== 导出 ==============================

    @PostMapping("export")
    @ApiOperation(value = "按筛选条件导出 EDI210 Excel")
    public void export(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = Maps.newHashMap();
        putIfNotBlank(params, "region",   request.getParameter("region"));
        putIfNotBlank(params, "scac",     request.getParameter("scac"));
        putIfNotBlank(params, "invNo",    request.getParameter("invNo"));
        putIfNotBlank(params, "bol",      request.getParameter("bol"));
        putIfNotBlank(params, "dateFrom", request.getParameter("dateFrom"));
        putIfNotBlank(params, "dateTo",   request.getParameter("dateTo"));

        List<EDI210ExcelRowDTO> rows = invoiceMapper.exportFlat(params);
        List<Object[]> data = new ArrayList<>(rows.size());
        for (EDI210ExcelRowDTO r : rows) {
            data.add(r.toArray());
        }
        String fileName = MessageFormat.format("{0}-{1}", "EDI210",
                DateUtil.format(new Date(), DateUtil.NORM_DATE_TIME_PATTERN_TWO));
        ExcelUtil.excelExport2(fileName, "EDI210", EDI210Schema.HEADERS, data, response);
    }

    // ============================== 模板下载 ==============================

    @GetMapping("template")
    @ApiOperation(value = "下载 EDI210 导入模板（空表头）")
    public void template(HttpServletResponse response) {
        ExcelUtil.excelExport2("EDI210-Template", "EDI210",
                EDI210Schema.HEADERS, new ArrayList<Object[]>(), response);
    }

    // ============================== 内部工具方法 ==============================

    private EDI210Invoice parseInvoice(XSSFRow row, String region, String batchNo) throws Exception {
        EDI210Invoice inv = new EDI210Invoice();
        inv.setRegion(region);
        inv.setScac(cell(row, EDI210Schema.COL_SCAC));
        inv.setInvNo(cell(row, EDI210Schema.COL_INV_NO));
        inv.setInvDate(cell(row, EDI210Schema.COL_INV_DATE));
        inv.setPaymentTerms(cell(row, EDI210Schema.COL_PAYMENT_TERMS));
        inv.setCur(cell(row, EDI210Schema.COL_CUR));
        inv.setInvAmount(cell(row, EDI210Schema.COL_INV_AMOUNT));
        inv.setTtCharge(cell(row, EDI210Schema.COL_TT_CHARGE));
        inv.setPickupDate(cell(row, EDI210Schema.COL_PICKUP_DATE));
        inv.setPickupTime(cell(row, EDI210Schema.COL_PICKUP_TIME));
        inv.setPodDate(cell(row, EDI210Schema.COL_POD_DATE));
        inv.setPodTime(cell(row, EDI210Schema.COL_POD_TIME));
        inv.setPodName(cell(row, EDI210Schema.COL_POD_NAME));
        inv.setGrossWeight(cell(row, EDI210Schema.COL_GROSS_WEIGHT));
        inv.setGwQualifier(cell(row, EDI210Schema.COL_GW_QUALIFIER));
        inv.setGwUnit(cell(row, EDI210Schema.COL_GW_UNIT));
        inv.setBillWeight(cell(row, EDI210Schema.COL_BILL_WEIGHT));
        inv.setBwQualifier(cell(row, EDI210Schema.COL_BW_QUALIFIER));
        inv.setBwUnit(cell(row, EDI210Schema.COL_BW_UNIT));
        inv.setBol(cell(row, EDI210Schema.COL_BOL));
        inv.setStatus(0);
        inv.setImportBatch(batchNo);
        return inv;
    }

    private EDI210Party parseParty(XSSFRow row, Long invoiceId, String role) throws Exception {
        EDI210Party p = new EDI210Party();
        p.setInvoiceId(invoiceId);
        p.setPartyRole(role);
        switch (role) {
            case EDI210Party.ROLE_BILL_TO:
                p.setName1(cell(row, EDI210Schema.COL_BT_NAME1));
                p.setName2(cell(row, EDI210Schema.COL_BT_NAME2));
                p.setAddress1(cell(row, EDI210Schema.COL_BT_ADDR1));
                p.setAddress2(cell(row, EDI210Schema.COL_BT_ADDR2));
                p.setCity(cell(row, EDI210Schema.COL_BT_CITY));
                p.setCountry(cell(row, EDI210Schema.COL_BT_COUNTRY));
                p.setPostalCode(cell(row, EDI210Schema.COL_BT_POSTAL));
                p.setStateProvince(cell(row, EDI210Schema.COL_BT_STATE));
                p.setAccount(cell(row, EDI210Schema.COL_BT_ACCOUNT));
                p.setLocation(cell(row, EDI210Schema.COL_BT_LOCATION));
                break;
            case EDI210Party.ROLE_BILL_FROM:
                // Bill-From 仅 5 列：Name / Address / City / Postal / Country
                p.setName1(cell(row, EDI210Schema.COL_BF_NAME));
                p.setAddress1(cell(row, EDI210Schema.COL_BF_ADDRESS));
                p.setCity(cell(row, EDI210Schema.COL_BF_CITY));
                p.setPostalCode(cell(row, EDI210Schema.COL_BF_POSTAL));
                p.setCountry(cell(row, EDI210Schema.COL_BF_COUNTRY));
                break;
            case EDI210Party.ROLE_SHIPPER:
                p.setName1(cell(row, EDI210Schema.COL_SH_NAME1));
                p.setName2(cell(row, EDI210Schema.COL_SH_NAME2));
                p.setAddress1(cell(row, EDI210Schema.COL_SH_ADDR1));
                p.setAddress2(cell(row, EDI210Schema.COL_SH_ADDR2));
                p.setCity(cell(row, EDI210Schema.COL_SH_CITY));
                p.setCountry(cell(row, EDI210Schema.COL_SH_COUNTRY));
                p.setPostalCode(cell(row, EDI210Schema.COL_SH_POSTAL));
                p.setStateProvince(cell(row, EDI210Schema.COL_SH_STATE));
                p.setAccount(cell(row, EDI210Schema.COL_SH_ACCOUNT));
                p.setLocation(cell(row, EDI210Schema.COL_SH_LOCATION));
                break;
            case EDI210Party.ROLE_CONSIGNEE:
                p.setName1(cell(row, EDI210Schema.COL_CN_NAME1));
                p.setName2(cell(row, EDI210Schema.COL_CN_NAME2));
                p.setAddress1(cell(row, EDI210Schema.COL_CN_ADDR1));
                p.setAddress2(cell(row, EDI210Schema.COL_CN_ADDR2));
                p.setCity(cell(row, EDI210Schema.COL_CN_CITY));
                p.setCountry(cell(row, EDI210Schema.COL_CN_COUNTRY));
                p.setPostalCode(cell(row, EDI210Schema.COL_CN_POSTAL));
                p.setStateProvince(cell(row, EDI210Schema.COL_CN_STATE));
                p.setAccount(cell(row, EDI210Schema.COL_CN_ACCOUNT));
                p.setLocation(cell(row, EDI210Schema.COL_CN_LOCATION));
                break;
            default:
                break;
        }
        return p;
    }

    private EDI210Charge parseCharge(XSSFRow row, Long invoiceId, int rIndex) throws Exception {
        EDI210Charge c = new EDI210Charge();
        c.setInvoiceId(invoiceId);
        c.setLineNo(rIndex);
        c.setDescription(cell(row, EDI210Schema.COL_DESCRIPTION));
        c.setRate(cell(row, EDI210Schema.COL_RATE));
        c.setRateQual(cell(row, EDI210Schema.COL_RATE_QUAL));
        c.setRatedCurrency(cell(row, EDI210Schema.COL_RATED_CURRENCY));
        c.setExchangeRate(cell(row, EDI210Schema.COL_EXCHANGE_RATE));
        c.setExpressedCurrency(cell(row, EDI210Schema.COL_EXPRESSED_CURRENCY));
        c.setCharge(cell(row, EDI210Schema.COL_CHARGE));
        c.setWeight(cell(row, EDI210Schema.COL_WEIGHT));
        c.setWeightQual(cell(row, EDI210Schema.COL_WEIGHT_QUAL));
        c.setWeightType(cell(row, EDI210Schema.COL_WEIGHT_TYPE));
        c.setSpChargeCode(cell(row, EDI210Schema.COL_SP_CHARGE_CODE));
        c.setSpChargeDesc(cell(row, EDI210Schema.COL_SP_CHARGE_DESC));
        c.setEquipmentCode(cell(row, EDI210Schema.COL_EQUIPMENT_CODE));
        c.setEquipmentType(cell(row, EDI210Schema.COL_EQUIPMENT_TYPE));
        c.setTotalWeight(cell(row, EDI210Schema.COL_TOTAL_WEIGHT));
        c.setTotalWeightQual(cell(row, EDI210Schema.COL_TOTAL_WEIGHT_QUAL));
        c.setServiceCode(cell(row, EDI210Schema.COL_SERVICE_CODE));
        c.setVolume(cell(row, EDI210Schema.COL_VOLUME));
        c.setVolumeQual(cell(row, EDI210Schema.COL_VOLUME_QUAL));
        c.setRatedQuantity(cell(row, EDI210Schema.COL_RATED_QUANTITY));
        c.setRatedPackageType(cell(row, EDI210Schema.COL_RATED_PACKAGE_TYPE));
        c.setBilledQuantity(cell(row, EDI210Schema.COL_BILLED_QUANTITY));
        c.setBilledQualifier(cell(row, EDI210Schema.COL_BILLED_QUALIFIER));
        c.setLength(cell(row, EDI210Schema.COL_LENGTH));
        c.setWidth(cell(row, EDI210Schema.COL_WIDTH));
        c.setHeight(cell(row, EDI210Schema.COL_HEIGHT));
        c.setMeasurementUnit(cell(row, EDI210Schema.COL_MEASUREMENT_UNIT));
        c.setDeclaredValue(cell(row, EDI210Schema.COL_DECLARED_VALUE));
        c.setCommodityClass(cell(row, EDI210Schema.COL_COMMODITY_CLASS));
        c.setCommodityCode(cell(row, EDI210Schema.COL_COMMODITY_CODE));
        c.setCorrectionIndicator(cell(row, EDI210Schema.COL_CORRECTION_INDICATOR));
        c.setCarriersDescription(cell(row, EDI210Schema.COL_CARRIERS_DESCRIPTION));
        c.setCarriersQuantity(cell(row, EDI210Schema.COL_CARRIERS_QUANTITY));
        c.setTransportService(cell(row, EDI210Schema.COL_TRANSPORT_SERVICE));
        c.setOriginZone(cell(row, EDI210Schema.COL_ORIGIN_ZONE));
        c.setDestinationZone(cell(row, EDI210Schema.COL_DESTINATION_ZONE));
        return c;
    }

    private EDI210Tax parseTax(XSSFRow row, Long invoiceId) throws Exception {
        EDI210Tax t = new EDI210Tax();
        t.setInvoiceId(invoiceId);
        t.setCustomerReference(cell(row, EDI210Schema.COL_CUSTOMER_REFERENCE));
        t.setPo(cell(row, EDI210Schema.COL_PO));
        t.setDn(cell(row, EDI210Schema.COL_DN));
        t.setRma(cell(row, EDI210Schema.COL_RMA));
        t.setHawb(cell(row, EDI210Schema.COL_HAWB));
        t.setSo(cell(row, EDI210Schema.COL_SO));
        t.setBillOfLadingNumber(cell(row, EDI210Schema.COL_BILL_OF_LADING_NUMBER));
        t.setTrackingNumber(cell(row, EDI210Schema.COL_TRACKING_NUMBER));
        t.setFreightBillNumber(cell(row, EDI210Schema.COL_FREIGHT_BILL_NUMBER));
        t.setBillingAccount(cell(row, EDI210Schema.COL_BILLING_ACCOUNT));
        t.setBillToAccount(cell(row, EDI210Schema.COL_BILL_TO_ACCOUNT));
        t.setDivisionIdentifier(cell(row, EDI210Schema.COL_DIVISION_IDENTIFIER));
        t.setInvoiceStandardCode(cell(row, EDI210Schema.COL_INVOICE_STANDARD_CODE));
        t.setCarrierName(cell(row, EDI210Schema.COL_CARRIER_NAME));
        t.setBusinessUnit(cell(row, EDI210Schema.COL_BUSINESS_UNIT));
        t.setCustomerVatRegistration(cell(row, EDI210Schema.COL_CUSTOMER_VAT_REG));
        t.setCarrierVatRegistration(cell(row, EDI210Schema.COL_CARRIER_VAT_REG));
        t.setCarrierTaxIdentity(cell(row, EDI210Schema.COL_CARRIER_TAX_IDENTITY));
        t.setSenderTaxIdentity(cell(row, EDI210Schema.COL_SENDER_TAX_IDENTITY));
        t.setRecipientTaxIdentity(cell(row, EDI210Schema.COL_RECIPIENT_TAX_IDENTITY));
        t.setTaxBureauName(cell(row, EDI210Schema.COL_TAX_BUREAU_NAME));
        t.setTaxBureauCode(cell(row, EDI210Schema.COL_TAX_BUREAU_CODE));
        return t;
    }

    private static String cell(XSSFRow row, int idx) throws Exception {
        if (row == null) return null;
        return ExcelUtil.getCellValue(row.getCell(idx));
    }

    private static void putIfNotBlank(Map<String, Object> params, String key, String value) {
        if (StringUtils.isNotBlank(value)) {
            params.put(key, value);
        }
    }

    private void checkFileName(MultipartFile file) {
        String fileOrigName = file.getOriginalFilename();
        if (fileOrigName == null || !fileOrigName.contains(".")) {
            throw new IllegalArgumentException("缺少后缀名");
        }
    }

    private FileInfo getFileInfo(MultipartFile file) throws IOException {
        String md5 = FileUtil.fileMd5(file.getInputStream());
        long size = file.getSize();
        String contentType = file.getContentType();
        FileInfo fileInfo = new FileInfo();
        fileInfo.setId(md5);
        fileInfo.setContentType(contentType);
        fileInfo.setSize(size);
        fileInfo.setType(contentType != null && contentType.startsWith("image/") ? 1 : 0);
        return fileInfo;
    }
}
