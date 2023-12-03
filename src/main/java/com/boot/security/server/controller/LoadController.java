package com.boot.security.server.controller;

import com.boot.security.server.annotation.LogAnnotation;
import com.boot.security.server.dao.*;
import com.boot.security.server.dto.EDI945ExportExcelDTO;
import com.boot.security.server.model.*;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    private EdiLoadIHubMapper ediLoadIHubMapper;

    @LogAnnotation
    @PostMapping("importLoad")
    @ApiOperation(value = "文件上传")
    public FileInfo importTruck(MultipartFile file) throws IOException {
        checkFileName(file);
        // 读取Excel
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0); //获取文件的第一个sheet
        int rows = sheet.getPhysicalNumberOfRows();
        for (int r = 1; r < rows; r++) {
            XSSFRow xssfRow = sheet.getRow(r);  //获取sheet的第一行
            try {
                String hoDate = ExcelUtil.getCellValue(xssfRow.getCell(0));
                String oem = ExcelUtil.getCellValue(xssfRow.getCell(1));
                String forwarder = ExcelUtil.getCellValue(xssfRow.getCell(2));
                String hawb = ExcelUtil.getCellValue(xssfRow.getCell(3));
                String palletId = ExcelUtil.getCellValue(xssfRow.getCell(4));
                String plts = ExcelUtil.getCellValue(xssfRow.getCell(5));
                String ctns = ExcelUtil.getCellValue(xssfRow.getCell(6));
                String units = ExcelUtil.getCellValue(xssfRow.getCell(7));
                String lengthCm = ExcelUtil.getCellValue(xssfRow.getCell(8));
                String widthCm = ExcelUtil.getCellValue(xssfRow.getCell(9));
                String heigthCm = ExcelUtil.getCellValue(xssfRow.getCell(10));
                String weigthKg = ExcelUtil.getCellValue(xssfRow.getCell(11));
                String remark = ExcelUtil.getCellValue(xssfRow.getCell(12));
                EdiLoadIHub ediLoadIHub = new EdiLoadIHub();
                ediLoadIHub.setStatus(0);
                ediLoadIHub.setHoDate(hoDate);
                ediLoadIHub.setOem(oem);
                ediLoadIHub.setForwarder(forwarder);
                ediLoadIHub.setHawb(hawb);
                ediLoadIHub.setPalletId(palletId);
                ediLoadIHub.setPlts(plts);
                ediLoadIHub.setCtns(ctns);
                ediLoadIHub.setUnits(units);
                ediLoadIHub.setLengthCm(lengthCm);
                ediLoadIHub.setWidthCm(widthCm);
                ediLoadIHub.setHeigthCm(heigthCm);
                ediLoadIHub.setWeigthKg(weigthKg);
                ediLoadIHub.setRemark(remark);
                ediLoadIHubMapper.insertSelective(ediLoadIHub);
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
        String palletIdForm = request.getParameter("palletIdForm");
        String hoDateForm = request.getParameter("hoDateForm");
        Map<String, Object> params = Maps.newHashMap();
        params.put("palletId", palletIdForm);
        params.put("hoDate", hoDateForm);
        List<EdiLoadIHub> loadIHubs = ediLoadIHubMapper.list(params, 0, 999999);
        exportLoad(response, loadIHubs);
    }

    private void exportLoad(HttpServletResponse response, List<EdiLoadIHub> loadIHubs) {
        List<Object[]> data = new ArrayList<>();
        for (EdiLoadIHub loadIHub : loadIHubs) {
            data.add(loadIHub.toString().split(","));
        }
        String headerStr = ",HO Date," +
                "OEM," +
                "Forwarder," +
                "HAWB," +
                "Pallet ID," +
                "PLTs," +
                "CTNS," +
                "UNITS," +
                "Length/cm," +
                "Width/cm," +
                "Height/cm," +
                "Weight/kg," +
                "Remark";
        String[] headers = headerStr.split(",");
        String fileName = MessageFormat.format("{0}-{1}", "EDI945", DateUtil.format(new Date(), DateUtil.NORM_DATE_TIME_PATTERN_TWO));
        ExcelUtil.excelExport2(fileName, null, headers, data, response);
    }

    @GetMapping("listLoadIHub")
    @ApiOperation(value = "IHub load管理列表")
    public PageTableResponse listLoadIHub(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return ediLoadIHubMapper.count(request.getParams());
            }
        }, new ListHandler() {
            @Override
            public List<EdiLoadIHub> list(PageTableRequest request) {
                List<EdiLoadIHub> loadIHubs = ediLoadIHubMapper.list(request.getParams(), request.getOffset(), request.getLimit());
                return loadIHubs;
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
