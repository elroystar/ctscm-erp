package com.boot.security.server.dto.load;

public class SawbDTO {
    private Integer id;

    private Integer status;

    private String shipDate;

    private String sawb;

    private String hawb;

    private String palletId;

    private String ctns;

    private String units;

    private String gw;

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
        this.shipDate = shipDate;
    }

    public String getHawb() {
        return hawb;
    }

    public void setHawb(String hawb) {
        this.hawb = hawb;
    }

    public String getSawb() {
        return sawb;
    }

    public void setSawb(String sawb) {
        this.sawb = sawb;
    }

    public String getPalletId() {
        return palletId;
    }

    public void setPalletId(String palletId) {
        this.palletId = palletId;
    }

    public String getCtns() {
        return ctns;
    }

    public void setCtns(String ctns) {
        this.ctns = ctns;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getGw() {
        return gw;
    }

    public void setGw(String gw) {
        this.gw = gw;
    }
}