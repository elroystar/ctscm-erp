package com.boot.security.server.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.boot.security.server.annotation.LogAnnotation;
import com.boot.security.server.dao.EDIDetailOEM2020Mapper;
import com.boot.security.server.dao.EDIHeadingOEM2020Mapper;
import com.boot.security.server.dao.EDIHeadingUpdateMapper;
import com.boot.security.server.dao.EDIManOEM2020Mapper;
import com.boot.security.server.dto.EDIExportExcelDTO;
import com.boot.security.server.dto.ShipperDetailDTO;
import com.boot.security.server.model.EDIHeadingOEM2020;
import com.boot.security.server.model.EDIHeadingUpdate;
import com.boot.security.server.page.table.PageTableHandler;
import com.boot.security.server.page.table.PageTableHandler.CountHandler;
import com.boot.security.server.page.table.PageTableHandler.ListHandler;
import com.boot.security.server.page.table.PageTableRequest;
import com.boot.security.server.page.table.PageTableResponse;
import com.boot.security.server.utils.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Api(tags = "日志")
@RestController
@RequestMapping("/shipper")
public class ShipperController {

    @Autowired
    private EDIHeadingOEM2020Mapper ediHeadingOEM2020Mapper;

    @Autowired
    private EDIDetailOEM2020Mapper ediDetailOEM2020Mapper;

    @Autowired
    private EDIHeadingUpdateMapper ediHeadingUpdateMapper;

    @Autowired
    private EDIManOEM2020Mapper ediManOEM2020Mapper;

    @GetMapping("/HHC/{id}")
    @ApiOperation(value = "根据id HHC shipper")
    public EDIHeadingOEM2020 getShipperHHC(@PathVariable Integer id) {
        EDIHeadingOEM2020 headingOEM2020 = getEdiHeadingOEM2020(id);
        return headingOEM2020;
    }

    @LogAnnotation
    @PutMapping("updateShipperHHC")
    @ApiOperation(value = "修改HHC shipper")
    public EDIHeadingOEM2020 updateShipperHHC(@RequestBody EDIHeadingOEM2020 headingOEM2020) {
        if (!"".equals(headingOEM2020.getTotalPallet()) && null != headingOEM2020.getTotalPallet()) {
            ediHeadingOEM2020Mapper.updateByPrimaryKeySelective(headingOEM2020);
        }
        List<EDIHeadingUpdate> ediHeadingUpdates = ediHeadingUpdateMapper.selectByHid(headingOEM2020.getId(), headingOEM2020.getShipper());
        if (ediHeadingUpdates.isEmpty()) {
            EDIHeadingUpdate update = new EDIHeadingUpdate();
            update.setCreateTime(new Date());
            update.setHid(headingOEM2020.getId());
            update.setShipper(headingOEM2020.getShipper());
            update.setCtns(headingOEM2020.getCtns());
            update.setQty(headingOEM2020.getQty());
            update.setFactoryWeight(headingOEM2020.getFactoryWeight());
            ediHeadingUpdateMapper.insertSelective(update);
        } else {
            EDIHeadingUpdate update = ediHeadingUpdates.get(0);
            update.setCtns(headingOEM2020.getCtns());
            update.setQty(headingOEM2020.getQty());
            update.setFactoryWeight(headingOEM2020.getFactoryWeight());
            ediHeadingUpdateMapper.updateByPrimaryKey(update);
        }
        return headingOEM2020;
    }

