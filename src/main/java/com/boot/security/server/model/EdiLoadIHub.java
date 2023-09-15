package com.boot.security.server.model;

public class EdiLoadIHub {
    private Integer id;

    private Integer status;

    private String shipDate;

    private String plantNo;

    private String hawb;

    private String loadingNo;

    private String palletId;

    private String pcs;

    private String ctns;

    private String gw;

    private String fwd;

    private String poe;

    private String region;

    private String fwd945;

    private String fwdCode945;

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

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate == null ? null : shipDate.trim();
    }

    public String getPlantNo() {
        return plantNo;
    }

    public void setPlantNo(String plantNo) {
        this.plantNo = plantNo == null ? null : plantNo.trim();
    }

    public String getHawb() {
        return hawb;
    }

    public void setHawb(String hawb) {
        this.hawb = hawb == null ? null : hawb.trim();
    }

    public String getLoadingNo() {
        return loadingNo;
    }

    public void setLoadingNo(String loadingNo) {
        this.loadingNo = loadingNo == null ? null : loadingNo.trim();
    }

    public String getPalletId() {
        return palletId;
    }

    public void setPalletId(String palletId) {
        this.palletId = palletId == null ? null : palletId.trim();
    }

    public String getPcs() {
        return pcs;
    }

    public void setPcs(String pcs) {
        this.pcs = pcs == null ? null : pcs.trim();
    }

    public String getCtns() {
        return ctns;
    }

    public void setCtns(String ctns) {
        this.ctns = ctns == null ? null : ctns.trim();
    }

    public String getGw() {
        return gw;
    }

    public void setGw(String gw) {
        this.gw = gw == null ? null : gw.trim();
    }

    public String getFwd() {
        return fwd;
    }

    public void setFwd(String fwd) {
        this.fwd = fwd == null ? null : fwd.trim();
    }

    public String getPoe() {
        return poe;
    }

    public void setPoe(String poe) {
        this.poe = poe == null ? null : poe.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public String getFwd945() {
        return fwd945;
    }

    public void setFwd945(String fwd945) {
        this.fwd945 = fwd945 == null ? null : fwd945.trim();
    }

    public String getFwdCode945() {
        return fwdCode945;
    }

    public void setFwdCode945(String fwdCode945) {
        this.fwdCode945 = fwdCode945 == null ? null : fwdCode945.trim();
    }
}