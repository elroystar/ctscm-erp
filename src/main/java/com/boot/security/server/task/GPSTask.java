package com.boot.security.server.task;

import com.alibaba.fastjson.JSON;
import com.boot.security.server.dao.*;
import com.boot.security.server.dto.gps.FirstVcl;
import com.boot.security.server.dto.gps.TransTimeManageV2;
import com.boot.security.server.model.*;
import com.boot.security.server.service.GPSService;
import com.boot.security.server.utils.DateUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
public class GPSTask {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GPSService gpsService;

    @Autowired
    private EDI945Mapper edi945Mapper;

//    @Autowired
//    private EdiWzDeviceMapper ediWzDeviceMapper;
//
//    @Autowired
//    private EdiWzUploadMapper ediWzUploadMapper;
//
//    @Autowired
//    private EdiWzRegisterMapper ediWzRegisterMapper;
//
//    @Autowired
//    private EdiWzTrackMapper ediWzTrackMapper;
//
//    @Autowired
//    private EdiWzCancelMapper ediWzCancelMapper;

    @Scheduled(cron = "0 */1 * * * ? ")
    public void getTruckPlantNumberGPS() {
        // 定时获取所有需要获取数据的车辆
        List<EDI945> edi945List = edi945Mapper.selectByGPSState(0);
        for (EDI945 edi945 : edi945List) {
            Boolean isFirst = Boolean.FALSE;
            String token = gpsService.login();
            String transTimeManageV2 = gpsService.transTimeManageV2(token, edi945.getGpsDevice());
            if (StringUtils.isBlank(edi945.getLatitude()) && StringUtils.isBlank(edi945.getLongitude())) {
                isFirst = Boolean.TRUE;
            }
            if (StringUtils.isNotBlank(transTimeManageV2)) {
                TransTimeManageV2 transTime = JSON.parseObject(transTimeManageV2, TransTimeManageV2.class);
                if (transTime.getStatus() == 1001) {
                    extracted(edi945, transTime);
                    edi945Mapper.updateByTruckPlantNumber(edi945);
                    edi945Mapper.updateGPSByTruckPlantNumber(edi945);
                    checkFirstTask(edi945, isFirst);
                    // upload表数据写入
                    insertEDIInfo(edi945);
                }
            }
            logger.debug("GPSTask getTruckPlantNumberGPS log, TruckPlantNumber:{}, gps info:{}", edi945.getGpsDevice(), transTimeManageV2);
        }
    }

    @Scheduled(cron = "0 */1 * * * ? ")
    public void getTruckPlantNumberGPSOEM() {
        // 定时获取所有需要获取数据的车辆
        List<EDI945> edi945List = edi945Mapper.selectByGPSStateOEM(0);
        for (EDI945 edi945 : edi945List) {
            Boolean isFirst = Boolean.FALSE;
            String token = gpsService.login();
            String transTimeManageV2 = gpsService.transTimeManageV2(token, edi945.getGpsDevice());
            if (StringUtils.isBlank(edi945.getLatitude()) && StringUtils.isBlank(edi945.getLongitude())) {
                isFirst = Boolean.TRUE;
            }
            if (StringUtils.isNotBlank(transTimeManageV2)) {
                TransTimeManageV2 transTime = JSON.parseObject(transTimeManageV2, TransTimeManageV2.class);
                if (transTime.getStatus() == 1001) {
                    extracted(edi945, transTime);
                    edi945Mapper.updateByTruckPlantNumberOEM(edi945);
                    edi945Mapper.updateGPSByTruckPlantNumberOEM(edi945);
                    checkFirstTask(edi945, isFirst);
                    // upload表数据写入
                    insertEDIInfo(edi945);
                }
            }
            logger.debug("GPSTask getTruckPlantNumberGPSOEM log, TruckPlantNumber:{}, gps info:{}", edi945.getGpsDevice(), transTimeManageV2);
        }
    }

    @Scheduled(cron = "0 */1 * * * ? ")
    public void getTruckPlantNumberGPSICT() {
        // 定时获取所有需要获取数据的车辆
        List<EDI945> edi945List = edi945Mapper.selectByGPSStateICT(0);
        for (EDI945 edi945 : edi945List) {
            Boolean isFirst = Boolean.FALSE;
            String token = gpsService.login();
            String transTimeManageV2 = gpsService.transTimeManageV2(token, edi945.getGpsDevice());
            if (StringUtils.isBlank(edi945.getLatitude()) && StringUtils.isBlank(edi945.getLongitude())) {
                isFirst = Boolean.TRUE;
            }
            if (StringUtils.isNotBlank(transTimeManageV2)) {
                TransTimeManageV2 transTime = JSON.parseObject(transTimeManageV2, TransTimeManageV2.class);
                if (transTime.getStatus() == 1001) {
                    extracted(edi945, transTime);
                    edi945Mapper.updateByTruckPlantNumberICT(edi945);
                    edi945Mapper.updateGPSByTruckPlantNumberICT(edi945);
                    checkFirstTask(edi945, isFirst);
                    insertEDIInfo(edi945);
                }
            }
            logger.debug("GPSTask getTruckPlantNumberGPSICT log, TruckPlantNumber:{}, gps info:{}", edi945.getGpsDevice(), transTimeManageV2);
        }
    }

