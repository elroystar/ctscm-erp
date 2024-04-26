package com.boot.security.server.dto.load;

import java.math.BigDecimal;
import java.util.Date;

public class DimTransfer {
    private Integer id;

    private Integer status;

    private Date createdTime;

    private String oem;

    private String shipDate;

    private String shippingPoint;

    private String shippingMode;

    private String forwarder;

    private String forwarderPdd;

    private String hawb;

    private String mawb;

    private String palletId;

    private String palletSscc18;

    private String palletIdTrucker;

    private BigDecimal grossWeight;

    private BigDecimal grossWeightPdd;

    private BigDecimal lengthCm;

    private BigDecimal widthCm;

    private BigDecimal heightCm;

    private String region;

    private String poe;

    private String destination;

    private String gateway;

    private String consolidationWarehouse;

    private String npiFlag;

    private String securityLevel;

    private String handover;

    private String shipType;

    private String hubCode;

    private String gccn;

    private String countryOfClearance;

    private String shipToCity;

    private String containerNo;

    private String truckNoExoem;

    private String truckNoExtrucker;

    private String truckNoBorderexchange;

    private String elockExoem;

    private String elockExtrucker;

    private String pod;

    private String terminal;

    private String scacFwd;

    private String scacTrucker;

    private String vesselImo;

    private BigDecimal dwt;

    private BigDecimal portToPortDistance;

    private BigDecimal vesselDistanceTraveled;

    private String fastBoatService;

    private String standardOceanService;

    private String icaoFlightCode;

    private String aircraftType;

    private String airlineName;

    private BigDecimal flightDistance;

    private BigDecimal flightTime;

    private String flightNo;

    private String gpsTransmitterNo;

    private String driverPhNo;

    private String trailerNo;

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

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getOem() {
        return oem;
    }

