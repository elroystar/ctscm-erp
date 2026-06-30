package com.boot.security.server.model;

/**
 * EDI 210 计费明细
 */
public class EDI210Charge {
    private Long id;
    private Long invoiceId;
    private Integer lineNo;
    private String description;
    private String rate;
    private String rateQual;
    private String ratedCurrency;
    private String exchangeRate;
    private String expressedCurrency;
    private String charge;
    private String weight;
    private String weightQual;
    private String weightType;
    private String spChargeCode;
    private String spChargeDesc;
    private String equipmentCode;
    private String equipmentType;
    private String totalWeight;
    private String totalWeightQual;
    private String serviceCode;
    private String volume;
    private String volumeQual;
    private String ratedQuantity;
    private String ratedPackageType;
    private String billedQuantity;
    private String billedQualifier;
    private String length;
    private String width;
    private String height;
    private String measurementUnit;
    private String declaredValue;
    private String commodityClass;
    private String commodityCode;
    private String correctionIndicator;
    private String carriersDescription;
    private String carriersQuantity;
    private String transportService;
    private String originZone;
    private String destinationZone;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getInvoiceId() { return invoiceId; }
    public void setInvoiceId(Long invoiceId) { this.invoiceId = invoiceId; }
    public Integer getLineNo() { return lineNo; }
    public void setLineNo(Integer lineNo) { this.lineNo = lineNo; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description == null ? null : description.trim(); }
    public String getRate() { return rate; }
    public void setRate(String rate) { this.rate = rate == null ? null : rate.trim(); }
    public String getRateQual() { return rateQual; }
    public void setRateQual(String rateQual) { this.rateQual = rateQual == null ? null : rateQual.trim(); }
    public String getRatedCurrency() { return ratedCurrency; }
    public void setRatedCurrency(String ratedCurrency) { this.ratedCurrency = ratedCurrency == null ? null : ratedCurrency.trim(); }
    public String getExchangeRate() { return exchangeRate; }
    public void setExchangeRate(String exchangeRate) { this.exchangeRate = exchangeRate == null ? null : exchangeRate.trim(); }
    public String getExpressedCurrency() { return expressedCurrency; }
    public void setExpressedCurrency(String expressedCurrency) { this.expressedCurrency = expressedCurrency == null ? null : expressedCurrency.trim(); }
    public String getCharge() { return charge; }
    public void setCharge(String charge) { this.charge = charge == null ? null : charge.trim(); }
    public String getWeight() { return weight; }
    public void setWeight(String weight) { this.weight = weight == null ? null : weight.trim(); }
    public String getWeightQual() { return weightQual; }
    public void setWeightQual(String weightQual) { this.weightQual = weightQual == null ? null : weightQual.trim(); }
    public String getWeightType() { return weightType; }
    public void setWeightType(String weightType) { this.weightType = weightType == null ? null : weightType.trim(); }
    public String getSpChargeCode() { return spChargeCode; }
    public void setSpChargeCode(String spChargeCode) { this.spChargeCode = spChargeCode == null ? null : spChargeCode.trim(); }
    public String getSpChargeDesc() { return spChargeDesc; }
    public void setSpChargeDesc(String spChargeDesc) { this.spChargeDesc = spChargeDesc == null ? null : spChargeDesc.trim(); }
    public String getEquipmentCode() { return equipmentCode; }
    public void setEquipmentCode(String equipmentCode) { this.equipmentCode = equipmentCode == null ? null : equipmentCode.trim(); }
    public String getEquipmentType() { return equipmentType; }
    public void setEquipmentType(String equipmentType) { this.equipmentType = equipmentType == null ? null : equipmentType.trim(); }
    public String getTotalWeight() { return totalWeight; }
    public void setTotalWeight(String totalWeight) { this.totalWeight = totalWeight == null ? null : totalWeight.trim(); }
    public String getTotalWeightQual() { return totalWeightQual; }
    public void setTotalWeightQual(String totalWeightQual) { this.totalWeightQual = totalWeightQual == null ? null : totalWeightQual.trim(); }
    public String getServiceCode() { return serviceCode; }
    public void setServiceCode(String serviceCode) { this.serviceCode = serviceCode == null ? null : serviceCode.trim(); }
    public String getVolume() { return volume; }
    public void setVolume(String volume) { this.volume = volume == null ? null : volume.trim(); }
    public String getVolumeQual() { return volumeQual; }
    public void setVolumeQual(String volumeQual) { this.volumeQual = volumeQual == null ? null : volumeQual.trim(); }
    public String getRatedQuantity() { return ratedQuantity; }
    public void setRatedQuantity(String ratedQuantity) { this.ratedQuantity = ratedQuantity == null ? null : ratedQuantity.trim(); }
    public String getRatedPackageType() { return ratedPackageType; }
    public void setRatedPackageType(String ratedPackageType) { this.ratedPackageType = ratedPackageType == null ? null : ratedPackageType.trim(); }
    public String getBilledQuantity() { return billedQuantity; }
    public void setBilledQuantity(String billedQuantity) { this.billedQuantity = billedQuantity == null ? null : billedQuantity.trim(); }
    public String getBilledQualifier() { return billedQualifier; }
    public void setBilledQualifier(String billedQualifier) { this.billedQualifier = billedQualifier == null ? null : billedQualifier.trim(); }
    public String getLength() { return length; }
    public void setLength(String length) { this.length = length == null ? null : length.trim(); }
    public String getWidth() { return width; }
    public void setWidth(String width) { this.width = width == null ? null : width.trim(); }
    public String getHeight() { return height; }
    public void setHeight(String height) { this.height = height == null ? null : height.trim(); }
    public String getMeasurementUnit() { return measurementUnit; }
    public void setMeasurementUnit(String measurementUnit) { this.measurementUnit = measurementUnit == null ? null : measurementUnit.trim(); }
    public String getDeclaredValue() { return declaredValue; }
    public void setDeclaredValue(String declaredValue) { this.declaredValue = declaredValue == null ? null : declaredValue.trim(); }
    public String getCommodityClass() { return commodityClass; }
    public void setCommodityClass(String commodityClass) { this.commodityClass = commodityClass == null ? null : commodityClass.trim(); }
    public String getCommodityCode() { return commodityCode; }
    public void setCommodityCode(String commodityCode) { this.commodityCode = commodityCode == null ? null : commodityCode.trim(); }
    public String getCorrectionIndicator() { return correctionIndicator; }
    public void setCorrectionIndicator(String correctionIndicator) { this.correctionIndicator = correctionIndicator == null ? null : correctionIndicator.trim(); }
    public String getCarriersDescription() { return carriersDescription; }
    public void setCarriersDescription(String carriersDescription) { this.carriersDescription = carriersDescription == null ? null : carriersDescription.trim(); }
    public String getCarriersQuantity() { return carriersQuantity; }
    public void setCarriersQuantity(String carriersQuantity) { this.carriersQuantity = carriersQuantity == null ? null : carriersQuantity.trim(); }
    public String getTransportService() { return transportService; }
    public void setTransportService(String transportService) { this.transportService = transportService == null ? null : transportService.trim(); }
    public String getOriginZone() { return originZone; }
    public void setOriginZone(String originZone) { this.originZone = originZone == null ? null : originZone.trim(); }
    public String getDestinationZone() { return destinationZone; }
    public void setDestinationZone(String destinationZone) { this.destinationZone = destinationZone == null ? null : destinationZone.trim(); }
}
