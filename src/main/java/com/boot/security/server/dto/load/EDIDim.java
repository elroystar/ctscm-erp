package com.boot.security.server.dto.load;

import java.math.BigDecimal;
import java.util.Date;

public class EDIDim {
    private Integer id;

    private Integer status;

    private Date shipDate;

    private String oem;

    private String shippingMode;

    private String forwarder;

    private String hawb;

    private String dnPoLineNumber;

    private String sscc18PalletId;

    private String oemPalletId;

    private String truckerPalletId;

    private String productId;

    private String region;

    private String poe;

    private String destination;

    private String gateway;

    private String consolidationWH;

    private String scacFwd;

    private String scacTruck;

    private String sealNrOceanContainer;

    private String vesselImo;

    private BigDecimal dwt;

    private BigDecimal portToPortDistance;

    private BigDecimal vesselDistanceTraveled;

    private String fastStandardOcean;

    private String fastBoatService;

    private String standardOceanService;

    private String icaoFlightCode;

    private String aircraftType;

    private String airlineName;

    private BigDecimal flightDistance;

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

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
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

    public String getDnPoLineNumber() {
        return dnPoLineNumber;
    }

    public void setDnPoLineNumber(String dnPoLineNumber) {
        this.dnPoLineNumber = dnPoLineNumber == null ? null : dnPoLineNumber.trim();
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
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

    public String getScacFwd() {
        return scacFwd;
    }

    public void setScacFwd(String scacFwd) {
        this.scacFwd = scacFwd == null ? null : scacFwd.trim();
    }

    public String getScacTruck() {
        return scacTruck;
    }

    public void setScacTruck(String scacTruck) {
        this.scacTruck = scacTruck == null ? null : scacTruck.trim();
    }

    public String getSealNrOceanContainer() {
        return sealNrOceanContainer;
    }

    public void setSealNrOceanContainer(String sealNrOceanContainer) {
        this.sealNrOceanContainer = sealNrOceanContainer == null ? null : sealNrOceanContainer.trim();
    }

    public String getVesselImo() {
        return vesselImo;
    }

    public void setVesselImo(String vesselImo) {
        this.vesselImo = vesselImo == null ? null : vesselImo.trim();
    }

    public BigDecimal getDwt() {
        return dwt;
    }

    public void setDwt(BigDecimal dwt) {
        this.dwt = dwt;
    }

    public BigDecimal getPortToPortDistance() {
        return portToPortDistance;
    }

    public void setPortToPortDistance(BigDecimal portToPortDistance) {
        this.portToPortDistance = portToPortDistance;
    }

    public BigDecimal getVesselDistanceTraveled() {
        return vesselDistanceTraveled;
    }

    public void setVesselDistanceTraveled(BigDecimal vesselDistanceTraveled) {
        this.vesselDistanceTraveled = vesselDistanceTraveled;
    }

    public String getFastStandardOcean() {
        return fastStandardOcean;
    }

    public void setFastStandardOcean(String fastStandardOcean) {
        this.fastStandardOcean = fastStandardOcean == null ? null : fastStandardOcean.trim();
    }

    public String getFastBoatService() {
        return fastBoatService;
    }

    public void setFastBoatService(String fastBoatService) {
        this.fastBoatService = fastBoatService == null ? null : fastBoatService.trim();
    }

    public String getStandardOceanService() {
        return standardOceanService;
    }

    public void setStandardOceanService(String standardOceanService) {
        this.standardOceanService = standardOceanService == null ? null : standardOceanService.trim();
    }

    public String getIcaoFlightCode() {
        return icaoFlightCode;
    }

    public void setIcaoFlightCode(String icaoFlightCode) {
        this.icaoFlightCode = icaoFlightCode == null ? null : icaoFlightCode.trim();
    }

    public String getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(String aircraftType) {
        this.aircraftType = aircraftType == null ? null : aircraftType.trim();
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName == null ? null : airlineName.trim();
    }

    public BigDecimal getFlightDistance() {
        return flightDistance;
    }

    public void setFlightDistance(BigDecimal flightDistance) {
        this.flightDistance = flightDistance;
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
}