    public void setOem(String oem) {
        this.oem = oem == null ? null : oem.trim();
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getShippingPoint() {
        return shippingPoint;
    }

    public void setShippingPoint(String shippingPoint) {
        this.shippingPoint = shippingPoint == null ? null : shippingPoint.trim();
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

    public String getForwarderPdd() {
        return forwarderPdd;
    }

    public void setForwarderPdd(String forwarderPdd) {
        this.forwarderPdd = forwarderPdd;
    }

    public String getHawb() {
        return hawb;
    }

    public void setHawb(String hawb) {
        this.hawb = hawb == null ? null : hawb.trim();
    }

    public String getMawb() {
        return mawb;
    }

    public void setMawb(String mawb) {
        this.mawb = mawb == null ? null : mawb.trim();
    }

    public String getPalletId() {
        return palletId;
    }

    public void setPalletId(String palletId) {
        this.palletId = palletId == null ? null : palletId.trim();
    }

    public String getPalletSscc18() {
        return palletSscc18;
    }

    public void setPalletSscc18(String palletSscc18) {
        this.palletSscc18 = palletSscc18 == null ? null : palletSscc18.trim();
    }

    public String getPalletIdTrucker() {
        return palletIdTrucker;
    }

    public void setPalletIdTrucker(String palletIdTrucker) {
        this.palletIdTrucker = palletIdTrucker == null ? null : palletIdTrucker.trim();
    }

    public BigDecimal getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(BigDecimal grossWeight) {
        this.grossWeight = grossWeight;
    }

    public BigDecimal getGrossWeightPdd() {
        return grossWeightPdd;
    }

    public void setGrossWeightPdd(BigDecimal grossWeightPdd) {
        this.grossWeightPdd = grossWeightPdd;
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

    public String getConsolidationWarehouse() {
        return consolidationWarehouse;
    }

    public void setConsolidationWarehouse(String consolidationWarehouse) {
        this.consolidationWarehouse = consolidationWarehouse == null ? null : consolidationWarehouse.trim();
    }

    public String getNpiFlag() {
        return npiFlag;
    }

    public void setNpiFlag(String npiFlag) {
        this.npiFlag = npiFlag == null ? null : npiFlag.trim();
    }

    public String getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(String securityLevel) {
        this.securityLevel = securityLevel == null ? null : securityLevel.trim();
    }

    public String getHandover() {
        return handover;
    }

    public void setHandover(String handover) {
        this.handover = handover == null ? null : handover.trim();
    }

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType == null ? null : shipType.trim();
    }

    public String getHubCode() {
        return hubCode;
    }

    public void setHubCode(String hubCode) {
        this.hubCode = hubCode == null ? null : hubCode.trim();
    }

    public String getGccn() {
        return gccn;
    }

    public void setGccn(String gccn) {
        this.gccn = gccn == null ? null : gccn.trim();
    }

    public String getCountryOfClearance() {
        return countryOfClearance;
    }

    public void setCountryOfClearance(String countryOfClearance) {
        this.countryOfClearance = countryOfClearance == null ? null : countryOfClearance.trim();
    }

    public String getShipToCity() {
        return shipToCity;
    }

    public void setShipToCity(String shipToCity) {
        this.shipToCity = shipToCity == null ? null : shipToCity.trim();
    }

    public String getContainerNo() {
        return containerNo;
    }

    public void setContainerNo(String containerNo) {
        this.containerNo = containerNo == null ? null : containerNo.trim();
    }

    public String getTruckNoExoem() {
        return truckNoExoem;
    }

    public void setTruckNoExoem(String truckNoExoem) {
        this.truckNoExoem = truckNoExoem == null ? null : truckNoExoem.trim();
    }

    public String getTruckNoExtrucker() {
        return truckNoExtrucker;
    }

    public void setTruckNoExtrucker(String truckNoExtrucker) {
        this.truckNoExtrucker = truckNoExtrucker == null ? null : truckNoExtrucker.trim();
    }

    public String getTruckNoBorderexchange() {
        return truckNoBorderexchange;
    }

    public void setTruckNoBorderexchange(String truckNoBorderexchange) {
        this.truckNoBorderexchange = truckNoBorderexchange == null ? null : truckNoBorderexchange.trim();
    }

    public String getElockExoem() {
        return elockExoem;
    }

    public void setElockExoem(String elockExoem) {
        this.elockExoem = elockExoem == null ? null : elockExoem.trim();
    }

    public String getElockExtrucker() {
        return elockExtrucker;
    }

    public void setElockExtrucker(String elockExtrucker) {
        this.elockExtrucker = elockExtrucker == null ? null : elockExtrucker.trim();
    }

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod == null ? null : pod.trim();
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal == null ? null : terminal.trim();
    }

    public String getScacFwd() {
        return scacFwd;
    }

    public void setScacFwd(String scacFwd) {
        this.scacFwd = scacFwd == null ? null : scacFwd.trim();
    }

    public String getScacTrucker() {
        return scacTrucker;
    }

    public void setScacTrucker(String scacTrucker) {
        this.scacTrucker = scacTrucker == null ? null : scacTrucker.trim();
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

    public BigDecimal getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(BigDecimal flightTime) {
        this.flightTime = flightTime;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo == null ? null : flightNo.trim();
    }

    public String getGpsTransmitterNo() {
        return gpsTransmitterNo;
    }

    public void setGpsTransmitterNo(String gpsTransmitterNo) {
        this.gpsTransmitterNo = gpsTransmitterNo == null ? null : gpsTransmitterNo.trim();
    }

    public String getDriverPhNo() {
        return driverPhNo;
    }

    public void setDriverPhNo(String driverPhNo) {
        this.driverPhNo = driverPhNo == null ? null : driverPhNo.trim();
    }

    public String getTrailerNo() {
        return trailerNo;
    }

    public void setTrailerNo(String trailerNo) {
        this.trailerNo = trailerNo == null ? null : trailerNo.trim();
    }

    @Override
    public String toString() {
        return oem +
                "," + shipDate +
                "," + shippingPoint +
                "," + shippingMode +
                "," + forwarder +
                "," + forwarderPdd +
                "," + hawb +
                "," + mawb +
                "," + palletId +
                "," + palletSscc18 +
                "," + palletIdTrucker +
                "," + grossWeight +
                "," + grossWeightPdd +
                "," + lengthCm +
                "," + widthCm +
                "," + heightCm +
                "," + region +
                "," + poe +
                "," + destination +
                "," + gateway +
                "," + consolidationWarehouse +
                "," + npiFlag +
                "," + securityLevel +
                "," + handover +
                "," + shipType +
                "," + hubCode +
                "," + gccn +
                "," + countryOfClearance +
                "," + shipToCity +
                "," + containerNo +
                "," + truckNoExoem +
                "," + truckNoExtrucker +
                "," + truckNoBorderexchange +
                "," + elockExoem +
                "," + elockExtrucker +
                "," + pod +
                "," + terminal +
                "," + scacFwd +
                "," + scacTrucker +
                "," + vesselImo +
                "," + dwt +
                "," + portToPortDistance +
                "," + vesselDistanceTraveled +
                "," + fastBoatService +
                "," + standardOceanService +
                "," + icaoFlightCode +
                "," + aircraftType +
                "," + airlineName +
                "," + flightDistance +
                "," + flightTime +
                "," + flightNo +
                "," + gpsTransmitterNo +
                "," + driverPhNo +
                "," + trailerNo;
    }
}