    private void extracted(EDI945 edi945, TransTimeManageV2 transTime) {
        FirstVcl firstVcl = transTime.getResult().getFirstVcl();
        String utc = firstVcl.getUtc();
        String adr = firstVcl.getAdr();
        String city = firstVcl.getCity();
        String province = firstVcl.getProvince();
        String lat = firstVcl.getLat();
        String lon = firstVcl.getLon();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = Long.parseLong(utc);
        Date date1 = new Date(lt);
        String gpsUpdating = simpleDateFormat.format(date1);
        edi945.setGpsUpdating(gpsUpdating);
        edi945.setPosition(adr);
        edi945.setCity(city);
        edi945.setProvince(province);
        BigDecimal latitude = BigDecimal.valueOf(Long.parseLong(lat)).divide(BigDecimal.valueOf(600000L), 3, BigDecimal.ROUND_HALF_UP);
        BigDecimal longitude = BigDecimal.valueOf(Long.parseLong(lon)).divide(BigDecimal.valueOf(600000L), 3, BigDecimal.ROUND_HALF_UP);
        edi945.setLatitude(latitude.toString());
        edi945.setLongitude(longitude.toString());
        // 如果获取的经纬度与一开始输入的经纬度相等，停止获取数据
        if (edi945.getDesLon().equals(longitude.toString()) && edi945.getDesLat().equals(latitude.toString())) {
            edi945.setGpsState(1);
            edi945.setTrackEndTime(new Date());
            // 取消数据edi表数据写入
            EdiWzCancel ediWzCancel = new EdiWzCancel();
            ediWzCancel.setStatus(0);
            ediWzCancel.setTrackno(edi945.getCtTracking());
            ediWzCancel.setDeviceid(edi945.getTruckPlantNumber());
            ediWzCancel.setTrackendtime(DateUtil.format(edi945.getTrackEndTime(), DateUtil.NORM_DATETIME_PATTERN));
//            ediWzCancelMapper.insertSelective(ediWzCancel);
        }
    }

    private void insertEDIInfo(EDI945 edi945) {
        // upload表数据写入
//        List<EdiWzUpload> ediWzUploads = ediWzUploadMapper.selectBykPlantNumber(edi945.getTruckPlantNumber());
        List<EdiWzUpload> ediWzUploads = Lists.newArrayList();
        if (ediWzUploads.isEmpty()) {
            EdiWzUpload ediWzUpload = new EdiWzUpload();
            ediWzUpload.setStatus(0);
            ediWzUpload.setDeviceid(edi945.getTruckPlantNumber());
            ediWzUpload.setLongitude(edi945.getLongitude());
            ediWzUpload.setLatitude(edi945.getLatitude());
            ediWzUpload.setLocationtime(edi945.getGpsUpdating());
//            ediWzUploadMapper.insertSelective(ediWzUpload);
        } else {
            EdiWzUpload ediWzUpload = ediWzUploads.get(0);
            ediWzUpload.setStatus(0);
            ediWzUpload.setDeviceid(edi945.getTruckPlantNumber());
            ediWzUpload.setLongitude(edi945.getLongitude());
            ediWzUpload.setLatitude(edi945.getLatitude());
            ediWzUpload.setLocationtime(edi945.getGpsUpdating());
//            ediWzUploadMapper.updateByPrimaryKey(ediWzUpload);
        }
        // 轨迹数据edi表数据写入
//        List<EdiWzTrack> ediWzTracks = ediWzTrackMapper.selectBykPlantNumber(edi945.getTruckPlantNumber());
        List<EdiWzTrack> ediWzTracks = Lists.newArrayList();
        if (ediWzTracks.isEmpty()) {
            EdiWzTrack ediWzTrack = new EdiWzTrack();
            getEdiWzTrack(edi945, ediWzTrack);
//            ediWzTrackMapper.insertSelective(ediWzTrack);
        } else {
            EdiWzTrack ediWzTrack = ediWzTracks.get(0);
            getEdiWzTrack(edi945, ediWzTrack);
//            ediWzTrackMapper.updateByPrimaryKey(ediWzTrack);
        }
    }

    private void getEdiWzTrack(EDI945 edi945, EdiWzTrack ediWzTrack) {
        ediWzTrack.setStatus(0);
        ediWzTrack.setTracknos(edi945.getCtTracking());
        ediWzTrack.setLocationtime(edi945.getGpsUpdating());
        ediWzTrack.setDeviceid(edi945.getTruckPlantNumber());
        ediWzTrack.setProvince(edi945.getProvince());
        ediWzTrack.setCity(edi945.getCity());
        ediWzTrack.setPosition(edi945.getPosition());
        ediWzTrack.setLongitude(edi945.getLongitude());
        ediWzTrack.setLatitude(edi945.getLatitude());
    }

    private void checkFirstTask(EDI945 edi945, Boolean isFirst) {
        if (isFirst) {
            // 首次数据edi表数据写入
            EdiWzDevice ediWzDevice = new EdiWzDevice();
            ediWzDevice.setStatus(0);
            ediWzDevice.setDeviceid(edi945.getTruckPlantNumber());
            ediWzDevice.setDevicetype(edi945.getDeviceType());
            ediWzDevice.setSource(edi945.getSource());
//            ediWzDeviceMapper.insertSelective(ediWzDevice);
            EdiWzRegister ediWzRegister = new EdiWzRegister();
            ediWzRegister.setStatus(0);
            ediWzRegister.setDeviceid(edi945.getTruckPlantNumber());
            ediWzRegister.setTrackstarttime(DateUtil.format(edi945.getLocationTime(), DateUtil.NORM_DATETIME_PATTERN));
            ediWzRegister.setCompanycode(edi945.getCompanyCode());
            ediWzRegister.setOrderno(edi945.getCtTracking());
            ediWzRegister.setTrackno(edi945.getCtTracking());
//            ediWzRegisterMapper.insertSelective(ediWzRegister);
        }
    }
}
