package com.boot.security.server.controller;

import com.boot.security.server.annotation.LogAnnotation;
import com.boot.security.server.dao.DictDao;
import com.boot.security.server.dao.DimTransferMapper;
import com.boot.security.server.dao.EDI945Mapper;
import com.boot.security.server.dao.EDIDimTransferMapper;
import com.boot.security.server.dto.load.DimTransfer;
import com.boot.security.server.dto.load.EDIDimTransfer;
import com.boot.security.server.dto.load.SendPddInfoDTO;
import com.boot.security.server.model.EDI945;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Api(tags = "日志")
@RestController
@RequestMapping("/dimTransfer")
public class DimTransferController {

    @Autowired
    private DimTransferMapper dimTransferMapper;

    @Autowired
    private EDI945Mapper edi945Mapper;

    @Autowired
    private EDIDimTransferMapper ediDimTransferMapper;

    @Autowired
    private DictDao dictDao;

    @PostMapping("sendPdd")
    @ApiOperation(value = "发送pdd数据")
    public Integer sendPdd(@RequestBody SendPddInfoDTO pddInfoDTO) {
        Map<String, Object> params = convert(pddInfoDTO);
        List<DimTransfer> sendPddData = dimTransferMapper.getSendPddData(params, 0, 999999);
        for (DimTransfer dimTransfer : sendPddData) {
            try {
                EDIDimTransfer ediDimTransfer = new EDIDimTransfer();
                BeanUtils.copyProperties(dimTransfer, ediDimTransfer);
                ediDimTransfer.setGrossWeight(dimTransfer.getGrossWeightPdd());
                ediDimTransfer.setCreatedtime(dimTransfer.getCreatedTime());
                ediDimTransfer.setWidth(dimTransfer.getWidthCm());
                ediDimTransfer.setHeight(dimTransfer.getHeightCm());
                ediDimTransfer.setLength(dimTransfer.getLengthCm());
                ediDimTransfer.setShipdate(dimTransfer.getShipDate());
                ediDimTransfer.setPalletIdOem(dimTransfer.getPalletId());
                ediDimTransfer.setStatus(0);
                ediDimTransferMapper.insertSelective(ediDimTransfer);
                // 推送完毕后标记为已推送
                dimTransfer.setStatus(2);
                dimTransferMapper.updateByPrimaryKeySelective(dimTransfer);
            } catch (Exception e) {
                // 推送完毕后标记为已推送
                dimTransfer.setStatus(2);
                dimTransferMapper.updateByPrimaryKeySelective(dimTransfer);
                e.printStackTrace();
            }
        }
        return 1;
    }

