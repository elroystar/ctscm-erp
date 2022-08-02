package com.boot.security.server.service;

import com.openapi.sdk.service.DataExchangeService;

import java.util.HashMap;
import java.util.Map;

public class TestService {

    /** 用户登陆
     * 用户首次调用接口，须先登录，认证通过后生成令牌。
     令牌，多服务调用业务接口时，建议由统一服务调用登录接口将令牌缓存起来，多个服务统一从
     共享缓存中获取令牌。
     为安全考虑，建议用户自己每月至少更新一次令牌。
     令牌失效后再调用登录接口获取令牌，避免频繁调用登录接口，建议一天内登录次数不超过 10
     次，超过 10 次将触发安全系统报警。
     * */
    public static void login() {
        try {
            Map<String, String> map = new HashMap<String, String>(4);
            map.put("user", "2d7db19a-e76d-4249-9caa-d92613c215ba");
            map.put("pwd", "37X7451434Y32477651hP87GN47wy3");
            map.put("srt", "300ba756-4284-48de-b0a8-cbcf3619a317");
            map.put("cid", "e8b17fe1-9905-4c15-8aa3-b8da5f9ff65b");
            String url = "https://openapi-test.sinoiov.cn/save/apis/login";
            // 设置 http 读写超时
            DataExchangeService des = new DataExchangeService(5000, 8000);
            System.out.println("请求地址:"+url);
            // 通过 https 方式调用，此方法内部会使用私钥生成签名参数 sign,私钥不会发送
            String res = des.postHttps(url, map);
            System.out.println("返回:"+ res);
        } catch (Exception e) {
            System.out.println("e:" + e.getMessage());
        }
    }

    /** 一、 运输时效管理接口
     * 本接口提供指定车牌号的车辆运输时效。
     93
     * */
    public static void transTimeManageV2() {
        try {
            Map<String, String> map = new HashMap<String, String>(3);
            map.put("token", "604b80a6-a49e-4855-834d-b19e9802dd1c");
            map.put("cid", "e8b17fe1-9905-4c15-8aa3-b8da5f9ff65b");
            map.put("srt", "300ba756-4284-48de-b0a8-cbcf3619a317");
            map.put("vnos", "陕YH0008_2");
//            map.put("timeNearby", "30");
            String url = "https://openapi-test.sinoiov.cn/save/apis/transTimeManageV2";
            DataExchangeService des = new DataExchangeService(5000, 8000);
            System.out.println("请求地址:"+url);
            // 通过 https 方式调用，此方法内部会使用私钥生成签名参数 sign,私钥不会发送
            String res = des.postHttps(url, map);
            System.out.println("返回:"+ res);
        } catch (Exception e) {
            System.out.println("e:" + e.getMessage());
        }
    }

    /** 一、 运输时效管理接口
     * 本接口提供指定车牌号的车辆运输时效。
     93
     * */
    public static void routerPath() {
        try {
            Map<String, String> map = new HashMap<String, String>(3);
            map.put("token", "604b80a6-a49e-4855-834d-b19e9802dd1c");
            map.put("cid", "e8b17fe1-9905-4c15-8aa3-b8da5f9ff65b");
            map.put("srt", "300ba756-4284-48de-b0a8-cbcf3619a317");
            map.put("vclN", "陕YH0008");
            map.put("vco", "2");
            map.put("qryBtm", "2021-09-05 15:56:46");
            map.put("qryEtm", "2021-09-06 15:56:46");
            String url = "https://openapi-test.sinoiov.cn/save/apis/routerPath";
            DataExchangeService des = new DataExchangeService(5000, 8000);
            System.out.println("请求地址:"+url);
            // 通过 https 方式调用，此方法内部会使用私钥生成签名参数 sign,私钥不会发送
            String res = des.postHttps(url, map);
            System.out.println("返回:"+ res);
        } catch (Exception e) {
            System.out.println("e:" + e.getMessage());
        }
    }

    public static void main(String[] args) {
//        login();
        transTimeManageV2();
        routerPath();
    }
}
