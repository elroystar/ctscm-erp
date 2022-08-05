package com.boot.security.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.boot.security.server.service.GPSService;
import com.openapi.sdk.service.DataExchangeService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class GPSServiceImpl implements GPSService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String login() {
        try {
            String token = redisTemplate.opsForValue().get("gps:login:token");
            if (StringUtils.isNotBlank(token)) {
                return token;
            }
            Map<String, String> map = new HashMap<String, String>(4);
            map.put("user", "dd69052e-5367-429c-873c-9dba2ddb5cca");
            map.put("pwd", "6742208qHb8p62KP9m7G59rE92937T");
            map.put("srt", "e4e0967d-8593-464b-a608-17b9042a5f4d");
            map.put("cid", "72ca4ad1-dec0-45ab-91a7-1d99627e9427");
            String url = "https://zhiyunopenapi.95155.com/save/apis/login";
            // 设置 http 读写超时
            DataExchangeService des = new DataExchangeService(5000, 8000);
            logger.info("请求地址:{}", url);
            // 通过 https 方式调用，此方法内部会使用私钥生成签名参数 sign,私钥不会发送
            String res = des.postHttps(url, map);
            logger.info("返回:{}", res);
            JSONObject jsonObject = JSON.parseObject(res);
            token = jsonObject.getString("result");
            redisTemplate.opsForValue().set("gps:login:token", token, 30, TimeUnit.MINUTES);
            return token;
        } catch (Exception e) {
            logger.error("GPSService login is error, e:{}", e);
            return null;
        }
    }

    /**
     * 一、 运输时效管理接口
     * 本接口提供指定车牌号的车辆运输时效。
     * 93
     */
    @Override
    public String transTimeManageV2(String token, String truckPlantNumber) {
        try {
            Map<String, String> map = new HashMap<String, String>(3);
            map.put("token", token);
            map.put("srt", "e4e0967d-8593-464b-a608-17b9042a5f4d");
            map.put("cid", "72ca4ad1-dec0-45ab-91a7-1d99627e9427");
            map.put("vnos", truckPlantNumber);
//            map.put("timeNearby", "30");
            String url = "https://zhiyunopenapi.95155.com/save/apis/login/save/apis/transTimeManageV2";
            DataExchangeService des = new DataExchangeService(5000, 8000);
            logger.info("请求地址:{}", url);
            // 通过 https 方式调用，此方法内部会使用私钥生成签名参数 sign,私钥不会发送
            String res = des.postHttps(url, map);
            logger.info("返回:{}", res);
            return res;
        } catch (Exception e) {
            logger.error("GPSService transTimeManageV2 is error, e:{}", e);
            return null;
        }
    }
}
