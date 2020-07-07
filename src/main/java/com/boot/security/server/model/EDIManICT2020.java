package com.boot.security.server.model;

public class EDIManICT2020 {
    private Integer manid;

    private Integer lid;

    private String serialNo;

    private String tracking;

    private String cartonNo;

    private String scccartonCount;

    public Integer getManid() {
        return manid;
    }

    public void setManid(Integer manid) {
        this.manid = manid;
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
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

    public String getScccartonCount() {
        return scccartonCount;
    }

    public void setScccartonCount(String scccartonCount) {
        this.scccartonCount = scccartonCount == null ? null : scccartonCount.trim();
    }
}