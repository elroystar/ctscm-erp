package com.boot.security.server.controller;

import com.boot.security.server.annotation.LogAnnotation;
import com.boot.security.server.dao.*;
import com.boot.security.server.dto.EDI214ExportExcelDTO;
import com.boot.security.server.dto.EDI945ExportExcelDTO;
import com.boot.security.server.dto.EditTruckDTO;
import com.boot.security.server.dto.Send997InfoDTO;
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
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Api(tags = "日志")
@RestController
@RequestMapping("/shipper")
public class ShipperController {

    @Autowired
    private EDI945Mapper edi945Mapper;

    @Autowired
    private DictDao dictDao;

    @Autowired
    private EdiWzUploadMapper ediWzUploadMapper;

    @Autowired
    private EdiWzCancelMapper ediWzCancelMapper;

    @Autowired
    private FieldIhub997Mapper fieldIhub997Mapper;

    @GetMapping("/getGPSInformation/{truckPlantNumber}/{desLon}/{desLat}/{gpsDevice}/{ctTracking}")
    @ApiOperation(value = "获取gps数据")
    public void getGPSInformation(@PathVariable String truckPlantNumber,
                                  @PathVariable String desLon,
                                  @PathVariable String desLat,
                                  @PathVariable String gpsDevice,
                                  @PathVariable String ctTracking) {
        try {
            EDI945 edi945GPS = getEdi945(truckPlantNumber, desLon, desLat, gpsDevice, ctTracking);
            edi945Mapper.insertGPS(edi945GPS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/getGPSInformationOEM/{truckPlantNumber}/{desLon}/{desLat}/{gpsDevice}/{ctTracking}")
    @ApiOperation(value = "获取OEM gps数据")
    public void getGPSInformationOEM(@PathVariable String truckPlantNumber,
                                     @PathVariable String desLon,
                                     @PathVariable String desLat,
                                     @PathVariable String gpsDevice,
                                     @PathVariable String ctTracking) {
        try {
            EDI945 edi945GPS = getEdi945(truckPlantNumber, desLon, desLat, gpsDevice, ctTracking);
            edi945Mapper.insertGPSOEM(edi945GPS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/getGPSInformationICT/{truckPlantNumber}/{desLon}/{desLat}/{gpsDevice}/{ctTracking}")
    @ApiOperation(value = "获取ICT gps数据")
    public void getGPSInformationICT(@PathVariable String truckPlantNumber,
                                     @PathVariable String desLon,
                                     @PathVariable String desLat,
                                     @PathVariable String gpsDevice,
                                     @PathVariable String ctTracking) {
        try {
            EDI945 edi945GPS = getEdi945(truckPlantNumber, desLon, desLat, gpsDevice, ctTracking);
            edi945Mapper.insertGPSICT(edi945GPS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private EDI945 getEdi945(@PathVariable String truckPlantNumber, @PathVariable String desLon, @PathVariable String desLat, @PathVariable String gpsDevice, @PathVariable String ctTracking) {
        EDI945 edi945GPS = new EDI945();
        edi945GPS.setTruckPlantNumber(truckPlantNumber);
        edi945GPS.setGpsDevice(gpsDevice);
        edi945GPS.setCtTracking(ctTracking);
        edi945GPS.setDesLon(desLon);
        edi945GPS.setDesLat(desLat);
        edi945GPS.setDeviceType("GPS");
        edi945GPS.setSource("北斗标机");
        edi945GPS.setCompanyCode("CTSC");
        edi945GPS.setLocationTime(new Date());
        return edi945GPS;
    }

    @GetMapping("/stopGPSInformation/{stopGpsDevice}")
    @ApiOperation(value = "停止获取gps数据")
    public void stopGPSInformation(@PathVariable String stopGpsDevice) {
        try {
            List<EDI945> edi945List = edi945Mapper.selectGPSByGpsDevice(stopGpsDevice);
            if (!edi945List.isEmpty()) {
                for (EDI945 edi945 : edi945List) {
                    handleEDITable(edi945);
                    edi945Mapper.updateGPSByTruckPlantNumber(edi945);
                    edi945Mapper.updateByTruckPlantNumber(edi945);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/stopGPSInformationOEM/{stopGpsDevice}")
    @ApiOperation(value = "停止获取OEM gps数据")
    public void stopGPSInformationOEM(@PathVariable String stopGpsDevice) {
        try {
            List<EDI945> edi945List = edi945Mapper.selectGPSByGpsDeviceOEM(stopGpsDevice);
            if (!edi945List.isEmpty()) {
                for (EDI945 edi945 : edi945List) {
                    handleEDITable(edi945);
                    edi945Mapper.updateGPSByTruckPlantNumberOEM(edi945);
                    edi945Mapper.updateByTruckPlantNumberOEM(edi945);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/stopGPSInformationICT/{stopGpsDevice}")
    @ApiOperation(value = "停止获取ICT gps数据")
    public void stopGPSInformationICT(@PathVariable String stopGpsDevice) {
        try {
            List<EDI945> edi945List = edi945Mapper.selectGPSByGpsDeviceICT(stopGpsDevice);
            if (!edi945List.isEmpty()) {
                for (EDI945 edi945 : edi945List) {
                    handleEDITable(edi945);
                    edi945Mapper.updateGPSByTruckPlantNumberICT(edi945);
                    edi945Mapper.updateByTruckPlantNumberICT(edi945);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleEDITable(EDI945 edi945) {
        // upload表数据写入
        List<EdiWzUpload> ediWzUploads = ediWzUploadMapper.selectBykPlantNumber(edi945.getTruckPlantNumber());
        if (ediWzUploads.isEmpty()) {
            EdiWzUpload ediWzUpload = new EdiWzUpload();
            ediWzUpload.setStatus(0);
            ediWzUpload.setDeviceid(edi945.getTruckPlantNumber());
            ediWzUpload.setLongitude(edi945.getDesLon());
            ediWzUpload.setLatitude(edi945.getDesLat());
            ediWzUpload.setLocationtime(DateUtil.format(new Date(), DateUtil.NORM_DATETIME_PATTERN));
            ediWzUploadMapper.insertSelective(ediWzUpload);
        } else {
            EdiWzUpload ediWzUpload = ediWzUploads.get(0);
            ediWzUpload.setStatus(0);
            ediWzUpload.setDeviceid(edi945.getTruckPlantNumber());
            ediWzUpload.setLongitude(edi945.getDesLon());
            ediWzUpload.setLatitude(edi945.getDesLat());
            ediWzUpload.setLocationtime(DateUtil.format(new Date(), DateUtil.NORM_DATETIME_PATTERN));
            ediWzUploadMapper.updateByPrimaryKey(ediWzUpload);
        }
        // 取消数据edi表数据写入
        List<EdiWzCancel> ediWzCancels = ediWzCancelMapper.selectBykPlantNumber(edi945.getTruckPlantNumber());
        if (ediWzCancels.isEmpty()) {
            EdiWzCancel ediWzCancel = new EdiWzCancel();
            ediWzCancel.setStatus(0);
            ediWzCancel.setTrackno(edi945.getCtTracking());
            ediWzCancel.setDeviceid(edi945.getTruckPlantNumber());
            ediWzCancel.setTrackendtime(DateUtil.format(new Date(), DateUtil.NORM_DATETIME_PATTERN));
            ediWzCancelMapper.insertSelective(ediWzCancel);
        } else {
            EdiWzCancel ediWzCancel = ediWzCancels.get(0);
            ediWzCancel.setStatus(0);
            ediWzCancel.setTrackno(edi945.getCtTracking());
            ediWzCancel.setDeviceid(edi945.getTruckPlantNumber());
            ediWzCancel.setTrackendtime(DateUtil.format(new Date(), DateUtil.NORM_DATETIME_PATTERN));
            ediWzCancelMapper.updateByPrimaryKey(ediWzCancel);
        }
        edi945.setGpsState(1);
        edi945.setTrackEndTime(new Date());
    }

    @LogAnnotation
    @PutMapping("editTruck")
    @ApiOperation(value = "修改EDI945 Truck")
    public void editTruck(@RequestBody EditTruckDTO editTruckDTO) {
        setEditTruckRegion(editTruckDTO);
        edi945Mapper.editTruckBy(editTruckDTO);
    }

    @LogAnnotation
    @PutMapping("editTruckOEM")
    @ApiOperation(value = "修改OEM Truck")
    public void editTruckOEM(@RequestBody EditTruckDTO editTruckDTO) {
        setEditTruckRegion(editTruckDTO);
        edi945Mapper.editTruckByOEM(editTruckDTO);
    }

    @LogAnnotation
    @PutMapping("editTruckICT")
    @ApiOperation(value = "修改ICT Truck")
    public void editTruckICT(@RequestBody EditTruckDTO editTruckDTO) {
        setEditTruckRegion(editTruckDTO);
        edi945Mapper.editTruckByICT(editTruckDTO);
    }

    private void setEditTruckRegion(@RequestBody EditTruckDTO editTruckDTO) {
        String region = editTruckDTO.getRegionSub();
        if (StringUtils.isNotBlank(region)) {
            List<Dict> dicts = dictDao.listByType(region);
            String collect = dicts.stream().map(Dict::getK).collect(Collectors.joining(","));
            String[] split = collect.split(",");
            editTruckDTO.setRegion(split);
        }
    }

    @LogAnnotation
    @PostMapping("importTruck")
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
                EditTruckDTO editTruckDTO = getEditTruckDTO(xssfRow);
                edi945Mapper.editTruckBy(editTruckDTO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        FileInfo fileInfo = getFileInfo(file);
        return fileInfo;
    }

    @LogAnnotation
    @PostMapping("importTruckOEM")
    @ApiOperation(value = "OEM文件上传")
    public FileInfo importTruckOEM(MultipartFile file) throws IOException {
        checkFileName(file);
        // 读取Excel
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0); //获取文件的第一个sheet
        int rows = sheet.getPhysicalNumberOfRows();
        for (int r = 1; r < rows; r++) {
            XSSFRow xssfRow = sheet.getRow(r);  //获取sheet的第一行
            try {
                EditTruckDTO editTruckDTO = getEditTruckDTO(xssfRow);
                edi945Mapper.editTruckByOEM(editTruckDTO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        FileInfo fileInfo = getFileInfo(file);
        return fileInfo;
    }

    @LogAnnotation
    @PostMapping("importTruckICT")
    @ApiOperation(value = "ICT文件上传")
    public FileInfo importTruckICT(MultipartFile file) throws IOException {
        checkFileName(file);
        // 读取Excel
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0); //获取文件的第一个sheet
        int rows = sheet.getPhysicalNumberOfRows();
        for (int r = 1; r < rows; r++) {
            XSSFRow xssfRow = sheet.getRow(r);  //获取sheet的第一行
            try {
                EditTruckDTO editTruckDTO = getEditTruckDTO(xssfRow);
                edi945Mapper.editTruckByICT(editTruckDTO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        FileInfo fileInfo = getFileInfo(file);
        return fileInfo;
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

    private EditTruckDTO getEditTruckDTO(XSSFRow xssfRow) throws Exception {
        String shipDate = ExcelUtil.getCellValue(xssfRow.getCell(0));
        String shipmentNumber = ExcelUtil.getCellValue(xssfRow.getCell(1));
        String fwd = ExcelUtil.getCellValue(xssfRow.getCell(2));
        String fwdCode = ExcelUtil.getCellValue(xssfRow.getCell(3));
        String driverName = ExcelUtil.getCellValue(xssfRow.getCell(4));
        String cellular = ExcelUtil.getCellValue(xssfRow.getCell(5));
        String truckPlantNumber = ExcelUtil.getCellValue(xssfRow.getCell(6));
        String ctTracking = ExcelUtil.getCellValue(xssfRow.getCell(7));
        String gpsDevice = ExcelUtil.getCellValue(xssfRow.getCell(8));
        EditTruckDTO editTruckDTO = new EditTruckDTO();
        editTruckDTO.setShipDateSub(shipDate);
        editTruckDTO.setShipmentNumberSub(shipmentNumber);
        editTruckDTO.setFwd(fwd);
        editTruckDTO.setFwdCode(fwdCode);
        editTruckDTO.setDriverName(driverName);
        editTruckDTO.setCellular(cellular);
        editTruckDTO.setTruckPlantNumber(truckPlantNumber);
        editTruckDTO.setCtTracking(ctTracking);
        editTruckDTO.setGpsDevice(gpsDevice);
        return editTruckDTO;
    }

    @PostMapping("exportEDI945")
    @ApiOperation(value = "导出EDI945数据")
    public void exportEDI945(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = Maps.newHashMap();
        String shipDate = getShipDateAndHandleParams(request, params);
        String exportType = request.getParameter("exportType");
        if ("945".equals(exportType)) {
            List<Object[]> data = new ArrayList<>();
            List<EDI945> edi945List = edi945Mapper.list(params, 0, 9999);
            export945(response, data, edi945List);
        }
        if ("214".equals(exportType)) {
            List<Object[]> data = new ArrayList<>();
            List<EDI945> edi945List = edi945Mapper.list(params, 0, 9999);
            export214(response, shipDate, data, edi945List);
        }
    }

    @PostMapping("exportOEM")
    @ApiOperation(value = "导出OEM数据")
    public void exportOEM(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = Maps.newHashMap();
        String shipDate = getShipDateAndHandleParams(request, params);
        String exportType = request.getParameter("exportType");
        if ("945".equals(exportType)) {
            List<Object[]> data = new ArrayList<>();
            List<EDI945> edi945List = edi945Mapper.listOEM(params, 0, 9999);
            export945(response, data, edi945List);
        }
        if ("214".equals(exportType)) {
            List<Object[]> data = new ArrayList<>();
            List<EDI945> edi945List = edi945Mapper.listOEM(params, 0, 9999);
            export214(response, shipDate, data, edi945List);
        }
    }

    @PostMapping("exportICT")
    @ApiOperation(value = "导出ICT数据")
    public void exportICT(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = Maps.newHashMap();
        String shipDate = getShipDateAndHandleParams(request, params);
        String exportType = request.getParameter("exportType");
        if ("945".equals(exportType)) {
            List<Object[]> data = new ArrayList<>();
            List<EDI945> edi945List = edi945Mapper.listICT(params, 0, 9999);
            export945(response, data, edi945List);
        }
        if ("214".equals(exportType)) {
            List<Object[]> data = new ArrayList<>();
            List<EDI945> edi945List = edi945Mapper.listICT(params, 0, 9999);
            export214(response, shipDate, data, edi945List);
        }
    }

    private String getShipDateAndHandleParams(HttpServletRequest request, Map<String, Object> params) {
        String shipmentNumber = request.getParameter("shipmentNumberForm");
        String shipway = request.getParameter("shipwayForm");
        String oem = request.getParameter("oemForm");
        String gateway = request.getParameter("gatewayForm");
        String region = request.getParameter("regionForm");
        putRegion(params, region);
        String truckPlantNumber = request.getParameter("truckPlantNumberForm");
        String sender = request.getParameter("senderForm");
        String shipDate = request.getParameter("shipDateForm");
        params.put("shipmentNumber", shipmentNumber);
        params.put("shipway", shipway);
        params.put("oem", oem);
        params.put("gateway", gateway);
        params.put("truckPlantNumber", truckPlantNumber);
        params.put("sender", sender);
        params.put("shipDate", shipDate);
        return shipDate;
    }

    private void export214(HttpServletResponse response, String shipDate, List<Object[]> data, List<EDI945> edi945List) {
        for (EDI945 edi945 : edi945List) {
            List<String> regionTypeByK = dictDao.getRegionTypeByK(edi945.getPoeCountry());
            if (!regionTypeByK.isEmpty()) {
                edi945.setRegion(regionTypeByK.get(0));
            }
            EDI214ExportExcelDTO excelDTO = new EDI214ExportExcelDTO();
            if (edi945.getRegion().equals("APAC")) {
                excelDTO.setRegion("PAC");
            } else if (edi945.getRegion().equals("EMEIA")) {
                excelDTO.setRegion("EMEA");
            } else if (edi945.getRegion().equals("AMR")) {
                excelDTO.setRegion("AMR");
            } else {
                excelDTO.setReason("");
            }
            if (edi945.getSender().equals("HHUZZ-000295A7P")
                    || edi945.getSender().equals("HHEZZ-000295A7P")
                    || edi945.getSender().equals("HHAZZ-000295A7P")
                    || edi945.getSender().equals("HHETY-000295AJP")
                    || edi945.getSender().equals("HHEGL-00029584P")
                    || edi945.getSender().equals("HHAGL-00029584P")
                    || edi945.getSender().equals("HHUTY-000295AJP")
                    || edi945.getSender().equals("HHETY-000295AJP")
                    || edi945.getSender().equals("HHATY-000295AJP")
                    || edi945.getSender().equals("ICTJX")) {
                excelDTO.setDivison("FG");
            } else if (edi945.getSender().equals("HHGL-HUBACP")
                    || edi945.getSender().equals("HHZZ-HUBACP")
                    || edi945.getSender().equals("HHTY-HUBACP")
                    || edi945.getSender().equals("ICTJXACP")) {
                excelDTO.setDivison("AC");
            } else {
                excelDTO.setDivison("");
            }
            excelDTO.setTrackingNumber(edi945.getTrackingNumber());
            excelDTO.setBl("");
            excelDTO.setScac("CTSC");
            excelDTO.setWeightQual("N Chargeable Weight");
            excelDTO.setWeightType("K Kilograms");
            excelDTO.setWeight(edi945.getGw());
            excelDTO.setEvent("");
            excelDTO.setReason("");
            excelDTO.setDate(shipDate);
            excelDTO.setTime("");
            excelDTO.setTimeCode("08");
            excelDTO.setCity("");
            excelDTO.setState("");
            excelDTO.setCountry("CN");
            if (edi945.getShipway().equals("DIRECT")) {
                excelDTO.setPoid(edi945.getDn());
            } else if (edi945.getShipway().equals("STO")) {
                excelDTO.setPoid(edi945.getDn());
            } else if (edi945.getShipway().equals("HUB")) {
                excelDTO.setPoid(edi945.getShipmentNumber());
            } else {
                excelDTO.setPoid("");
            }
            excelDTO.setTruckPlantNumber(edi945.getTruckPlantNumber());
            excelDTO.setGps(edi945.getCtTracking());
            excelDTO.setHawb(edi945.getWaybill());
            excelDTO.setTransportMode("LCL");
            excelDTO.setDriverName(edi945.getDriverName());
            excelDTO.setCellualr(edi945.getCellular());
            excelDTO.setEarliestEstimatedDate("");
            excelDTO.setEarliestEstimatedTime("");
            excelDTO.setCarrier(edi945.getFwd());
            excelDTO.setCarriersScac(edi945.getFwdCode());
            excelDTO.setWarehouse("");
            excelDTO.setWarehouseScac("");
            excelDTO.setShipTo("");
            excelDTO.setShipToScac("");
            excelDTO.setAirfreightNo("");
            excelDTO.setLongitude("");
            excelDTO.setLatitude("");
            excelDTO.setAppointmentStatusCode("");
            excelDTO.setAppointmentStatus("");
            excelDTO.setCarrierScac("");
            excelDTO.setEquipNo("");
            excelDTO.setEquipDigit("");
            excelDTO.setExcepCode("");
            excelDTO.setExcepPackage("");
            excelDTO.setExcepQty("");
            excelDTO.setAccStatus("");
            String dispose997 = edi945.getDispose997();
            if ("1".equals(dispose997)) {
                excelDTO.setDispose997("已发送");
            } else if ("0".equals(dispose997)) {
                excelDTO.setDispose997("未发送");
            }

            data.add(excelDTO.toString().split(","));
        }
        String headerStr = ",Region," +
                "Divison," +
                "Tracking Number," +
                "BL," +
                "SCAC," +
                "Weight Qual," +
                "Weight Type," +
                "Weight," +
                "Event," +
                "Reason," +
                "Date," +
                "Time," +
                "Time Code," +
                "City," +
                "State," +
                "Country," +
                "PO/ID," +
                "Truck Plant Number," +
                "GPS," +
                "HAWB," +
                "Transport Mode," +
                "Driver Name," +
                "Cellualr," +
                "Earliest Estimated Date," +
                "Earliest Estimated Time," +
                "Carrier," +
                "Carriers SCAC," +
                "Warehouse," +
                "Warehouse SCAC," +
                "Ship To," +
                "Ship to SCAC," +
                "Airfreight No," +
                "Longitude," +
                "Latitude," +
                "Appointment Status Code," +
                "Appointment Status," +
                "Carrier SCAC," +
                "Equip No," +
                "Equip Digit," +
                "Excep Code," +
                "Excep Package," +
                "Excep QTY," +
                "ACC Status," +
                "Dispose 997";
        String[] headers = headerStr.split(",");
        String fileName = MessageFormat.format("{0}_{1}", "214", DateUtil.format(new Date(), DateUtil.NORM_DATE_TIME_PATTERN_TWO));
        ExcelUtil.excelExport2(fileName, "214 template", headers, data, response);
    }

    private void export945(HttpServletResponse response, List<Object[]> data, List<EDI945> edi945List) {
        for (EDI945 edi945 : edi945List) {
            setSenderAndRegion(edi945);
            EDI945ExportExcelDTO excelDTO = new EDI945ExportExcelDTO();
            BeanUtils.copyProperties(edi945, excelDTO);
            data.add(excelDTO.toString().split(","));
        }
        String headerStr = ",Ship Date," +
                "Actual Date," +
                "Sender," +
                "Tracking Number," +
                "PO/DN," +
                "Shipment Number," +
                "Waybill," +
                "Ship Way," +
                "FWD," +
                "FWD Code," +
                "OEM," +
                "Gateway," +
                "CTNS," +
                "Units," +
                "GW," +
                "Ship Mode," +
                "POE," +
                "POE Country," +
                "Region," +
                "Driver Name," +
                "Cellular," +
                "Truck Plant Number," +
                "CT Tracking," +
                "GPS Device";
        String[] headers = headerStr.split(",");
        String fileName = MessageFormat.format("{0}-{1}", "EDI945", DateUtil.format(new Date(), DateUtil.NORM_DATE_TIME_PATTERN_TWO));
        ExcelUtil.excelExport2(fileName, null, headers, data, response);
    }

    @GetMapping("listShipperHHC")
    @ApiOperation(value = "HHC shipper管理列表")
    public PageTableResponse listShipperHHC(PageTableRequest request) {
        extractedRegion(request);
        return new PageTableHandler(new CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return edi945Mapper.count(request.getParams());
            }
        }, new ListHandler() {
            @Override
            public List<EDI945> list(PageTableRequest request) {
                List<EDI945> edi945List = edi945Mapper.list(request.getParams(), request.getOffset(), request.getLimit());
                return getList(edi945List, "iHub");
            }
        }).handle(request);
    }

    @GetMapping("listShipperICT")
    @ApiOperation(value = "ICT shipper管理列表")
    public PageTableResponse listShipperICT(PageTableRequest request) {
        extractedRegion(request);
        return new PageTableHandler(new CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return edi945Mapper.countICT(request.getParams());
            }
        }, new ListHandler() {
            @Override
            public List<EDI945> list(PageTableRequest request) {
                List<EDI945> edi945List = edi945Mapper.listICT(request.getParams(), request.getOffset(), request.getLimit());
                return getList(edi945List, "ICT");
            }
        }).handle(request);
    }

    @GetMapping("listShipperOEM")
    @ApiOperation(value = "OEM shipper管理列表")
    public PageTableResponse listShipperOEM(PageTableRequest request) {
        extractedRegion(request);
        return new PageTableHandler(new CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return edi945Mapper.countOEM(request.getParams());
            }
        }, new ListHandler() {
            @Override
            public List<EDI945> list(PageTableRequest request) {
                List<EDI945> edi945List = edi945Mapper.listOEM(request.getParams(), request.getOffset(), request.getLimit());
                return getList(edi945List, "OEM");
            }
        }).handle(request);
    }

    @PostMapping("send997Info")
    @ApiOperation(value = "发送997数据")
    public Integer send997Info(@RequestBody Send997InfoDTO send997Info) {
        Map<String, Object> params = convert(send997Info);
        List<EDI945> edi945List = edi945Mapper.getSend997Data(params, 0, 9999);
        for (EDI945 edi945 : edi945List) {
            try {
                FieldIhub997 fieldIhub997 = new FieldIhub997();
                if (StringUtils.isNotBlank(edi945.getShipDate())
                        && StringUtils.isNotBlank(edi945.getTrackingNumber())
                        && StringUtils.isNotBlank(edi945.getDn())
                        && StringUtils.isNotBlank(edi945.getShipmentNumber())
                        && StringUtils.isNotBlank(edi945.getWaybill())
                        && StringUtils.isNotBlank(edi945.getShipway())
                        && StringUtils.isNotBlank(edi945.getGateway())
                        && StringUtils.isNotBlank(edi945.getCtns())
                        && StringUtils.isNotBlank(edi945.getUnits())
                        && StringUtils.isNotBlank(edi945.getGw())
                        && StringUtils.isNotBlank(edi945.getPoe())
                        && StringUtils.isNotBlank(edi945.getPoeCountry())) {
                    fieldIhub997.setTransationrp("A");
                    fieldIhub997.setFuctionalrp("A");
                    edi945.setTransationrp("A");
                    edi945.setFuctionalrp("A");
                } else if (StringUtils.isBlank(edi945.getShipDate())
                        && StringUtils.isBlank(edi945.getTrackingNumber())
                        && StringUtils.isBlank(edi945.getDn())
                        && StringUtils.isBlank(edi945.getShipmentNumber())
                        && StringUtils.isBlank(edi945.getWaybill())
                        && StringUtils.isBlank(edi945.getShipway())
                        && StringUtils.isBlank(edi945.getGateway())
                        && StringUtils.isBlank(edi945.getCtns())
                        && StringUtils.isBlank(edi945.getUnits())
                        && StringUtils.isBlank(edi945.getGw())
                        && StringUtils.isBlank(edi945.getPoe())
                        && StringUtils.isBlank(edi945.getPoeCountry())) {
                    fieldIhub997.setTransationrp("R");
                    fieldIhub997.setFuctionalrp("R");
                    edi945.setTransationrp("R");
                    edi945.setFuctionalrp("R");
                } else {
                    fieldIhub997.setTransationrp("R");
                    fieldIhub997.setFuctionalrp("P");
                    edi945.setTransationrp("R");
                    edi945.setFuctionalrp("P");
                }
                fieldIhub997.setStatus(0);
                fieldIhub997.setOrimessageid(edi945.getMessageid());
                fieldIhub997.setFareceiverid(edi945.getInreceiverid());
                fieldIhub997.setFasenderid(edi945.getInsenderid());
                fieldIhub997.setAppreceivercode(edi945.getAppreceivercode());
                fieldIhub997.setOrisenderid(edi945.getOrisenderid());
                fieldIhub997.setControlnumber(edi945.getControlnumber());
                fieldIhub997Mapper.insertSelective(fieldIhub997);
                // 推送完毕后标记为已推送
                edi945.setDispose997("1");
                edi945Mapper.updateDispose997(edi945);
            } catch (Exception e) {
                // 推送完毕后标记为已推送
                edi945.setDispose997("1");
                edi945Mapper.updateDispose997(edi945);
                e.printStackTrace();
            }
        }
        return 1;
    }

    private List<EDI945> getList(List<EDI945> edi945List, String listType) {
        for (EDI945 edi945 : edi945List) {
            if (StringUtils.equals(listType, "iHub")) {
                List<EDI945> ediGps = edi945Mapper.selectGPSByGpsDevice(edi945.getGpsDevice());
                if (null != ediGps && !ediGps.isEmpty()) {
                    edi945.setGpsState(ediGps.get(0).getGpsState());
                }
            } else if (StringUtils.equals(listType, "ICT")) {
                List<EDI945> ediGps = edi945Mapper.selectGPSByGpsDeviceICT(edi945.getGpsDevice());
                if (null != ediGps && !ediGps.isEmpty()) {
                    edi945.setGpsState(ediGps.get(0).getGpsState());
                }
            } else if (StringUtils.equals(listType, "OEM")) {
                List<EDI945> ediGps = edi945Mapper.selectGPSByGpsDeviceOEM(edi945.getGpsDevice());
                if (null != ediGps && !ediGps.isEmpty()) {
                    edi945.setGpsState(ediGps.get(0).getGpsState());
                }
            }
            setSenderAndRegion(edi945);
        }
        return edi945List;
    }

    private void setSenderAndRegion(EDI945 edi945) {
        List<String> regionTypeByK = dictDao.getRegionTypeByK(edi945.getPoeCountry());
        if (!regionTypeByK.isEmpty()) {
            edi945.setRegion(regionTypeByK.get(0));
        }
        if (edi945.getSender().equals("HHUZZ-000295A7P")
                || edi945.getSender().equals("HHEZZ-000295A7P")
                || edi945.getSender().equals("HHAZZ-000295A7P")
                || edi945.getSender().equals("HHETY-000295AJP")
                || edi945.getSender().equals("HHEGL-00029584P")
                || edi945.getSender().equals("HHAGL-00029584P")
                || edi945.getSender().equals("HHUTY-000295AJP")
                || edi945.getSender().equals("HHETY-000295AJP")
                || edi945.getSender().equals("HHATY-000295AJP")
                || edi945.getSender().equals("ICTJX")) {
            edi945.setSender("FG");
        } else if (edi945.getSender().equals("HHGL-HUBACP")
                || edi945.getSender().equals("HHZZ-HUBACP")
                || edi945.getSender().equals("HHTY-HUBACP")
                || edi945.getSender().equals("ICTJXACP")) {
            edi945.setSender("AC");
        }
    }

    private void extractedRegion(PageTableRequest request) {
        Map<String, Object> params = request.getParams();
        String region = (String) params.get("region");
        putRegion(params, region);
    }

    private void putRegion(Map<String, Object> params, String region) {
        if (StringUtils.isNotBlank(region)) {
            List<Dict> dicts = dictDao.listByType(region);
            String collect = dicts.stream().map(Dict::getK).collect(Collectors.joining(","));
            String[] split = collect.split(",");
            params.put("region", split);
        }
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

}
