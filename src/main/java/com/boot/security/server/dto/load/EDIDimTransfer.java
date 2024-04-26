package com.boot.security.server.dto.load;

import java.math.BigDecimal;
import java.util.Date;

public class EDIDimTransfer {
    private Integer id;

    private Integer status;

    private Date createdtime;

    private String oem;

    private Date shipdate;

    private String shippingPoint;

    private String shippingMode;

    private String forwarder;

    private String hawb;

    private String mawb;

    private String pallets;

    private String palletIdOem;

    private String palletSscc18;

    private String palletIdTrucker;

    private BigDecimal grossWeight;

    private BigDecimal length;

    private BigDecimal width;

    private BigDecimal height;

    private String appledn;

    private String pono;

    private String shipmentId;

    private String lineItem;

    private String projectCode;

    private String productId;

    private String lineItemQuantity;

    private String region;

    private String poe;

    private String destination;

    private String gateway;

    private String consolidationWarehouse;

    private Integer slac;

    private BigDecimal cbm;

    private String npiFlag;

    private String securityLevel;

    private String handover;

    private String shipType;

    private String hubCode;

    private String gccn;

    private String serviceLevel;

    private String serviceLevelId;

    private String countryOfClearance;

    private String shipToCity;

    private String markingNbrs;

    private String paymentTerm;

    private String battery;

    private String dgDeclaration;

    private String containerNo;

    private String truckNoExoem;

    private String truckNoExtrucker;

    private String truckNoBorderexchange;

    private String elockExoem;

    private String elockExtrucker;

    private String broker;

    private String truckNo;

    private String loadListNo;

    private String salesNo;

    private String customsId;

    private String ccm;

    private String pod;

    private String dpc;

    private String containerWeight;

    private String manifest;

    private String svcNo;

    private String terminal;

    private String scacFwd;

    private String scacTrucker;

    private String sealnrTruck;

    private String sealnrOceancontainer;

    private String vesselImo;

    private BigDecimal dwt;

    private BigDecimal portToPortDistance;

    private BigDecimal vesselDistanceTraveled;

    private String fastBoatService;

    private String standardOceanService;

    private String icaoFlightCode;

    private String aircraftType;

    private String aircraftName;

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

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public String getOem() {
        return oem;
    }

    public void setOem(String oem) {
        this.oem = oem == null ? null : oem.trim();
    }

    public Date getShipdate() {
        return shipdate;
    }

    public void setShipdate(Date shipdate) {
        this.shipdate = shipdate;
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

    public String getPallets() {
        return pallets;
    }

    public void setPallets(String pallets) {
        this.pallets = pallets == null ? null : pallets.trim();
    }

    public String getPalletIdOem() {
        return palletIdOem;
    }

    public void setPalletIdOem(String palletIdOem) {
        this.palletIdOem = palletIdOem == null ? null : palletIdOem.trim();
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

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public String getAppledn() {
        return appledn;
    }

    public void setAppledn(String appledn) {
        this.appledn = appledn == null ? null : appledn.trim();
    }

    public String getPono() {
        return pono;
    }

    public void setPono(String pono) {
        this.pono = pono == null ? null : pono.trim();
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId == null ? null : shipmentId.trim();
    }

    public String getLineItem() {
        return lineItem;
    }

    public void setLineItem(String lineItem) {
        this.lineItem = lineItem == null ? null : lineItem.trim();
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode == null ? null : projectCode.trim();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getLineItemQuantity() {
        return lineItemQuantity;
    }

    public void setLineItemQuantity(String lineItemQuantity) {
        this.lineItemQuantity = lineItemQuantity == null ? null : lineItemQuantity.trim();
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

    public Integer getSlac() {
        return slac;
    }

    public void setSlac(Integer slac) {
        this.slac = slac;
    }

    public BigDecimal getCbm() {
        return cbm;
    }

    public void setCbm(BigDecimal cbm) {
        this.cbm = cbm;
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

    public String getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(String serviceLevel) {
        this.serviceLevel = serviceLevel == null ? null : serviceLevel.trim();
    }

    public String getServiceLevelId() {
        return serviceLevelId;
    }

    public void setServiceLevelId(String serviceLevelId) {
        this.serviceLevelId = serviceLevelId == null ? null : serviceLevelId.trim();
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

    public String getMarkingNbrs() {
        return markingNbrs;
    }

    public void setMarkingNbrs(String markingNbrs) {
        this.markingNbrs = markingNbrs == null ? null : markingNbrs.trim();
    }

    public String getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(String paymentTerm) {
        this.paymentTerm = paymentTerm == null ? null : paymentTerm.trim();
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery == null ? null : battery.trim();
    }

    public String getDgDeclaration() {
        return dgDeclaration;
    }

    public void setDgDeclaration(String dgDeclaration) {
        this.dgDeclaration = dgDeclaration == null ? null : dgDeclaration.trim();
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

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker == null ? null : broker.trim();
    }

    public String getTruckNo() {
        return truckNo;
    }

    public void setTruckNo(String truckNo) {
        this.truckNo = truckNo == null ? null : truckNo.trim();
    }

    public String getLoadListNo() {
        return loadListNo;
    }

    public void setLoadListNo(String loadListNo) {
        this.loadListNo = loadListNo == null ? null : loadListNo.trim();
    }

    public String getSalesNo() {
        return salesNo;
    }

    public void setSalesNo(String salesNo) {
        this.salesNo = salesNo == null ? null : salesNo.trim();
    }

    public String getCustomsId() {
        return customsId;
    }

    public void setCustomsId(String customsId) {
        this.customsId = customsId == null ? null : customsId.trim();
    }

    public String getCcm() {
        return ccm;
    }

    public void setCcm(String ccm) {
        this.ccm = ccm == null ? null : ccm.trim();
    }

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod == null ? null : pod.trim();
    }

    public String getDpc() {
        return dpc;
    }

    public void setDpc(String dpc) {
        this.dpc = dpc == null ? null : dpc.trim();
    }

    public String getContainerWeight() {
        return containerWeight;
    }

    public void setContainerWeight(String containerWeight) {
        this.containerWeight = containerWeight == null ? null : containerWeight.trim();
    }

    public String getManifest() {
        return manifest;
    }

    public void setManifest(String manifest) {
        this.manifest = manifest == null ? null : manifest.trim();
    }

    public String getSvcNo() {
        return svcNo;
    }

    public void setSvcNo(String svcNo) {
        this.svcNo = svcNo == null ? null : svcNo.trim();
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

    public String getSealnrTruck() {
        return sealnrTruck;
    }

    public void setSealnrTruck(String sealnrTruck) {
        this.sealnrTruck = sealnrTruck == null ? null : sealnrTruck.trim();
    }

    public String getSealnrOceancontainer() {
        return sealnrOceancontainer;
    }

    public void setSealnrOceancontainer(String sealnrOceancontainer) {
        this.sealnrOceancontainer = sealnrOceancontainer == null ? null : sealnrOceancontainer.trim();
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

    public String getAircraftName() {
        return aircraftName;
    }

    public void setAircraftName(String aircraftName) {
        this.aircraftName = aircraftName == null ? null : aircraftName.trim();
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
}