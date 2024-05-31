package com.boot.security.server.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ObjectUtils;

/**
 * excel工具类
 *
 * @author 小威老师 xiaoweijiagou@163.com
 */
public class ExcelUtil {

    public static void excelLocal(String path, String fileName, String[] headers, List<Object[]> datas) {
        Workbook workbook = getWorkbook(headers, datas, null);
        if (workbook != null) {
            ByteArrayOutputStream byteArrayOutputStream = null;
            FileOutputStream fileOutputStream = null;
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                workbook.write(byteArrayOutputStream);

                String suffix = ".xls";
                File file = new File(path + File.separator + fileName + suffix);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }

                fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(byteArrayOutputStream.toByteArray());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 导出excel
     *
     * @param fileName
     * @param headers
     * @param datas
     * @param response
     */
    public static void excelExport(String fileName, String[] headers, List<Object[]> datas,
                                   HttpServletResponse response) {
        Workbook workbook = getWorkbook(headers, datas, null);
        if (workbook != null) {
            ByteArrayOutputStream byteArrayOutputStream = null;
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                workbook.write(byteArrayOutputStream);

                String suffix = ".xls";
                response.setContentType("application/vnd.ms-excel;charset=utf-8");
                response.setHeader("Content-Disposition",
                        "attachment;filename=" + new String((fileName + suffix).getBytes(), "iso-8859-1"));

                OutputStream outputStream = response.getOutputStream();
                outputStream.write(byteArrayOutputStream.toByteArray());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 导出excel
     *
     * @param fileName
     * @param sheetName
     * @param headers
     * @param datas
     * @param response
     */
    public static void excelExport2(String fileName, String sheetName, String[] headers, List<Object[]> datas,
                                    HttpServletResponse response) {
        Workbook workbook = getWorkbook(headers, datas, sheetName);
        if (workbook != null) {
            ByteArrayOutputStream byteArrayOutputStream = null;
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                workbook.write(byteArrayOutputStream);

                String suffix = ".xlsx";
                response.setContentType("application/vnd.ms-excel;charset=utf-8");
                response.setHeader("Content-Disposition",
                        "attachment;filename=" + new String((fileName + suffix).getBytes(), "UTF-8"));

                OutputStream outputStream = response.getOutputStream();
                outputStream.write(byteArrayOutputStream.toByteArray());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @param headers 列头
     * @param datas   数据
     * @return
     */
    public static Workbook getWorkbook(String[] headers, List<Object[]> datas, String sheetName) {
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet();
        if (StringUtils.isNotBlank(sheetName)) {
            workbook.setSheetName(0, sheetName);
        }
        Row row = null;
        Cell cell = null;
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        DataFormat dataFormat = workbook.createDataFormat();
        style.setDataFormat(dataFormat.getFormat("@"));

        Font font = workbook.createFont();

        int line = 0, maxColumn = 0;
        if (headers != null && headers.length > 0) {// 设置列头
            row = sheet.createRow(line++);
            row.setHeightInPoints(23);
            font.setBold(true);
            font.setFontHeightInPoints((short) 13);
            style.setFont(font);

            maxColumn = headers.length;
            for (int i = 0; i < maxColumn; i++) {
                cell = row.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(style);
            }
        }

        if (datas != null && datas.size() > 0) {// 渲染数据
            for (int index = 0, size = datas.size(); index < size; index++) {
                Object[] data = datas.get(index);
                if (data != null && data.length > 0) {
                    row = sheet.createRow(line++);
                    row.setHeightInPoints(20);

                    int length = data.length;
                    if (length > maxColumn) {
                        maxColumn = length;
                    }

                    for (int i = 0; i < length; i++) {
                        cell = row.createCell(i);
                        cell.setCellValue(ObjectUtils.isEmpty(data[i]) ? "" : data[i].toString());
                    }
                }
            }
        }

        for (int i = 0; i < maxColumn; i++) {
            sheet.autoSizeColumn(i);
        }

        return workbook;
    }

    /**
     * 获取单元格的值
     *
     * @param cell
     * @return
     * @throws Exception
     */
    public static String getCellValue(Cell cell) throws Exception {
        String cellVal = "";
        if (cell == null) {
            return cellVal;
        }
        int cellType = cell.getCellType();
        switch (cellType) {
            case Cell.CELL_TYPE_STRING:
                cellVal = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                    cellVal = sdf.format(cell.getDateCellValue());
                } else {
                    HSSFDataFormatter dataFormatter = new HSSFDataFormatter();
                    cellVal = dataFormatter.formatCellValue(cell);
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                cellVal = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA:
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cellVal = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_ERROR:
            default:
                throw new Exception("单元格格式异常！");
        }
        return cellVal.trim();
    }
}
