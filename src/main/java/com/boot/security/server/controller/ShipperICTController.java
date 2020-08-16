package com.boot.security.server.controller;

import com.boot.security.server.annotation.LogAnnotation;
import com.boot.security.server.dao.*;
import com.boot.security.server.dto.ICTExcelConditionDTO;
import com.boot.security.server.model.EDIHeadingICT2020;
import com.boot.security.server.model.EDIHeadingUpdateICT;
import com.boot.security.server.model.EDIManICT2020DTO;
import com.boot.security.server.page.table.PageTableHandler;
import com.boot.security.server.page.table.PageTableRequest;
import com.boot.security.server.page.table.PageTableResponse;
import com.boot.security.server.utils.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;
import java.util.*;

@Api(tags = "日志")
@RestController
@RequestMapping("/shipper")
public class ShipperICTController {

    @Autowired
    private EDIManICT2020Mapper ediManICT2020Mapper;

    @Autowired
    private EDIDetailICT2020Mapper ediDetailICT2020Mapper;

    @Autowired
    private EDIHeadingICT2020Mapper ediHeadingICT2020Mapper;

    @Autowired
    private EDIHeadingUpdateICTMapper ediHeadingUpdateICTMapper;

    @Autowired
    private EDITruckNoICTMapper ediTruckNoICTMapper;