    @GetMapping("/get945Info/{hawb}")
    @ApiOperation(value = "获取945数据")
    public void get945Info(@PathVariable String hawb) {
        try {
            String[] split = hawb.split(",");
            for (String s : split) {
                try {
                    List<EDI945> infoByWaybill = edi945Mapper.getInfoByWaybill(s);
                    if (!infoByWaybill.isEmpty() && infoByWaybill.size() > 0) {
                        EDI945 edi945 = infoByWaybill.get(0);
                        List<DimTransfer> dimTransfers = dimTransferMapper.selectByHawb(s);
                        for (DimTransfer dimTransfer : dimTransfers) {
                            dimTransfer.setShipDate(edi945.getShipDate());
                            dimTransfer.setShippingMode(edi945.getShipMode());
                            dimTransfer.setForwarderPdd(edi945.getFwd());
                            dimTransfer.setPalletSscc18(edi945.getCartonNo());
                            List<String> regionTypeByK = dictDao.getRegionTypeByK(edi945.getPoeCountry());
                            if (!regionTypeByK.isEmpty()) {
                                dimTransfer.setRegion(regionTypeByK.get(0));
                            }
                            dimTransfer.setPoe(edi945.getPoe());
                            dimTransfer.setDestination(edi945.getPoeCountry());
                            String gateway = edi945.getGateway();
                            dimTransfer.setTerminal(gateway);
                            dimTransfer.setShipType(edi945.getShipway());
                            dimTransfer.setCountryOfClearance(edi945.getPoe());
                            dimTransfer.setShipToCity(edi945.getStCity());
                            dimTransfer.setScacFwd(edi945.getFwdCode());
                            dimTransfer.setScacTrucker(edi945.getTcCode());
                            dimTransfer.setShippingPoint(edi945.getOem());
                            dimTransfer.setStatus(1);
                            dimTransferMapper.updateByPrimaryKeySelective(dimTransfer);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @LogAnnotation
    @PostMapping("importLoad")
    @ApiOperation(value = "文件上传")
    public FileInfo importLoad(MultipartFile file) throws IOException {
        checkFileName(file);
        // 读取Excel
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0); //获取文件的第一个sheet
        int rows = sheet.getPhysicalNumberOfRows();
        for (int r = 1; r < rows; r++) {
            XSSFRow xssfRow = sheet.getRow(r);  //获取sheet的第一行
            try {
                String hawb = ExcelUtil.getCellValue(xssfRow.getCell(2));
                String palletIdOem = ExcelUtil.getCellValue(xssfRow.getCell(4));
                String oem = ExcelUtil.getCellValue(xssfRow.getCell(0));
                String shipDate = ExcelUtil.getCellValue(xssfRow.getCell(1));
                String palletIdTrucker = ExcelUtil.getCellValue(xssfRow.getCell(5));
                String grossWeight = ExcelUtil.getCellValue(xssfRow.getCell(6));
                String length = ExcelUtil.getCellValue(xssfRow.getCell(7));
                String width = ExcelUtil.getCellValue(xssfRow.getCell(8));
                String height = ExcelUtil.getCellValue(xssfRow.getCell(9));
                String gateway = ExcelUtil.getCellValue(xssfRow.getCell(10));
                String consolidationWarehouse = ExcelUtil.getCellValue(xssfRow.getCell(11));
                String nPIFlag = ExcelUtil.getCellValue(xssfRow.getCell(12));
                String securityLevel = ExcelUtil.getCellValue(xssfRow.getCell(13));
                String handover = ExcelUtil.getCellValue(xssfRow.getCell(14));
                String hubCode = ExcelUtil.getCellValue(xssfRow.getCell(15));
                String gccn = ExcelUtil.getCellValue(xssfRow.getCell(16));
                String containerNo = ExcelUtil.getCellValue(xssfRow.getCell(17));
                String truckNoExOEM = ExcelUtil.getCellValue(xssfRow.getCell(18));
                String truckNoExTrucker = ExcelUtil.getCellValue(xssfRow.getCell(19));
                String truckNoBorderExchange = ExcelUtil.getCellValue(xssfRow.getCell(20));
                String eLockExOEM = ExcelUtil.getCellValue(xssfRow.getCell(21));
                String eLockExTrucker = ExcelUtil.getCellValue(xssfRow.getCell(22));
                String pod = ExcelUtil.getCellValue(xssfRow.getCell(23));
                String terminal = ExcelUtil.getCellValue(xssfRow.getCell(24));
                String vesselIMO = ExcelUtil.getCellValue(xssfRow.getCell(25));
                String dwt = ExcelUtil.getCellValue(xssfRow.getCell(26));
                String porttoPortDistance = ExcelUtil.getCellValue(xssfRow.getCell(27));
                String vesselDistanceTraveled = ExcelUtil.getCellValue(xssfRow.getCell(28));
                String fastBoatService = ExcelUtil.getCellValue(xssfRow.getCell(29));
                String standardOceanService = ExcelUtil.getCellValue(xssfRow.getCell(30));
                String iCAOFlightCode = ExcelUtil.getCellValue(xssfRow.getCell(31));
                String aircraftType = ExcelUtil.getCellValue(xssfRow.getCell(32));
                String aircraftName = ExcelUtil.getCellValue(xssfRow.getCell(33));
                String flightDistance = ExcelUtil.getCellValue(xssfRow.getCell(34));
                String flightTime = ExcelUtil.getCellValue(xssfRow.getCell(35));
                String flightNo = ExcelUtil.getCellValue(xssfRow.getCell(36));
                String driverPhNo = ExcelUtil.getCellValue(xssfRow.getCell(37));
                String trailerNo = ExcelUtil.getCellValue(xssfRow.getCell(38));
                String site = ExcelUtil.getCellValue(xssfRow.getCell(39));
                List<DimTransfer> dimTransfers = dimTransferMapper.selectByHawbAndPalletId(hawb, palletIdOem);
                if (!dimTransfers.isEmpty()) {
                    for (DimTransfer dimTransfer : dimTransfers) {
                        dimTransfer.setOem(oem);
                        dimTransfer.setShipDate(shipDate);
                        dimTransfer.setPalletIdTrucker(palletIdTrucker);
                        dimTransfer.setGrossWeightPdd(parseBigDecimal(grossWeight));
                        dimTransfer.setLengthCm(parseBigDecimal(length));
                        dimTransfer.setWidthCm(parseBigDecimal(width));
                        dimTransfer.setHeightCm(parseBigDecimal(height));
                        dimTransfer.setGateway(gateway);
                        dimTransfer.setConsolidationWarehouse(consolidationWarehouse);
                        dimTransfer.setNpiFlag(nPIFlag);
                        dimTransfer.setSecurityLevel(securityLevel);
                        dimTransfer.setHandover(handover);
                        dimTransfer.setHubCode(hubCode);
                        dimTransfer.setGccn(gccn);
                        dimTransfer.setContainerNo(containerNo);
                        dimTransfer.setTruckNoExoem(truckNoExOEM);
                        dimTransfer.setTruckNoExtrucker(truckNoExTrucker);
                        dimTransfer.setTruckNoBorderexchange(truckNoBorderExchange);
                        dimTransfer.setElockExoem(eLockExOEM);
                        dimTransfer.setElockExtrucker(eLockExTrucker);
                        dimTransfer.setPod(pod);
                        dimTransfer.setTerminal(terminal);
                        dimTransfer.setVesselImo(vesselIMO);
                        dimTransfer.setDwt(parseBigDecimal(dwt));
                        dimTransfer.setPortToPortDistance(parseBigDecimal(porttoPortDistance));
                        dimTransfer.setVesselDistanceTraveled(parseBigDecimal(vesselDistanceTraveled));
                        dimTransfer.setFastBoatService(fastBoatService);
                        dimTransfer.setStandardOceanService(standardOceanService);
                        dimTransfer.setIcaoFlightCode(iCAOFlightCode);
                        dimTransfer.setAircraftType(aircraftType);
                        dimTransfer.setAirlineName(aircraftName);
                        dimTransfer.setFlightDistance(parseBigDecimal(flightDistance));
                        dimTransfer.setFlightTime(parseBigDecimal(flightTime));
                        dimTransfer.setFlightNo(flightNo);
                        dimTransfer.setDriverPhNo(driverPhNo);
                        dimTransfer.setTrailerNo(trailerNo);

                        dimTransfer.setSite(site);

                        dimTransferMapper.updateByPrimaryKeySelective(dimTransfer);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        FileInfo fileInfo = getFileInfo(file);
        return fileInfo;
    }

    @PostMapping("exportLoad")
    @ApiOperation(value = "导出Load数据")
    public void exportLoad(HttpServletRequest request, HttpServletResponse response) {
        String shipDateForm = request.getParameter("shipDateForm");
        String hawbForm = request.getParameter("hawbForm");
        String licencePlateNumberForm = request.getParameter("licencePlateNumberForm");
        String oemForm = request.getParameter("oemForm");
        String gatewayForm = request.getParameter("gatewayForm");
        String pddStatusForm = request.getParameter("pddStatusForm");
        Map<String, Object> params = Maps.newHashMap();
        params.put("shipDate", shipDateForm);
        params.put("hawb", hawbForm);
        params.put("licencePlateNumber", licencePlateNumberForm);
        params.put("oem", oemForm);
        params.put("gateway", gatewayForm);
        if (StringUtils.isNotBlank(pddStatusForm)) {
            params.put("status", Integer.valueOf(pddStatusForm));
        }
        List<DimTransfer> ediLoads = dimTransferMapper.list(params, 0, 999999);
        exportLoad(response, ediLoads);
    }

    private void exportLoad(HttpServletResponse response, List<DimTransfer> ediLoads) {
        List<Object[]> data = new ArrayList<>();
        for (DimTransfer ediLoad : ediLoads) {
            data.add(ediLoad.toString().split("#"));
        }
        String headerStr = "OEM," +
                "Ship Date," +
                "Shipping Point," +
                "Shipping Mode," +
                "Forwarder," +
                "HAWB/BOL," +
                "MAWB/MBOL," +
                "Pallet ID OEM," +
                "Pallet SSCC18," +
                "Pallet ID Trucker," +
                "Gross Weight," +
                "Gross Weight Pdd," +
                "Length," +
                "Width," +
                "Height," +
                "Region," +
                "POE," +
                "Destination," +
                "Gateway," +
                "Consolidation warehouse," +
                "NPI Flag," +
                "Security Level," +
                "Handover," +
                "Ship Type," +
                "Hub Code," +
                "GCCN," +
                "Country Of Clearance," +
                "Ship To City," +
                "Container No," +
                "Truck No_ExOEM," +
                "Truck No_ExTrucker," +
                "Truck No_BorderExchange," +
                "ELock_ExOEM," +
                "ELock_ExTrucker," +
                "POD," +
                "Terminal/Ocean Port," +
                "SCAC_FWD," +
                "SCAC_Trucker," +
                "Vessel IMO," +
                "DWT," +
                "Port to Port Distance," +
                "Vessel Distance Traveled," +
                "Fast Boat Service/Standard Ocean Service," +
//                "Standard Ocean Service," +
                "ICAO Flight Code," +
                "Aircraft Type," +
                "Aircraft Name," +
                "Flight Distance," +
                "Flight Time," +
                "Flight No," +
//                "GPS Transmitter No," +
                "Driver Ph No," +
                "Trailer No," +
                "Site," +
                "Pdd Status";
        String[] headers = headerStr.split(",");
        String fileName = MessageFormat.format("{0}-{1}", "EDI LOAD", DateUtil.format(new Date(), DateUtil.NORM_DATE_TIME_PATTERN_TWO));
        ExcelUtil.excelExport2(fileName, null, headers, data, response);
    }

    @PostMapping("exportLoadManifest")
    @ApiOperation(value = "导出LoadManifest数据")
    public void exportLoadManifest(HttpServletRequest request, HttpServletResponse response) {
        String createdTimeForm = request.getParameter("createdTimeForm");
        String licencePlateNumberForm = request.getParameter("licencePlateNumberForm");
        Map<String, Object> params = Maps.newHashMap();
        params.put("createdTime", createdTimeForm);
        params.put("licencePlateNumber", licencePlateNumberForm);
        List<DimTransfer> ediLoads = dimTransferMapper.list(params, 0, 999999);
        exportLoadManifest(response, ediLoads);
    }

    private void exportLoadManifest(HttpServletResponse response, List<DimTransfer> ediLoads) {
        List<Object[]> data = new ArrayList<>();
        for (DimTransfer ediLoad : ediLoads) {
            String[] rowData = ediLoad.toLoadManifestString().split(",");
            // 转换Forwarder字段（索引为6）
            if (rowData.length > 6) {
                rowData[6] = convertForwarder(rowData[6]);
            }
            data.add(rowData);
        }
        String headerStr = "Created Time," +
                "Loading No," +
                "Pallet ID," +
                "Cartons," +
                "Quantity," +
                "Gross Weight," +
                "Forwarder," +
                "Destination," +
                "HAWB," +
                "Licence Plate Number";
        String[] headers = headerStr.split(",");
        String fileName = MessageFormat.format("{0}-{1}", "LOAD MANIFEST", DateUtil.format(new Date(), DateUtil.NORM_DATE_TIME_PATTERN_TWO));
        ExcelUtil.excelExport2(fileName, null, headers, data, response);
    }

    @GetMapping("list")
    @ApiOperation(value = "load管理列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return dimTransferMapper.count(request.getParams());
            }
        }, new ListHandler() {
            @Override
            public List<DimTransfer> list(PageTableRequest request) {
                List<DimTransfer> ediLoads = dimTransferMapper.list(request.getParams(), request.getOffset(), request.getLimit());
                return ediLoads;
            }
        }).handle(request);
    }

    @GetMapping("listLoad")
    @ApiOperation(value = "loadList管理列表")
    public PageTableResponse listLoad(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return dimTransferMapper.count(request.getParams());
            }
        }, new ListHandler() {
            @Override
            public List<DimTransfer> list(PageTableRequest request) {
                List<DimTransfer> ediLoads = dimTransferMapper.list(request.getParams(), request.getOffset(), request.getLimit());
                return ediLoads;
            }
        }).handle(request);
    }

    private void checkFileName(MultipartFile file) {
        String fileOrigName = file.getOriginalFilename();
        if (!fileOrigName.contains(".")) {
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
        fileInfo.setType(contentType.startsWith("image/") ? 1 : 0);
        return fileInfo;
    }

    private Map<String, Object> convert(Object object) {
        Map<String, Object> map = Maps.newHashMap();
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(object));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    private BigDecimal parseBigDecimal(String input) {
        try {
            return new BigDecimal(input);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * 转换Forwarder字段值
     * @param forwarder 原始forwarder值
     * @return 转换后的forwarder值
     */
    private String convertForwarder(String forwarder) {
        if (forwarder == null || forwarder.trim().isEmpty()) {
            return forwarder;
        }
        
        // Forwarder转换映射表
        Map<String, String> forwarderMap = Maps.newHashMap();
        // APEX相关
        forwarderMap.put("XAPXL", "APEX");
        forwarderMap.put("APEX", "APEX");
        forwarderMap.put("APXL", "APEX");
        forwarderMap.put("CGS-SH", "APEX");
        
        // CEVA相关
        forwarderMap.put("XEAGL", "CEVA");
        forwarderMap.put("EAGL", "CEVA");
        forwarderMap.put("EGL", "CEVA");
        forwarderMap.put("1060049080", "CEVA");
        
        // China Post相关
        forwarderMap.put("XCPEL", "China Post");
        forwarderMap.put("XCPLFF", "China Post");
        forwarderMap.put("XCPLHK", "China Post");
        
        // CNTIC相关
        forwarderMap.put("XCNTIC", "CNTIC");
        
        // DGF相关
        forwarderMap.put("XDZNA", "DGF");
        forwarderMap.put("DZNA", "DGF");
        forwarderMap.put("1060017927", "DGF");
        forwarderMap.put("1060036203", "DGF");
        forwarderMap.put("XDGF", "DGF");
        forwarderMap.put("DGF", "DGF");
        forwarderMap.put("DGFI", "DGF");
        forwarderMap.put("BDHL", "DGF");
        forwarderMap.put("ECL", "DGF");
        forwarderMap.put("ECLI", "DGF");
        forwarderMap.put("DHL", "DGF");
        forwarderMap.put("DGF-CN", "DGF");
        
        // DHL相关
        forwarderMap.put("XDHLE", "DHL");
        forwarderMap.put("XDHLKR", "DHL");
        forwarderMap.put("XDHLG", "DHL");
        forwarderMap.put("DHLG", "DHL");
        forwarderMap.put("1060000099", "DHL");
        forwarderMap.put("DHLBBX", "DHL");
        forwarderMap.put("1060025331", "DHL");
        forwarderMap.put("1060029373", "DHL");
        forwarderMap.put("1060032962", "DHL");
        forwarderMap.put("PDHL", "DHL");
        forwarderMap.put("PPBX", "DHL");
        
        // EI相关
        forwarderMap.put("1060027904", "EI");
        forwarderMap.put("EI", "EI");
        forwarderMap.put("1060027895", "EI");
        forwarderMap.put("XEPL", "EI");
        
        // Expeditors相关
        forwarderMap.put("EXP", "Expeditors");
        
        // FedEx相关
        forwarderMap.put("XFEDE", "FedEx");
        forwarderMap.put("FEDC", "FedEx");
        forwarderMap.put("FEDE", "FedEx");
        forwarderMap.put("FEDM", "FedEx");
        forwarderMap.put("FED", "FedEx");
        
        // Flexport相关
        forwarderMap.put("XFLXT", "Flexport");
        
        // FTN相关
        forwarderMap.put("XFTNQ", "FTN");
        forwarderMap.put("XFTNV", "FTN");
        
        // GEO相关
        forwarderMap.put("1060027586", "GEO");
        forwarderMap.put("1060034741", "GEO");
        forwarderMap.put("GEO", "GEO");
        
        // Geodis相关
        forwarderMap.put("GDW", "Geodis");
        
        // KN相关
        forwarderMap.put("1060020795", "KN");
        forwarderMap.put("1060029822", "KN");
        forwarderMap.put("1060051752", "KN");
        forwarderMap.put("KAN", "KN");
        forwarderMap.put("KNI", "KN");
        forwarderMap.put("XKHNN", "KN");
        forwarderMap.put("KN", "KN");
        forwarderMap.put("NKN", "KN");
        forwarderMap.put("K&N-CN", "KN");
        
        // KWE相关
        forwarderMap.put("XKWE", "KWE");
        
        // Morrison相关
        forwarderMap.put("XMORR", "Morrison");
        forwarderMap.put("MORR", "Morrison");
        forwarderMap.put("XMOR", "Morrison");
        forwarderMap.put("MOR", "Morrison");
        
        // Nippon相关
        forwarderMap.put("XNEX", "Nippon");
        forwarderMap.put("NEX", "Nippon");
        
        // Schenker相关
        forwarderMap.put("XBNAF", "Schenker");
        forwarderMap.put("BNAF", "Schenker");
        forwarderMap.put("1060000064", "Schenker");
        forwarderMap.put("XBAX", "Schenker");
        forwarderMap.put("BAX", "Schenker");
        forwarderMap.put("BAXI", "Schenker");
        forwarderMap.put("1060028361", "Schenker");
        forwarderMap.put("1060026371", "Schenker");
        forwarderMap.put("SCK-CN", "Schenker");
        
        // SF相关
        forwarderMap.put("XSF", "SF");
        forwarderMap.put("SF", "SF");
        
        // SLC相关
        forwarderMap.put("XSLC", "SLC");
        
        // TCI相关
        forwarderMap.put("0080154092", "TCI");
        
        // TNT相关
        forwarderMap.put("1060019776", "TNT");
        forwarderMap.put("XTNT", "TNT");
        forwarderMap.put("XTNTA", "TNT");
        forwarderMap.put("XTNTKR", "TNT");
        forwarderMap.put("XTNTTW", "TNT");
        
        // TOLL相关
        forwarderMap.put("XTOLL", "TOLL");
        
        // UPS相关
        forwarderMap.put("UPS", "UPS");
        forwarderMap.put("UPSC", "UPS");
        forwarderMap.put("1060022352", "UPS");
        forwarderMap.put("1060032936", "UPS");
        forwarderMap.put("XUPSC", "UPS");
        forwarderMap.put("XUPSM", "UPS");
        forwarderMap.put("XUPSN", "UPS");
        forwarderMap.put("XUPSSG", "UPS");
        forwarderMap.put("XUPSF", "UPS SC");
        
        // YMT相关
        forwarderMap.put("XYMT", "YMT");
        
        // Yamato相关
        forwarderMap.put("YMT", "Yamato");
        forwarderMap.put("QCIMCAC", "AC");
        forwarderMap.put("QCIMC", "FG");

        // 如果在映射表中找到对应的值，返回转换后的值，否则返回原值
        return forwarderMap.getOrDefault(forwarder.trim(), forwarder);
    }
}
