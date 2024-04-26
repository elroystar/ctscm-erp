package com.boot.security.server.dto.load;

import java.math.BigDecimal;

public class EDILoad {
    private Integer id;

    private Integer status;

    private String shipDate;

    private String oem;

    private String shippingMode;

    private String forwarder;

    private String hawb;

    private String sscc18PalletId;

    private String oemPalletId;

    private String truckerPalletId;

    private String region;

    private String poe;

    private String destination;

    private String gateway;

    private String consolidationWH;

    private BigDecimal grossWeightKg;

    private BigDecimal lengthCm;

    private BigDecimal widthCm;

    private BigDecimal heightCm;

    private BigDecimal weightOemDataKg;

    private BigDecimal lengthOemDataCm;

    private BigDecimal widthOemDataCm;

    private BigDecimal heightOemDataCm;

    private BigDecimal weightDiscrepancyPercentage;

    private BigDecimal lengthDiscrepancyPercentage;

    private BigDecimal widthDiscrepancyPercentage;

    private BigDecimal heightDiscrepancyPercentage;

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

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getOem() {
        return oem;
    }

    public void setOem(String oem) {
        this.oem = oem == null ? null : oem.trim();
    }

    public String getShippingMode() {
        return shippingMode;
    }

    public void setShippingMode(String shippingMode) {
        this.shippingMode = shippingMode == null ? null : shippingMode.trim();
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

    public String getSscc18PalletId() {
        return sscc18PalletId;
    }

    public void setSscc18PalletId(String sscc18PalletId) {
        this.sscc18PalletId = sscc18PalletId == null ? null : sscc18PalletId.trim();
    }

    public String getOemPalletId() {
        return oemPalletId;
    }

    public void setOemPalletId(String oemPalletId) {
        this.oemPalletId = oemPalletId == null ? null : oemPalletId.trim();
    }

    public String getTruckerPalletId() {
        return truckerPalletId;
    }

    public void setTruckerPalletId(String truckerPalletId) {
        this.truckerPalletId = truckerPalletId == null ? null : truckerPalletId.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public String getPoe() {
        return poe;
    }

    public void setPoe(String poe) {
        this.poe = poe == null ? null : poe.trim();
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination == null ? null : destination.trim();
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway == null ? null : gateway.trim();
    }

    public String getConsolidationWH() {
        return consolidationWH;
    }

    public void setConsolidationWH(String consolidationWH) {
        this.consolidationWH = consolidationWH == null ? null : consolidationWH.trim();
    }

    public BigDecimal getGrossWeightKg() {
        return grossWeightKg;
    }

    public void setGrossWeightKg(BigDecimal grossWeightKg) {
        this.grossWeightKg = grossWeightKg;
    }

    public BigDecimal getLengthCm() {
        return lengthCm;
    }

    public void setLengthCm(BigDecimal lengthCm) {
        this.lengthCm = lengthCm;
    }

    public BigDecimal getWidthCm() {
        return widthCm;
    }

    public void setWidthCm(BigDecimal widthCm) {
        this.widthCm = widthCm;
    }

    public BigDecimal getHeightCm() {
        return heightCm;
    }

    public void setHeightCm(BigDecimal heightCm) {
        this.heightCm = heightCm;
    }

    public BigDecimal getWeightOemDataKg() {
        return weightOemDataKg;
    }

    public void setWeightOemDataKg(BigDecimal weightOemDataKg) {
        this.weightOemDataKg = weightOemDataKg;
    }

    public BigDecimal getLengthOemDataCm() {
        return lengthOemDataCm;
    }

    public void setLengthOemDataCm(BigDecimal lengthOemDataCm) {
        this.lengthOemDataCm = lengthOemDataCm;
    }

    public BigDecimal getWidthOemDataCm() {
        return widthOemDataCm;
    }

    public void setWidthOemDataCm(BigDecimal widthOemDataCm) {
        this.widthOemDataCm = widthOemDataCm;
    }

    public BigDecimal getHeightOemDataCm() {
        return heightOemDataCm;
    }

    public void setHeightOemDataCm(BigDecimal heightOemDataCm) {
        this.heightOemDataCm = heightOemDataCm;
    }

    public BigDecimal getWeightDiscrepancyPercentage() {
        return weightDiscrepancyPercentage;
    }

    public void setWeightDiscrepancyPercentage(BigDecimal weightDiscrepancyPercentage) {
        this.weightDiscrepancyPercentage = weightDiscrepancyPercentage;
    }

    public BigDecimal getLengthDiscrepancyPercentage() {
        return lengthDiscrepancyPercentage;
    }

    public void setLengthDiscrepancyPercentage(BigDecimal lengthDiscrepancyPercentage) {
        this.lengthDiscrepancyPercentage = lengthDiscrepancyPercentage;
    }

    public BigDecimal getWidthDiscrepancyPercentage() {
        return widthDiscrepancyPercentage;
    }

    public void setWidthDiscrepancyPercentage(BigDecimal widthDiscrepancyPercentage) {
        this.widthDiscrepancyPercentage = widthDiscrepancyPercentage;
    }

    public BigDecimal getHeightDiscrepancyPercentage() {
        return heightDiscrepancyPercentage;
    }

    public void setHeightDiscrepancyPercentage(BigDecimal heightDiscrepancyPercentage) {
        this.heightDiscrepancyPercentage = heightDiscrepancyPercentage;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        return shipDate +
                "," + oem +
                "," + shippingMode +
                "," + forwarder +
                "," + hawb +
                "," + sscc18PalletId +
                "," + oemPalletId +
                "," + truckerPalletId +
                "," + region +
                "," + poe +
                "," + destination +
                "," + gateway +
                "," + consolidationWH +
                "," + grossWeightKg +
                "," + lengthCm +
                "," + widthCm +
                "," + heightCm +
                "," + weightOemDataKg +
                "," + lengthOemDataCm +
                "," + widthOemDataCm +
                "," + heightOemDataCm +
                "," + weightDiscrepancyPercentage +
                "," + lengthDiscrepancyPercentage +
                "," + widthDiscrepancyPercentage +
                "," + heightDiscrepancyPercentage +
                "," + remark;
    }
}