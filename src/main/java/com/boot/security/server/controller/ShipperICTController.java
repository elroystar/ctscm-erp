package com.boot.security.server.controller;

import com.boot.security.server.annotation.LogAnnotation;
import com.boot.security.server.dao.EDIDetailICT2020Mapper;
import com.boot.security.server.dao.EDIHeadingICT2020Mapper;
import com.boot.security.server.dao.EDIHeadingUpdateICTMapper;
import com.boot.security.server.dao.EDIManICT2020Mapper;
import com.boot.security.server.model.*;
import com.boot.security.server.page.table.PageTableHandler;
import com.boot.security.server.page.table.PageTableRequest;
import com.boot.security.server.page.table.PageTableResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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

}