    @GetMapping("listShipperICT")
    @ApiOperation(value = "ICT shipper管理列表")
    public PageTableResponse listShipperICT(PageTableRequest request) {
        return new PageTableHandler(new PageTableHandler.CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return ediManICT2020Mapper.count(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {

            @Override
            public List<EDIManICT2020DTO> list(PageTableRequest request) {
                List<EDIManICT2020DTO> ediManICT2020DTOList = ediManICT2020Mapper.list(request.getParams(), request.getOffset(), request.getLimit());
                assembleEDIManICT(ediManICT2020DTOList);
                return ediManICT2020DTOList;
            }
        }).handle(request);
    }

    @GetMapping("/ICT/{hid}")
    @ApiOperation(value = "根据id ICT shipper")
    public EDIHeadingICT2020 getShipperHHC(@PathVariable Integer hid) {
        EDIHeadingICT2020 ediHeadingICT2020 = ediHeadingICT2020Mapper.selectByPrimaryKey(hid);
        // 计算CTNs、QTY、factoryWeight
        if ("".equals(ediHeadingICT2020.getCtns()) || null == ediHeadingICT2020.getCtns()) {
            String CTNs = ediDetailICT2020Mapper.selectCTNsByHeadingId(hid);
            ediHeadingICT2020.setCtns(CTNs);
        }
        if ("".equals(ediHeadingICT2020.getQty()) || null == ediHeadingICT2020.getQty()) {
            String QTY = ediDetailICT2020Mapper.selectQTYByHeadingId(hid);
            ediHeadingICT2020.setQty(QTY);
        }
        if ("".equals(ediHeadingICT2020.getFactoryWeight()) || null == ediHeadingICT2020.getFactoryWeight()) {
            Double factoryWeight = ediDetailICT2020Mapper.selectFactoryWeightByHeadingId(hid);
            ediHeadingICT2020.setFactoryWeight(String.format("%.2f", factoryWeight));
        }
        return ediHeadingICT2020;
    }

    @LogAnnotation
    @PutMapping("updateShipperICT")
    @ApiOperation(value = "修改ICT shipper")
    public EDIHeadingICT2020 updateShipperHHC(@RequestBody EDIHeadingICT2020 ediHeadingICT2020) {
        if (!"".equals(ediHeadingICT2020.getTotalPallet()) && null != ediHeadingICT2020.getTotalPallet()) {
            ediHeadingICT2020Mapper.updateByPrimaryKeySelective(ediHeadingICT2020);
        }
        List<EDIHeadingUpdateICT> ediHeadingUpdateICTS = ediHeadingUpdateICTMapper.selectByHid(ediHeadingICT2020.getId(), ediHeadingICT2020.getShipper());
        if (ediHeadingUpdateICTS.isEmpty()) {
            EDIHeadingUpdateICT update = new EDIHeadingUpdateICT();
            update.setCreateTime(new Date());
            update.setHid(ediHeadingICT2020.getId());
            update.setShipper(ediHeadingICT2020.getShipper());
            update.setCtns(ediHeadingICT2020.getCtns());
            update.setQty(ediHeadingICT2020.getQty());
            update.setFactoryWeight(ediHeadingICT2020.getFactoryWeight());
            ediHeadingUpdateICTMapper.insertSelective(update);
        } else {
            EDIHeadingUpdateICT update = ediHeadingUpdateICTS.get(0);
            update.setCtns(ediHeadingICT2020.getCtns());
            update.setQty(ediHeadingICT2020.getQty());
            update.setFactoryWeight(ediHeadingICT2020.getFactoryWeight());
            ediHeadingUpdateICTMapper.updateByPrimaryKey(update);
        }
        return ediHeadingICT2020;
    }

    @LogAnnotation
    @PostMapping("exportExcelICT")
    @ApiOperation(value = "导出ICT数据")
    public void exportExcelHHC(ICTExcelConditionDTO ictExcelConditionDTO, HttpServletResponse response) {
        String arrayId = ictExcelConditionDTO.getArrayId();
        String[] exportStatus = ictExcelConditionDTO.getExportStatus().split(",");
        String[] exportReason = ictExcelConditionDTO.getExportReason().split(",");
        String[] exportDate = ictExcelConditionDTO.getExportDate().split(",");
        String[] exportTime = ictExcelConditionDTO.getExportTime().split(",");
        String[] exportZone = ictExcelConditionDTO.getExportZone().split(",");
        String[] exportCity = ictExcelConditionDTO.getExportCity().split(",");
        String[] exportState = ictExcelConditionDTO.getExportState().split(",");
        String[] exportCountry = ictExcelConditionDTO.getExportCountry().split(",");
        List<EDIManICT2020DTO> ediManICT2020DTOList;
        if (StringUtils.isNotBlank(arrayId)) {
            String[] split = arrayId.split(",");
            ediManICT2020DTOList = ediManICT2020Mapper.findByIdArray(split);
        } else {
            Map<String, Object> params = new HashMap<>();
            params.put("actualDate", ictExcelConditionDTO.getActualDate());
            params.put("hawb", ictExcelConditionDTO.getHawb());
            params.put("awb", ictExcelConditionDTO.getAwb());
            params.put("shipmentway", ictExcelConditionDTO.getShipmentway());
            params.put("truckNo", ictExcelConditionDTO.getTruckNo());
            ediManICT2020DTOList = ediManICT2020Mapper.listAll(params);
        }
        assembleEDIManICT(ediManICT2020DTOList);
        List<Object[]> data = new ArrayList<>();
        for (EDIManICT2020DTO dto : ediManICT2020DTOList) {
            for (int i = 0; i < 5; i++) {
                String dataStr = ",{0},,{1},,CTSC,N Chargeable Weight,K Kilograms,{2},{3},{4},{5},{6},{7},{8},{9},{10}";
                String format = MessageFormat.format(dataStr, dto.getRegion(), dto.getDn(), exportStatus[i], exportReason[i], exportDate[i], exportTime[i], exportZone[i], exportCity[i].toUpperCase(), exportState[i].toUpperCase(), exportCountry[i].toUpperCase(), dto.getTrackingNo());
                data.add(format.split(","));
            }
        }
        String headerStr = ",Region,Division,MAN,Bill of loading,SCAC,Weight Qual,Weight Type,Status,Reason,Date,Time,Time Code,City,State,Country,Tracking No";
        String[] headers = headerStr.split(",");
        ExcelUtil.excelExport2("detail", headers, data, response);
    }

    private void assembleEDIManICT(List<EDIManICT2020DTO> ediManICT2020DTOList) {
        if (!ediManICT2020DTOList.isEmpty()) {
            for (EDIManICT2020DTO ediManICT2020DTO : ediManICT2020DTOList) {
                // 计算CTNs、QTY、factoryWeight
                if ("".equals(ediManICT2020DTO.getCtns()) || null == ediManICT2020DTO.getCtns()) {
                    String CTNs = ediDetailICT2020Mapper.selectCTNsByHeadingId(ediManICT2020DTO.getHid());
                    ediManICT2020DTO.setCtns(CTNs);
                }
                if ("".equals(ediManICT2020DTO.getQty()) || null == ediManICT2020DTO.getQty()) {
                    String QTY = ediDetailICT2020Mapper.selectQTYByHeadingId(ediManICT2020DTO.getHid());
                    ediManICT2020DTO.setQty(QTY);
                }
                if ("".equals(ediManICT2020DTO.getFactoryWeight()) || null == ediManICT2020DTO.getFactoryWeight()) {
                    Double factoryWeight = ediDetailICT2020Mapper.selectFactoryWeightByHeadingId(ediManICT2020DTO.getHid());
                    ediManICT2020DTO.setFactoryWeight(String.format("%.2f", factoryWeight));
                }
                // TrackingNo赋值
                String partno = ediManICT2020DTO.getPartno();
                String shipType = ediManICT2020DTO.getShipmentway();
                if (partno.startsWith("M")) {
                    switch (shipType) {
                        case "DIRECT":
                            ediManICT2020DTO.setTrackingNo(ediManICT2020DTO.getDn());
                            break;
                        case "STO":
                            ediManICT2020DTO.setTrackingNo(ediManICT2020DTO.getDn());
                            break;
                        case "HUB":
                            ediManICT2020DTO.setTrackingNo(ediManICT2020DTO.getHawb());
                            break;
                    }
                }
                if (partno.startsWith("P")) {
                    switch (shipType) {
                        case "DIRECT":
                            if (ediManICT2020DTO.getRegion().equals("PAC")) {
                                ediManICT2020DTO.setTrackingNo(ediManICT2020DTO.getDn());
                            }
                            if (ediManICT2020DTO.getRegion().equals("EMEA")) {
                                ediManICT2020DTO.setTrackingNo(ediManICT2020DTO.getDn());
                            }
                            break;
                        case "STO":
                            ediManICT2020DTO.setTrackingNo(ediManICT2020DTO.getDn());
                            break;
                        case "HUB":
                            ediManICT2020DTO.setTrackingNo(ediManICT2020DTO.getHawb());
                            break;
                    }
                }
            }
        }
    }
}
