package com.boot.security.server.controller;

import com.boot.security.server.annotation.LogAnnotation;
import com.boot.security.server.dao.*;
import com.boot.security.server.dto.NoticeReadVO;
import com.boot.security.server.dto.NoticeVO;
import com.boot.security.server.dto.ShipperDetailDTO;
import com.boot.security.server.model.EDIHeadingOEM2020;
import com.boot.security.server.model.EDIHeadingUpdate;
import com.boot.security.server.model.Notice;
import com.boot.security.server.model.SysUser;
import com.boot.security.server.page.table.PageTableHandler;
import com.boot.security.server.page.table.PageTableHandler.CountHandler;
import com.boot.security.server.page.table.PageTableHandler.ListHandler;
import com.boot.security.server.page.table.PageTableRequest;
import com.boot.security.server.page.table.PageTableResponse;
import com.boot.security.server.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Api(tags = "日志")
@RestController
@RequestMapping("/shipper")
public class ShipperController {

    @Autowired
    private NoticeDao noticeDao;

    @Autowired
    private EDIHeadingOEM2020Mapper ediHeadingOEM2020Mapper;

    @Autowired
    private EDIDetailOEM2020Mapper ediDetailOEM2020Mapper;

    @Autowired
    private EDIHeadingUpdateMapper ediHeadingUpdateMapper;

    @Autowired
    private EDIManOEM2020Mapper ediManOEM2020Mapper;

    @LogAnnotation
    @PostMapping
    @ApiOperation(value = "保存公告")
    @PreAuthorize("hasAuthority('notice:add')")
    public Notice saveNotice(@RequestBody Notice notice) {
        noticeDao.save(notice);

        return notice;
    }

    @GetMapping("/HHC/{id}")
    @ApiOperation(value = "根据id HHC shipper")
    public EDIHeadingOEM2020 getShipperHHC(@PathVariable Long id) {
        EDIHeadingOEM2020 headingOEM2020 = ediHeadingOEM2020Mapper.selectByPrimaryKey(Long.bitCount(id));
        if ("".equals(headingOEM2020.getCtns()) || null == headingOEM2020.getCtns()) {
            String CTNs = ediDetailOEM2020Mapper.selectCTNsByHeadingId(headingOEM2020.getId());
            headingOEM2020.setCtns(CTNs);
        }
        if ("".equals(headingOEM2020.getQty()) || null == headingOEM2020.getQty()) {
            String QTY = ediDetailOEM2020Mapper.selectQTYByHeadingId(headingOEM2020.getId());
            headingOEM2020.setQty(QTY);
        }
        if ("".equals(headingOEM2020.getFactoryWeight()) || null == headingOEM2020.getFactoryWeight()) {
            String factoryWeight = ediDetailOEM2020Mapper.selectFactoryWeightByHeadingId(headingOEM2020.getId());
            headingOEM2020.setFactoryWeight(factoryWeight);
        }
        return headingOEM2020;
    }

    @GetMapping(params = "id")
    public NoticeVO readNotice(Long id) {
        NoticeVO vo = new NoticeVO();

        Notice notice = noticeDao.getById(id);
        if (notice == null || notice.getStatus() == Notice.Status.DRAFT) {
            return vo;
        }
        vo.setNotice(notice);

        noticeDao.saveReadRecord(notice.getId(), UserUtil.getLoginUser().getId());

        List<SysUser> users = noticeDao.listReadUsers(id);
        vo.setUsers(users);

        return vo;
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
                            String factoryWeight = ediDetailOEM2020Mapper.selectFactoryWeightByHeadingId(headingOEM2020.getId());
                            headingOEM2020.setFactoryWeight(factoryWeight);
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
                List<ShipperDetailDTO> shipperDetailDTOS = ediManOEM2020Mapper.list(params, isCountry, request.getOffset(), request.getLimit());
                return shipperDetailDTOS;
            }
        }).handle(request);
    }

    @LogAnnotation
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除公告")
    @PreAuthorize("hasAuthority('notice:del')")
    public void delete(@PathVariable Long id) {
        noticeDao.delete(id);
    }

    @ApiOperation(value = "未读公告数")
    @GetMapping("/count-unread")
    public Integer countUnread() {
        SysUser user = UserUtil.getLoginUser();
        return noticeDao.countUnread(user.getId());
    }

    @GetMapping("/published")
    @ApiOperation(value = "公告列表")
    public PageTableResponse listNoticeReadVO(PageTableRequest request) {
        request.getParams().put("userId", UserUtil.getLoginUser().getId());

        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return noticeDao.countNotice(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<NoticeReadVO> list(PageTableRequest request) {
                return noticeDao.listNotice(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }
}
