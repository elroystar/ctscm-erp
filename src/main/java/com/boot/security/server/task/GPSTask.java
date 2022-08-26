package com.boot.security.server.task;

import com.alibaba.fastjson.JSON;
import com.boot.security.server.dao.EDI945Mapper;
import com.boot.security.server.dto.gps.FirstVcl;
import com.boot.security.server.dto.gps.TransTimeManageV2;
import com.boot.security.server.model.EDI945;
import com.boot.security.server.service.GPSService;
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

    @Scheduled(cron = "0 */1 * * * ? ")
    public void getTruckPlantNumberGPS() {
        // 定时获取所有需要获取数据的车辆
        List<EDI945> edi945List = edi945Mapper.selectByGPSState(0);
        for (EDI945 edi945 : edi945List) {
            String token = gpsService.login();
            String transTimeManageV2 = gpsService.transTimeManageV2(token, edi945.getGpsDevice());
            if (StringUtils.isNotBlank(transTimeManageV2)) {
                TransTimeManageV2 transTime = JSON.parseObject(transTimeManageV2, TransTimeManageV2.class);
                if (transTime.getStatus() == 1001) {
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
                    }
                    edi945Mapper.updateByTruckPlantNumber(edi945);
                    edi945Mapper.updateGPSByTruckPlantNumber(edi945);
                }
            }
            logger.debug("GPSTask getTruckPlantNumberGPS log, TruckPlantNumber:{}, gps info:{}", edi945.getGpsDevice(), transTimeManageV2);
        }
    }
}