    @LogAnnotation
    @PostMapping("exportExcelHHC")
    @ApiOperation(value = "导出HHC数据")
    public void exportExcelHHC(EDIExportExcelDTO exportExcelDTO, HttpServletResponse response) {
        Integer isCountry = null;
        String region = exportExcelDTO.getRegion();
        if (StringUtils.isNotBlank(region)) {
            Object country = exportExcelDTO.getCountry();
            if (country instanceof String) {
                if (StringUtils.isNotBlank((String) country)) {
                    isCountry = 1;
                }
            }
            if (country instanceof ArrayList) {
                isCountry = 0;
            }
        }
        Map<String, Object> params = (Map<String, Object>) JSONObject.parse(JSON.toJSONString(exportExcelDTO));
        EDIHeadingOEM2020 headingOEM2020 = getEdiHeadingOEM2020(exportExcelDTO.getHid());
        List<ShipperDetailDTO> shipperDetailDTOS = ediManOEM2020Mapper.listAll(params, isCountry);
        List<Object[]> data = new ArrayList<>();
        switch (headingOEM2020.getShipmentway()) {
            case "DIRECT":
                for (ShipperDetailDTO detailDTO : shipperDetailDTOS) {
                    detailDTO.setTrackingNo(detailDTO.getDn());
                    setExportData(exportExcelDTO, detailDTO, headingOEM2020);
                    data.add(detailDTO.toString().split(","));
                }
                break;
            case "STO":
                for (ShipperDetailDTO detailDTO : shipperDetailDTOS) {
                    detailDTO.setTrackingNo(detailDTO.getDn());
                    setExportData(exportExcelDTO, detailDTO, headingOEM2020);
                    data.add(detailDTO.toString().split(","));
                }
                break;
            case "HUB":
                for (ShipperDetailDTO detailDTO : shipperDetailDTOS) {
                    detailDTO.setTrackingNo(detailDTO.getHawb());
                    setExportData(exportExcelDTO, detailDTO, headingOEM2020);
                    data.add(detailDTO.toString().split(","));
                }
                break;
        }
        String headerStr = "id,country,PO/DN,region,trackingNo,状态,原因,日期,时间,地区,城市,国别,ctns,qty,factoryWeight";
        String[] headers = headerStr.split(",");
        ExcelUtil.excelExport2("detail", headers, data, response);
    }

    private void setExportData(EDIExportExcelDTO exportExcelDTO, ShipperDetailDTO detailDTO, EDIHeadingOEM2020 headingOEM2020) {
        detailDTO.setState(exportExcelDTO.getState());
        detailDTO.setReason(exportExcelDTO.getReason());
        detailDTO.setDate(exportExcelDTO.getDate());
        detailDTO.setTime(exportExcelDTO.getTime());
        detailDTO.setZone(exportExcelDTO.getZone());
        detailDTO.setCity(exportExcelDTO.getCity());
        detailDTO.setGuobie(exportExcelDTO.getGuobie());
        detailDTO.setCtns(headingOEM2020.getCtns());
        detailDTO.setQty(headingOEM2020.getQty());
        detailDTO.setFactoryWeight(headingOEM2020.getFactoryWeight());
    }

