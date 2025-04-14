package com.boot.security.server.dto.load;

import java.math.BigDecimal;

public class DimTransferSH {
    private Integer id;

    private String date;

    private String loadingNo;

    private String palletId;

    private String cartons;

    private BigDecimal quantity;

    private BigDecimal grossWeight;

    private String forwarder;

    private String destination;

    private String hawb;

    private String licencePlateNumber;

    private String measurementTime;

    private BigDecimal measurementWeightKg;

    private BigDecimal measurementLengthCm;

    private BigDecimal measurementWidthCm;

    private BigDecimal measurementHeightCm;

    private BigDecimal measurementCbm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLoadingNo() {
        return loadingNo;
    }

    public void setLoadingNo(String loadingNo) {
        this.loadingNo = loadingNo;
    }

    public String getPalletId() {
        return palletId;
    }

    public void setPalletId(String palletId) {
        this.palletId = palletId;
    }

    public String getCartons() {
        return cartons;
    }

    public void setCartons(String cartons) {
        this.cartons = cartons;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(BigDecimal grossWeight) {
        this.grossWeight = grossWeight;
    }

    public String getForwarder() {
        return forwarder;
    }

    public void setForwarder(String forwarder) {
        this.forwarder = forwarder;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getHawb() {
        return hawb;
    }

    public void setHawb(String hawb) {
        this.hawb = hawb;
    }

    public String getLicencePlateNumber() {
        return licencePlateNumber;
    }

    public void setLicencePlateNumber(String licencePlateNumber) {
        this.licencePlateNumber = licencePlateNumber;
    }

    public String getMeasurementTime() {
        return measurementTime;
    }

    public void setMeasurementTime(String measurementTime) {
        this.measurementTime = measurementTime;
    }

    public BigDecimal getMeasurementWeightKg() {
        return measurementWeightKg;
    }

    public void setMeasurementWeightKg(BigDecimal measurementWeightKg) {
        this.measurementWeightKg = measurementWeightKg;
    }

    public BigDecimal getMeasurementLengthCm() {
        return measurementLengthCm;
    }

    public void setMeasurementLengthCm(BigDecimal measurementLengthCm) {
        this.measurementLengthCm = measurementLengthCm;
    }

    public BigDecimal getMeasurementWidthCm() {
        return measurementWidthCm;
    }

    public void setMeasurementWidthCm(BigDecimal measurementWidthCm) {
        this.measurementWidthCm = measurementWidthCm;
    }

    public BigDecimal getMeasurementHeightCm() {
        return measurementHeightCm;
    }

    public void setMeasurementHeightCm(BigDecimal measurementHeightCm) {
        this.measurementHeightCm = measurementHeightCm;
    }

    public BigDecimal getMeasurementCbm() {
        return measurementCbm;
    }

    public void setMeasurementCbm(BigDecimal measurementCbm) {
        this.measurementCbm = measurementCbm;
    }

    public String toLoadManifestString() {
        return (date != null ? date : "") +
                "," + (loadingNo != null ? loadingNo : "") +
                "," + (palletId != null ? palletId : "") +
                "," + (cartons != null ? cartons : "") +
                "," + (quantity != null ? quantity : "") +
                "," + (grossWeight != null ? grossWeight : "") +
                "," + (forwarder != null ? forwarder : "") +
                "," + (destination != null ? destination : "") +
                "," + (hawb != null ? hawb : "") +
                "," + (licencePlateNumber != null ? licencePlateNumber : "") +
                "," + (measurementTime != null ? measurementTime : "") +
                "," + (measurementWeightKg != null ? measurementWeightKg : "") +
                "," + (measurementLengthCm != null ? measurementLengthCm : "") +
                "," + (measurementWidthCm != null ? measurementWidthCm : "") +
                "," + (measurementHeightCm != null ? measurementHeightCm : "") +
                "," + (measurementCbm != null ? measurementCbm : "");
    }
}