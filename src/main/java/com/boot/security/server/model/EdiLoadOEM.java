package com.boot.security.server.model;

public class EdiLoadOEM {
    private Integer id;

    private Integer status;

    private String hoDate;

    private String oem;

    private String forwarder;

    private String hawb;

    private String palletId;

    private String plts;

    private String ctns;

    private String units;

    private String lengthCm;

    private String widthCm;

    private String heigthCm;

    private String weigthKg;

    private String remark;

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

    public String getHoDate() {
        return hoDate;
    }

    public void setHoDate(String hoDate) {
        this.hoDate = hoDate == null ? null : hoDate.trim();
    }

    public String getOem() {
        return oem;
    }

    public void setOem(String oem) {
        this.oem = oem == null ? null : oem.trim();
    }

    public String getForwarder() {
        return forwarder;
    }

    public void setForwarder(String forwarder) {
        this.forwarder = forwarder == null ? null : forwarder.trim();
    }

    public String getHawb() {
        return hawb;
    }

    public void setHawb(String hawb) {
        this.hawb = hawb == null ? null : hawb.trim();
    }

    public String getPalletId() {
        return palletId;
    }

    public void setPalletId(String palletId) {
        this.palletId = palletId == null ? null : palletId.trim();
    }

    public String getPlts() {
        return plts;
    }

    public void setPlts(String plts) {
        this.plts = plts == null ? null : plts.trim();
    }

    public String getCtns() {
        return ctns;
    }

    public void setCtns(String ctns) {
        this.ctns = ctns == null ? null : ctns.trim();
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units == null ? null : units.trim();
    }

    public String getLengthCm() {
        return lengthCm;
    }

    public void setLengthCm(String lengthCm) {
        this.lengthCm = lengthCm == null ? null : lengthCm.trim();
    }

    public String getWidthCm() {
        return widthCm;
    }

    public void setWidthCm(String widthCm) {
        this.widthCm = widthCm == null ? null : widthCm.trim();
    }

    public String getHeigthCm() {
        return heigthCm;
    }

    public void setHeigthCm(String heigthCm) {
        this.heigthCm = heigthCm == null ? null : heigthCm.trim();
    }

    public String getWeigthKg() {
        return weigthKg;
    }

    public void setWeigthKg(String weigthKg) {
        this.weigthKg = weigthKg == null ? null : weigthKg.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}