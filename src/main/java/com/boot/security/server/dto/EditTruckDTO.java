package com.boot.security.server.dto;

import java.io.Serializable;

public class EditTruckDTO implements Serializable {

    private String shipmentNumberSub;
    private String shipwaySub;
    private String oemSub;
    private String gatewaySub;
    private String regionSub;
    private String[] region;
    private String truckPlantNumberSub;
    private String senderSub;
    private String shipDateSub;

    private String driverName;

    private String cellular;

    private String truckPlantNumber;

    private String ctTracking;

    private String gpsDevice;

    private String shipperType;
    private String fwd;
    private String fwdCode;

    public String getShipmentNumberSub() {
        return shipmentNumberSub;
    }

    public void setShipmentNumberSub(String shipmentNumberSub) {
        this.shipmentNumberSub = shipmentNumberSub;
    }

    public String getShipwaySub() {
        return shipwaySub;
    }

    public void setShipwaySub(String shipwaySub) {
        this.shipwaySub = shipwaySub;
    }

    public String getOemSub() {
        return oemSub;
    }

    public void setOemSub(String oemSub) {
        this.oemSub = oemSub;
    }

    public String getGatewaySub() {
        return gatewaySub;
    }

    public void setGatewaySub(String gatewaySub) {
        this.gatewaySub = gatewaySub;
    }

    public String getRegionSub() {
        return regionSub;
    }

    public void setRegionSub(String regionSub) {
        this.regionSub = regionSub;
    }

    public String getTruckPlantNumberSub() {
        return truckPlantNumberSub;
    }

    public void setTruckPlantNumberSub(String truckPlantNumberSub) {
        this.truckPlantNumberSub = truckPlantNumberSub;
    }

    public String getSenderSub() {
        return senderSub;
    }

    public void setSenderSub(String senderSub) {
        this.senderSub = senderSub;
    }

    public String getShipDateSub() {
        return shipDateSub;
    }

    public void setShipDateSub(String shipDateSub) {
        this.shipDateSub = shipDateSub;
    }

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

    public String getTruckPlantNumber() {
        return truckPlantNumber;
    }

    public void setTruckPlantNumber(String truckPlantNumber) {
        this.truckPlantNumber = truckPlantNumber;
    }

    public String getCtTracking() {
        return ctTracking;
    }

    public void setCtTracking(String ctTracking) {
        this.ctTracking = ctTracking;
    }

    public String getGpsDevice() {
        return gpsDevice;
    }

    public void setGpsDevice(String gpsDevice) {
        this.gpsDevice = gpsDevice;
    }

    public String getShipperType() {
        return shipperType;
    }

    public void setShipperType(String shipperType) {
        this.shipperType = shipperType;
    }

    public String[] getRegion() {
        return region;
    }

    public void setRegion(String[] region) {
        this.region = region;
    }

    public String getFwd() {
        return fwd;
    }

    public void setFwd(String fwd) {
        this.fwd = fwd;
    }

    public String getFwdCode() {
        return fwdCode;
    }

    public void setFwdCode(String fwdCode) {
        this.fwdCode = fwdCode;
    }
}
