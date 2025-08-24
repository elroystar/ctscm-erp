package com.boot.security.server.controller;

import com.boot.security.server.annotation.LogAnnotation;
import com.boot.security.server.dao.SawbMapper;
import com.boot.security.server.dto.load.SawbDTO;
import com.boot.security.server.model.FileInfo;
import com.boot.security.server.page.table.PageTableHandler;
import com.boot.security.server.page.table.PageTableHandler.CountHandler;
import com.boot.security.server.page.table.PageTableHandler.ListHandler;
import com.boot.security.server.page.table.PageTableRequest;
import com.boot.security.server.page.table.PageTableResponse;
import com.boot.security.server.utils.ExcelUtil;
import com.boot.security.server.utils.FileUtil;
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

import java.io.IOException;
import java.util.List;

@Api(tags = "日志")
@RestController
@RequestMapping("/swab")
public class SawbController {

    @Autowired
    private SawbMapper sawbMapper;


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
                String shipDate = ExcelUtil.getCellValue(xssfRow.getCell(0));
                String sawb = ExcelUtil.getCellValue(xssfRow.getCell(1));
                String hawb = ExcelUtil.getCellValue(xssfRow.getCell(2));
                String palletId = ExcelUtil.getCellValue(xssfRow.getCell(3));
                String ctns = ExcelUtil.getCellValue(xssfRow.getCell(4));
                String units = ExcelUtil.getCellValue(xssfRow.getCell(5));
                String gw = ExcelUtil.getCellValue(xssfRow.getCell(6));
                SawbDTO sawbDTO = new SawbDTO();
                sawbDTO.setStatus(1);
                sawbDTO.setShipDate(shipDate);
                sawbDTO.setHawb(hawb);
                sawbDTO.setSawb(sawb);
                sawbDTO.setPalletId(palletId);
                sawbDTO.setCtns(ctns);
                sawbDTO.setUnits(units);
                sawbDTO.setGw(gw);
                sawbMapper.insertSelective(sawbDTO);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        FileInfo fileInfo = getFileInfo(file);
        return fileInfo;
    }

    @GetMapping("list")
    @ApiOperation(value = "load管理列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return sawbMapper.count(request.getParams());
            }
        }, new ListHandler() {
            @Override
            public List<SawbDTO> list(PageTableRequest request) {
                List<SawbDTO> swabDates = sawbMapper.list(request.getParams(), request.getOffset(), request.getLimit());
                return swabDates;
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
