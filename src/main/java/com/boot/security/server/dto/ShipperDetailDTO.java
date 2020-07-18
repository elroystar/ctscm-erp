package com.boot.security.server.dto;

import java.io.Serializable;

public class ShipperDetailDTO implements Serializable {

    private Integer id;

    private String country;

    private String tracking;

    private String region;

    private String trackingNo;

    private String hawb;

    private String dn;

    private String state;
    private String reason;
    private String date;
    private String time;
    private String zone;
    private String city;
    private String guobie;

    private String ctns;

    private String qty;

    private String factoryWeight;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTracking() {
        return tracking;
    }

    public void setTracking(String tracking) {
        this.tracking = tracking;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }

    public String getHawb() {
        return hawb;
    }

    public void setHawb(String hawb) {
        this.hawb = hawb;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGuobie() {
        return guobie;
    }

    public void setGuobie(String guobie) {
        this.guobie = guobie;
    }

    public String getCtns() {
        return ctns;
    }

    public void setCtns(String ctns) {
        this.ctns = ctns;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getFactoryWeight() {
        return factoryWeight;
    }

    public void setFactoryWeight(String factoryWeight) {
        this.factoryWeight = factoryWeight;
    }

    @Override
    public String toString() {
        return id +
                "," + country +
                ",'" + tracking +
                "," + region +
                "," + trackingNo +
                "," + state +
                "," + reason +
                "," + date +
                "," + time +
                "," + zone +
                "," + city +
                "," + guobie +
                "," + ctns +
                "," + qty +
                "," + factoryWeight;
    }
}
