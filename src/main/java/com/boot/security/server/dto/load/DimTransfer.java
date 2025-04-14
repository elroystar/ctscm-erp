package com.boot.security.server.dto.load;

import java.math.BigDecimal;
import java.util.Date;

public class DimTransfer {
    private Integer id;

    private Integer status;

    private String createdTime;

    private String licencePlateNumber;

    private String lineNo;

    private String loadingNo;

    private BigDecimal numberOfBoxes;

    private BigDecimal quantity;

    private String repeatWeight;

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

    private String site;

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

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getLicencePlateNumber() {
        return licencePlateNumber;
    }

    public void setLicencePlateNumber(String licencePlateNumber) {
        this.licencePlateNumber = licencePlateNumber;
    }

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }

    public String getLoadingNo() {
        return loadingNo;
    }

    public void setLoadingNo(String loadingNo) {
        this.loadingNo = loadingNo;
    }

    public BigDecimal getNumberOfBoxes() {
        return numberOfBoxes;
    }

    public void setNumberOfBoxes(BigDecimal numberOfBoxes) {
        this.numberOfBoxes = numberOfBoxes;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getRepeatWeight() {
        return repeatWeight;
    }

    public void setRepeatWeight(String repeatWeight) {
        this.repeatWeight = repeatWeight;
    }

    public String getOem() {
        return oem;
    }

    public void setOem(String oem) {
        this.oem = oem;
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
        this.shippingPoint = shippingPoint;
    }

    public String getShippingMode() {
        return shippingMode;
    }

    public void setShippingMode(String shippingMode) {
        this.shippingMode = shippingMode;
    }

    public String getForwarder() {
        return forwarder;
    }

    public void setForwarder(String forwarder) {
        this.forwarder = forwarder;
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
        this.hawb = hawb;
    }

    public String getMawb() {
        return mawb;
    }

    public void setMawb(String mawb) {
        this.mawb = mawb;
    }

    public String getPalletId() {
        return palletId;
    }

    public void setPalletId(String palletId) {
        this.palletId = palletId;
    }

    public String getPalletSscc18() {
        return palletSscc18;
    }

    public void setPalletSscc18(String palletSscc18) {
        this.palletSscc18 = palletSscc18;
    }

    public String getPalletIdTrucker() {
        return palletIdTrucker;
    }

    public void setPalletIdTrucker(String palletIdTrucker) {
        this.palletIdTrucker = palletIdTrucker;
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
        this.region = region;
    }

    public String getPoe() {
        return poe;
    }

    public void setPoe(String poe) {
        this.poe = poe;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getConsolidationWarehouse() {
        return consolidationWarehouse;
    }

    public void setConsolidationWarehouse(String consolidationWarehouse) {
        this.consolidationWarehouse = consolidationWarehouse;
    }

    public String getNpiFlag() {
        return npiFlag;
    }

    public void setNpiFlag(String npiFlag) {
        this.npiFlag = npiFlag;
    }

    public String getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(String securityLevel) {
        this.securityLevel = securityLevel;
    }

    public String getHandover() {
        return handover;
    }

    public void setHandover(String handover) {
        this.handover = handover;
    }

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    public String getHubCode() {
        return hubCode;
    }

    public void setHubCode(String hubCode) {
        this.hubCode = hubCode;
    }

    public String getGccn() {
        return gccn;
    }

    public void setGccn(String gccn) {
        this.gccn = gccn;
    }

    public String getCountryOfClearance() {
        return countryOfClearance;
    }

    public void setCountryOfClearance(String countryOfClearance) {
        this.countryOfClearance = countryOfClearance;
    }

    public String getShipToCity() {
        return shipToCity;
    }

    public void setShipToCity(String shipToCity) {
        this.shipToCity = shipToCity;
    }

    public String getContainerNo() {
        return containerNo;
    }

    public void setContainerNo(String containerNo) {
        this.containerNo = containerNo;
    }

    public String getTruckNoExoem() {
        return truckNoExoem;
    }

    public void setTruckNoExoem(String truckNoExoem) {
        this.truckNoExoem = truckNoExoem;
    }

    public String getTruckNoExtrucker() {
        return truckNoExtrucker;
    }

    public void setTruckNoExtrucker(String truckNoExtrucker) {
        this.truckNoExtrucker = truckNoExtrucker;
    }

    public String getTruckNoBorderexchange() {
        return truckNoBorderexchange;
    }

    public void setTruckNoBorderexchange(String truckNoBorderexchange) {
        this.truckNoBorderexchange = truckNoBorderexchange;
    }

    public String getElockExoem() {
        return elockExoem;
    }

    public void setElockExoem(String elockExoem) {
        this.elockExoem = elockExoem;
    }

    public String getElockExtrucker() {
        return elockExtrucker;
    }

    public void setElockExtrucker(String elockExtrucker) {
        this.elockExtrucker = elockExtrucker;
    }

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getScacFwd() {
        return scacFwd;
    }

    public void setScacFwd(String scacFwd) {
        this.scacFwd = scacFwd;
    }

    public String getScacTrucker() {
        return scacTrucker;
    }

    public void setScacTrucker(String scacTrucker) {
        this.scacTrucker = scacTrucker;
    }

    public String getVesselImo() {
        return vesselImo;
    }

    public void setVesselImo(String vesselImo) {
        this.vesselImo = vesselImo;
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
        this.fastBoatService = fastBoatService;
    }

    public String getStandardOceanService() {
        return standardOceanService;
    }

    public void setStandardOceanService(String standardOceanService) {
        this.standardOceanService = standardOceanService;
    }

    public String getIcaoFlightCode() {
        return icaoFlightCode;
    }

    public void setIcaoFlightCode(String icaoFlightCode) {
        this.icaoFlightCode = icaoFlightCode;
    }

    public String getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(String aircraftType) {
        this.aircraftType = aircraftType;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
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
        this.flightNo = flightNo;
    }

    public String getGpsTransmitterNo() {
        return gpsTransmitterNo;
    }

    public void setGpsTransmitterNo(String gpsTransmitterNo) {
        this.gpsTransmitterNo = gpsTransmitterNo;
    }

    public String getDriverPhNo() {
        return driverPhNo;
    }

    public void setDriverPhNo(String driverPhNo) {
        this.driverPhNo = driverPhNo;
    }

    public String getTrailerNo() {
        return trailerNo;
    }

    public void setTrailerNo(String trailerNo) {
        this.trailerNo = trailerNo;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Override
    public String toString() {
        return (oem != null ? oem : "") +
                "#" + (shipDate != null ? shipDate : "") +
                "#" + (shippingPoint != null ? shippingPoint : "") +
                "#" + (shippingMode != null ? shippingMode : "") +
                "#" + (forwarderPdd != null ? forwarderPdd : "") +
                "#" + (hawb != null ? hawb : "") +
                "#" + (mawb != null ? mawb : "") +
                "#" + (palletId != null ? palletId : "") +
                "#" + (palletSscc18 != null ? palletSscc18 : "N/A") +
                "#" + (palletIdTrucker != null ? palletIdTrucker : "") +
                "#" + (grossWeight != null ? grossWeight : "") +
                "#" + (grossWeightPdd != null ? grossWeightPdd : "") +
                "#" + (lengthCm != null ? lengthCm : "") +
                "#" + (widthCm != null ? widthCm : "") +
                "#" + (heightCm != null ? heightCm : "") +
                "#" + (region != null ? region : "") +
                "#" + (poe != null ? poe : "") +
                "#" + (destination != null ? destination : "") +
                "#" + (gateway != null ? gateway : "") +
                "#" + (consolidationWarehouse != null ? consolidationWarehouse : "") +
                "#" + (npiFlag != null ? npiFlag : "") +
                "#" + (securityLevel != null ? securityLevel : "") +
                "#" + (handover != null ? handover : "") +
                "#" + (shipType != null ? shipType : "") +
                "#" + (hubCode != null ? hubCode : "") +
                "#" + (gccn != null ? gccn : "") +
                "#" + (countryOfClearance != null ? countryOfClearance : "") +
                "#" + (shipToCity != null ? shipToCity : "") +
                "#" + (containerNo != null ? containerNo : "") +
                "#" + (truckNoExoem != null ? truckNoExoem : "") +
                "#" + (truckNoExtrucker != null ? truckNoExtrucker : "") +
                "#" + (truckNoBorderexchange != null ? truckNoBorderexchange : "") +
                "#" + (elockExoem != null ? elockExoem : "") +
                "#" + (elockExtrucker != null ? elockExtrucker : "") +
                "#" + (pod != null ? pod : "") +
                "#" + (terminal != null ? terminal : "") +
                "#" + (scacFwd != null ? scacFwd : "") +
                "#" + (scacTrucker != null ? scacTrucker : "") +
                "#" + (vesselImo != null ? vesselImo : "") +
                "#" + (dwt != null ? dwt : "") +
                "#" + (portToPortDistance != null ? portToPortDistance : "") +
                "#" + (vesselDistanceTraveled != null ? vesselDistanceTraveled : "") +
                "#" + (fastBoatService != null ? fastBoatService : "") +
//            "#" + (standardOceanService != null ? standardOceanService : "") +
                "#" + (icaoFlightCode != null ? icaoFlightCode : "") +
                "#" + (aircraftType != null ? aircraftType : "") +
                "#" + (airlineName != null ? airlineName : "") +
                "#" + (flightDistance != null ? flightDistance : "") +
                "#" + (flightTime != null ? flightTime : "") +
                "#" + (flightNo != null ? flightNo : "") +
//            "#" + (gpsTransmitterNo != null ? gpsTransmitterNo : "") +
                "#" + (driverPhNo != null ? driverPhNo : "") +
                "#" + (trailerNo != null ? trailerNo : "") +
                "#" + (site != null ? site : "") +
                "#" + (status != null && status == 2 ? "已发送" : "未发送");
    }

    public String toLoadManifestString() {
        return (createdTime != null ? createdTime : "") +
                "," + (loadingNo != null ? loadingNo : "") +
                "," + (palletId != null ? palletId : "") +
                "," + (numberOfBoxes != null ? numberOfBoxes : "") +
                "," + (quantity != null ? quantity : "") +
                "," + (grossWeight != null ? grossWeight : "") +
                "," + (forwarder != null ? forwarder : "") +
                "," + (destination != null ? destination : "") +
                "," + (hawb != null ? hawb : "") +
                "," + (licencePlateNumber != null ? licencePlateNumber : "");
    }
}