package com.boot.security.server.dto;

public class Send997InfoDTO {

    private String shipmentNumber;
    private String waybill;
    private String oem;
    private String gateway;
    private String region;
    private String truckPlantNumber;
    private String sender;
    private String shipDate;

    public String getShipmentNumber() {
        return shipmentNumber;
    }

    public void setShipmentNumber(String shipmentNumber) {
        this.shipmentNumber = shipmentNumber;
    }

    public String getWaybill() {
        return waybill;
    }

    public void setWaybill(String waybill) {
        this.waybill = waybill;
    }

    public String getOem() {
        return oem;
    }

    public void setOem(String oem) {
        this.oem = oem;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
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
        this.truckPlantNumber = truckPlantNumber;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }
}
