package com.boot.security.server.model;

import java.util.Date;

public class EDIHeadingUpdate {
    private Integer id;

    private Integer hid;

    private String shipper;

    private Date createTime;

    private String ctns;

    private String qty;

    private String factoryWeight;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public String getShipper() {
        return shipper;
    }

    public void setShipper(String shipper) {
        this.shipper = shipper == null ? null : shipper.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCtns() {
        return ctns;
    }

    public void setCtns(String ctns) {
        this.ctns = ctns == null ? null : ctns.trim();
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty == null ? null : qty.trim();
    }

    public String getFactoryWeight() {
        return factoryWeight;
    }

    public void setFactoryWeight(String factoryWeight) {
        this.factoryWeight = factoryWeight == null ? null : factoryWeight.trim();
    }
}