package com.boot.security.server.model;

public class EdiWzRegister {
    private Integer id;

    private Integer status;

    private String trackno;

    private String deviceid;

    private String trackstarttime;

    private String companycode;

    private String orderno;

    private String trackendtime;

    private String bepushlocation;

    private String bepushtemperature;

    private String bepushdoorsensor;

    private String returnfield;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTrackno() {
        return trackno;
    }

    public void setTrackno(String trackno) {
        this.trackno = trackno == null ? null : trackno.trim();
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid == null ? null : deviceid.trim();
    }

    public String getTrackstarttime() {
        return trackstarttime;
    }

    public void setTrackstarttime(String trackstarttime) {
        this.trackstarttime = trackstarttime == null ? null : trackstarttime.trim();
    }

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode == null ? null : companycode.trim();
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public String getTrackendtime() {
        return trackendtime;
    }

    public void setTrackendtime(String trackendtime) {
        this.trackendtime = trackendtime == null ? null : trackendtime.trim();
    }

    public String getBepushlocation() {
        return bepushlocation;
    }

    public void setBepushlocation(String bepushlocation) {
        this.bepushlocation = bepushlocation == null ? null : bepushlocation.trim();
    }

    public String getBepushtemperature() {
        return bepushtemperature;
    }

    public void setBepushtemperature(String bepushtemperature) {
        this.bepushtemperature = bepushtemperature == null ? null : bepushtemperature.trim();
    }

    public String getBepushdoorsensor() {
        return bepushdoorsensor;
    }

    public void setBepushdoorsensor(String bepushdoorsensor) {
        this.bepushdoorsensor = bepushdoorsensor == null ? null : bepushdoorsensor.trim();
    }

    public String getReturnfield() {
        return returnfield;
    }

    public void setReturnfield(String returnfield) {
        this.returnfield = returnfield == null ? null : returnfield.trim();
    }
}