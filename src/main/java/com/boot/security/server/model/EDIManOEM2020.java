package com.boot.security.server.model;

public class EDIManOEM2020 {
    private Integer id;

    private Integer did;

    private String serialNo;

    private String tracking;

    private String cartonNo;

    private String cartonCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo == null ? null : serialNo.trim();
    }

    public String getTracking() {
        return tracking;
    }

    public void setTracking(String tracking) {
        this.tracking = tracking == null ? null : tracking.trim();
    }

    public String getCartonNo() {
        return cartonNo;
    }

    public void setCartonNo(String cartonNo) {
        this.cartonNo = cartonNo == null ? null : cartonNo.trim();
    }

    public String getCartonCount() {
        return cartonCount;
    }

    public void setCartonCount(String cartonCount) {
        this.cartonCount = cartonCount == null ? null : cartonCount.trim();
    }
}