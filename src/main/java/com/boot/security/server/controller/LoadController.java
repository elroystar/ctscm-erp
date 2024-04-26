package com.boot.security.server.controller;

import com.boot.security.server.annotation.LogAnnotation;
import com.boot.security.server.dao.EDILoadMapper;
import com.boot.security.server.dto.load.EDILoad;
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
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Api(tags = "日志")
@RestController
@RequestMapping("/load")
public class LoadController {

    @Autowired
    private EDILoadMapper ediLoadMapper;

    @LogAnnotation
    @PostMapping("importLoadICT")
    @ApiOperation(value = "文件上传")
    public FileInfo importLoadICT(MultipartFile file) throws IOException {
        checkFileName(file);
        // 读取Excel
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0); //获取文件的第一个sheet
        int rows = sheet.getPhysicalNumberOfRows();
        for (int r = 1; r < rows; r++) {
            XSSFRow xssfRow = sheet.getRow(r);  //获取sheet的第一行
            try {
                String shipDate = ExcelUtil.getCellValue(xssfRow.getCell(0));
                String oem = ExcelUtil.getCellValue(xssfRow.getCell(1));
                String shippingMode = ExcelUtil.getCellValue(xssfRow.getCell(2));
                String forwarder = ExcelUtil.getCellValue(xssfRow.getCell(3));
                String hawb = ExcelUtil.getCellValue(xssfRow.getCell(4));

                String oemPalletId = ExcelUtil.getCellValue(xssfRow.getCell(6));
                String truckerPalletId = ExcelUtil.getCellValue(xssfRow.getCell(7));
                String region = ExcelUtil.getCellValue(xssfRow.getCell(8));
                String poe = ExcelUtil.getCellValue(xssfRow.getCell(9));
                String destination = ExcelUtil.getCellValue(xssfRow.getCell(10));
                String gateway = ExcelUtil.getCellValue(xssfRow.getCell(11));
                String consolidationWH = ExcelUtil.getCellValue(xssfRow.getCell(12));
                String grossWeightKg = ExcelUtil.getCellValue(xssfRow.getCell(13));
                String lengthCm = ExcelUtil.getCellValue(xssfRow.getCell(14));
                String widthCm = ExcelUtil.getCellValue(xssfRow.getCell(15));
                String heightCm = ExcelUtil.getCellValue(xssfRow.getCell(16));
                String weightOemDataKg = ExcelUtil.getCellValue(xssfRow.getCell(17));
                String lengthOemDataCm = ExcelUtil.getCellValue(xssfRow.getCell(18));
                String widthOemDataCm = ExcelUtil.getCellValue(xssfRow.getCell(19));
                String heightOemDataCm = ExcelUtil.getCellValue(xssfRow.getCell(20));
                String remark = ExcelUtil.getCellValue(xssfRow.getCell(25));
                BigDecimal grossWeightDecimal = new BigDecimal(grossWeightKg);
                BigDecimal weightOemDataDecimal = new BigDecimal(weightOemDataKg);
                BigDecimal weightDiscrepancyPercentage = weightOemDataDecimal.subtract(grossWeightDecimal)
                        .divide(grossWeightDecimal)
                        .multiply(new BigDecimal("100"))
                        .setScale(1, RoundingMode.HALF_UP);
                BigDecimal lengthCmDecimal = new BigDecimal(lengthCm);
                BigDecimal lengthOemDataCmDecimal = new BigDecimal(lengthOemDataCm);
                BigDecimal lengthDiscrepancyPercentage = lengthOemDataCmDecimal
                        .subtract(lengthCmDecimal).divide(lengthCmDecimal)
                        .multiply(new BigDecimal("100"))
                        .setScale(1, RoundingMode.HALF_UP);
                BigDecimal widthCmDecimal = new BigDecimal(widthCm);
                BigDecimal widthOemDataCmDecimal = new BigDecimal(widthOemDataCm);
                BigDecimal widthDiscrepancyPercentage = widthOemDataCmDecimal.subtract(widthCmDecimal)
                        .divide(widthCmDecimal)
                        .multiply(new BigDecimal("100"))
                        .setScale(1, RoundingMode.HALF_UP);
                BigDecimal heightCmDecimal = new BigDecimal(heightCm);
                BigDecimal heightOemDataCmDecimal = new BigDecimal(heightOemDataCm);
                BigDecimal heightDiscrepancyPercentage = heightOemDataCmDecimal.subtract(heightCmDecimal)
                        .divide(heightCmDecimal)
                        .multiply(new BigDecimal("100"))
                        .setScale(1, RoundingMode.HALF_UP);
                EDILoad ediLoad = new EDILoad();
                ediLoad.setShipDate(shipDate);
                ediLoad.setOem(oem);
                ediLoad.setShippingMode(shippingMode);
                ediLoad.setForwarder(forwarder);
                ediLoad.setHawb(hawb);
//                ediLoad.setSscc18PalletId(sscc18PalletId);
                ediLoad.setOemPalletId(oemPalletId);
                ediLoad.setTruckerPalletId(truckerPalletId);
                ediLoad.setRegion(region);
                ediLoad.setPoe(poe);
                ediLoad.setDestination(destination);
                ediLoad.setGateway(gateway);
                ediLoad.setConsolidationWH(consolidationWH);
                ediLoad.setGrossWeightKg(grossWeightDecimal);
                ediLoad.setLengthCm(lengthCmDecimal);
                ediLoad.setWidthCm(widthCmDecimal);
                ediLoad.setHeightCm(heightCmDecimal);
                ediLoad.setWeightOemDataKg(weightOemDataDecimal);
                ediLoad.setLengthOemDataCm(lengthOemDataCmDecimal);
                ediLoad.setWidthOemDataCm(widthOemDataCmDecimal);
                ediLoad.setHeightOemDataCm(heightOemDataCmDecimal);
                ediLoad.setWeightDiscrepancyPercentage(weightDiscrepancyPercentage);
                ediLoad.setLengthDiscrepancyPercentage(lengthDiscrepancyPercentage);
                ediLoad.setWidthDiscrepancyPercentage(widthDiscrepancyPercentage);
                ediLoad.setHeightDiscrepancyPercentage(heightDiscrepancyPercentage);
                ediLoad.setRemark(remark);
                ediLoadMapper.insertSelectiveICT(ediLoad);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        FileInfo fileInfo = getFileInfo(file);
        return fileInfo;
    }

    @LogAnnotation
    @PostMapping("importLoadIHub")
    @ApiOperation(value = "文件上传")
    public FileInfo importLoadIHub(MultipartFile file) throws IOException {
        checkFileName(file);
        // 读取Excel
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0); //获取文件的第一个sheet
        int rows = sheet.getPhysicalNumberOfRows();
        for (int r = 1; r < rows; r++) {
            XSSFRow xssfRow = sheet.getRow(r);  //获取sheet的第一行
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        FileInfo fileInfo = getFileInfo(file);
        return fileInfo;
    }

    @PostMapping("exportLoadICT")
    @ApiOperation(value = "导出ICT Load数据")
    public void exportLoadICT(HttpServletRequest request, HttpServletResponse response) {
        String shipDateForm = request.getParameter("shipDateForm");
        Map<String, Object> params = Maps.newHashMap();
        params.put("shipDate", shipDateForm);
        List<EDILoad> ediLoads = ediLoadMapper.listICT(params, 0, 999999);
        exportLoad(response, ediLoads);
    }

    private void exportLoad(HttpServletResponse response, List<EDILoad> ediLoads) {
        List<Object[]> data = new ArrayList<>();
        for (EDILoad ediLoad : ediLoads) {
            data.add(ediLoad.toString().split(","));
        }
        String headerStr = "Ship date," +
                "OEM," +
                "Shipping Mode," +
                "Forwarder," +
                "HAWB," +
                "SSCC18 Pallet ID," +
                "OEM Pallet ID," +
                "Trucker Pallet ID," +
                "Region," +
                "POE," +
                "Destination," +
                "Gateway," +
                "Consolidation W/H," +
                "Gross weight (kg)," +
                "Length (cm)," +
                "Width (cm)," +
                "Height (cm)," +
                "Weight - OEM Data (kg)," +
                "Length - OEM Data (cm)," +
                "Width - OEM Data (cm)," +
                "Height - OEM Data (cm)," +
                "Weight - Discrepancy (%)," +
                "Length - Discrepancy (%)," +
                "Width - Discrepancy (%)," +
                "Height - Discrepancy (%)," +
                "Remark";
        String[] headers = headerStr.split(",");
        String fileName = MessageFormat.format("{0}-{1}", "EDI LOAD", DateUtil.format(new Date(), DateUtil.NORM_DATE_TIME_PATTERN_TWO));
        ExcelUtil.excelExport2(fileName, null, headers, data, response);
    }

    @GetMapping("listLoadICT")
    @ApiOperation(value = "ICT load管理列表")
    public PageTableResponse listLoadICT(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return ediLoadMapper.countICT(request.getParams());
            }
        }, new ListHandler() {
            @Override
            public List<EDILoad> list(PageTableRequest request) {
                List<EDILoad> ediLoads = ediLoadMapper.listICT(request.getParams(), request.getOffset(), request.getLimit());
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
}
