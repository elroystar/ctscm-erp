package com.boot.security.server.dto;

import java.io.Serializable;

public class EDI945ExportExcelDTO implements Serializable {

    private String shipDate;

    private String actualDate;

    private String sender;

    private String trackingNumber;

    private String dn;

    private String shipmentNumber;

    private String waybill;

    private String shipway;

    private String fwd;

    private String fwdCode;

    private String oem;

    private String gateway;

    private String ctns;

    private String units;

    private String gw;

    private String shipMode;

    private String poe;

    private String poeCountry;

    private String region;

    private String driverName;

    private String cellular;

    private String truckPlantNumber;

    private String ctTracking;

    private String gpsDevice;

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getCellular() {
        return cellular;
    }

    public void setCellular(String cellular) {
        this.cellular = cellular;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate == null ? null : shipDate.trim();
    }

    public String getActualDate() {
        return actualDate;
    }

    public void setActualDate(String actualDate) {
        this.actualDate = actualDate == null ? null : actualDate.trim();
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender == null ? null : sender.trim();
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber == null ? null : trackingNumber.trim();
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn == null ? null : dn.trim();
    }

    public String getShipmentNumber() {
        return shipmentNumber;
    }

    public void setShipmentNumber(String shipmentNumber) {
        this.shipmentNumber = shipmentNumber == null ? null : shipmentNumber.trim();
    }

    public String getWaybill() {
        return waybill;
    }

    public void setWaybill(String waybill) {
        this.waybill = waybill == null ? null : waybill.trim();
    }

    public String getShipway() {
        return shipway;
    }

    public void setShipway(String shipway) {
        this.shipway = shipway == null ? null : shipway.trim();
    }

    public String getFwd() {
        return fwd;
    }

    public void setFwd(String fwd) {
        this.fwd = fwd == null ? null : fwd.trim();
    }

    public String getFwdCode() {
        return fwdCode;
    }

    public void setFwdCode(String fwdCode) {
        this.fwdCode = fwdCode == null ? null : fwdCode.trim();
    }

    public String getOem() {
        return oem;
    }

    public void setOem(String oem) {
        this.oem = oem == null ? null : oem.trim();
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway == null ? null : gateway.trim();
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

    public String getGw() {
        return gw;
    }

    public void setGw(String gw) {
        this.gw = gw == null ? null : gw.trim();
    }

    public String getShipMode() {
        return shipMode;
    }

    public void setShipMode(String shipMode) {
        this.shipMode = shipMode == null ? null : shipMode.trim();
    }

    public String getPoe() {
        return poe;
    }

    public void setPoe(String poe) {
        this.poe = poe == null ? null : poe.trim();
    }

    public String getPoeCountry() {
        return poeCountry;
    }

    public void setPoeCountry(String poeCountry) {
        this.poeCountry = poeCountry == null ? null : poeCountry.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTruckPlantNumber() {
        return truckPlantNumber;
    }

    public void setTruckPlantNumber(String truckPlantNumber) {
        this.truckPlantNumber = truckPlantNumber == null ? null : truckPlantNumber.trim();
    }

    public String getCtTracking() {
        return ctTracking;
    }

    public void setCtTracking(String ctTracking) {
        this.ctTracking = ctTracking == null ? null : ctTracking.trim();
    }

    public String getGpsDevice() {
        return gpsDevice;
    }

    public void setGpsDevice(String gpsDevice) {
        this.gpsDevice = gpsDevice == null ? null : gpsDevice.trim();
    }

    @Override
    public String toString() {
        return "" +
                "," + shipDate +
                "," + actualDate +
                "," + sender +
                "," + trackingNumber +
                "," + dn +
                "," + shipmentNumber +
                "," + waybill +
                "," + shipway +
                "," + fwd +
                "," + fwdCode +
                "," + oem +
                "," + gateway +
                "," + ctns +
                "," + units +
                "," + gw +
                "," + shipMode +
                "," + poe +
                "," + poeCountry +
                "," + region +
                "," + driverName +
                "," + cellular +
                "," + truckPlantNumber +
                "," + ctTracking +
                "," + gpsDevice;
    }
}