    @GetMapping("listShipperHHC")
    @ApiOperation(value = "HHC shipper管理列表")
    public PageTableResponse listShipperHHC(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return ediHeadingOEM2020Mapper.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<EDIHeadingOEM2020> list(PageTableRequest request) {
                List<EDIHeadingOEM2020> headingOEM2020List = ediHeadingOEM2020Mapper.list(request.getParams(), request.getOffset(), request.getLimit());
                if (!headingOEM2020List.isEmpty()) {
                    for (EDIHeadingOEM2020 headingOEM2020 : headingOEM2020List) {
                        if ("".equals(headingOEM2020.getCtns()) || null == headingOEM2020.getCtns()) {
                            String CTNs = ediDetailOEM2020Mapper.selectCTNsByHeadingId(headingOEM2020.getId());
                            headingOEM2020.setCtns(CTNs);
                        }
                        if ("".equals(headingOEM2020.getQty()) || null == headingOEM2020.getQty()) {
                            String QTY = ediDetailOEM2020Mapper.selectQTYByHeadingId(headingOEM2020.getId());
                            headingOEM2020.setQty(QTY);
                        }
                        if ("".equals(headingOEM2020.getFactoryWeight()) || null == headingOEM2020.getFactoryWeight()) {
                            Double factoryWeight = ediDetailOEM2020Mapper.selectFactoryWeightByHeadingId(headingOEM2020.getId());
                            headingOEM2020.setFactoryWeight(String.format("%.2f", factoryWeight));
                        }
                    }
                }
                return headingOEM2020List;
            }
        }).handle(request);
    }

    @GetMapping("getShipperHHCDetail")
    @ApiOperation(value = "HHC shipper detail列表")
    public PageTableResponse getShipperHHCDetail(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                Integer isCountry = null;
                Map<String, Object> params = request.getParams();
                String region = (String) params.get("region");
                if (StringUtils.isNotBlank(region)) {
                    Object country = params.get("country");
                    if (country instanceof String) {
                        if (StringUtils.isNotBlank((String) country)) {
                            isCountry = 1;
                        }
                    }
                    if (country instanceof ArrayList) {
                        isCountry = 0;
                    }
                }
                return ediManOEM2020Mapper.count(params, isCountry);
            }
        }, new ListHandler() {

            @Override
            public List<ShipperDetailDTO> list(PageTableRequest request) {
                Integer isCountry = null;
                Map<String, Object> params = request.getParams();
                String region = (String) params.get("region");
                if (StringUtils.isNotBlank(region)) {
                    Object country = params.get("country");
                    if (country instanceof String) {
                        if (StringUtils.isNotBlank((String) country)) {
                            isCountry = 1;
                        }
                    }
                    if (country instanceof ArrayList) {
                        isCountry = 0;
                    }
                }
                EDIHeadingOEM2020 headingOEM2020 = getEdiHeadingOEM2020(Integer.parseInt((String) params.get("hid")));
                List<ShipperDetailDTO> shipperDetailDTOS = ediManOEM2020Mapper.list(params, isCountry, request.getOffset(), request.getLimit());
                switch (headingOEM2020.getShipmentway()) {
                    case "DIRECT":
                        for (ShipperDetailDTO detailDTO : shipperDetailDTOS) {
                            detailDTO.setTrackingNo(detailDTO.getDn());
                            detailDTO.setCtns(headingOEM2020.getCtns());
                            detailDTO.setQty(headingOEM2020.getQty());
                            detailDTO.setFactoryWeight(headingOEM2020.getFactoryWeight());
                        }
                        break;
                    case "STO":
                        for (ShipperDetailDTO detailDTO : shipperDetailDTOS) {
                            detailDTO.setTrackingNo(detailDTO.getDn());
                            detailDTO.setCtns(headingOEM2020.getCtns());
                            detailDTO.setQty(headingOEM2020.getQty());
                            detailDTO.setFactoryWeight(headingOEM2020.getFactoryWeight());
                        }
                        break;
                    case "HUB":
                        for (ShipperDetailDTO detailDTO : shipperDetailDTOS) {
                            detailDTO.setTrackingNo(detailDTO.getHawb());
                            detailDTO.setCtns(headingOEM2020.getCtns());
                            detailDTO.setQty(headingOEM2020.getQty());
                            detailDTO.setFactoryWeight(headingOEM2020.getFactoryWeight());
                        }
                        break;
                }
                return shipperDetailDTOS;
            }
        }).handle(request);
    }

    private EDIHeadingOEM2020 getEdiHeadingOEM2020(int hid) {
        EDIHeadingOEM2020 headingOEM2020 = ediHeadingOEM2020Mapper.selectByPrimaryKey(hid);
        if ("".equals(headingOEM2020.getCtns()) || null == headingOEM2020.getCtns()) {
            String CTNs = ediDetailOEM2020Mapper.selectCTNsByHeadingId(headingOEM2020.getId());
            headingOEM2020.setCtns(CTNs);
        }
        if ("".equals(headingOEM2020.getQty()) || null == headingOEM2020.getQty()) {
            String QTY = ediDetailOEM2020Mapper.selectQTYByHeadingId(headingOEM2020.getId());
            headingOEM2020.setQty(QTY);
        }
        if ("".equals(headingOEM2020.getFactoryWeight()) || null == headingOEM2020.getFactoryWeight()) {
            Double factoryWeight = ediDetailOEM2020Mapper.selectFactoryWeightByHeadingId(headingOEM2020.getId());
            headingOEM2020.setFactoryWeight(String.format("%.2f", factoryWeight));
        }
        return headingOEM2020;
    }

}
