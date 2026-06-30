package com.boot.security.server.dto;

import com.boot.security.server.model.EDI210Schema;

/**
 * EDI 210 导出 / 导入宽表行 DTO（每行对应 Excel 的一行：113 列）
 *
 * <p>由 SQL JOIN 得到，每条 charge 一行；toArray() 输出供 ExcelUtil 直接写入。
 *
 * <p>所有字段均为字符串，与表结构 VARCHAR 风格一致。
 */
public class EDI210ExcelRowDTO {
    // 0 Region
    private String region;
    // Bill-To 1~10
    private String btName1, btName2, btAddress1, btAddress2, btCity, btCountry, btPostalCode, btStateProvince, btAccount, btLocation;
    // Bill-From 11~15
    private String bfName, bfAddress, bfCity, bfPostalCode, bfCountry;
    // Shipper 16~25
    private String shName1, shName2, shAddress1, shAddress2, shCity, shCountry, shPostalCode, shStateProvince, shAccount, shLocation;
    // Consignee 26~35
    private String cnName1, cnName2, cnAddress1, cnAddress2, cnCity, cnCountry, cnPostalCode, cnStateProvince, cnAccount, cnLocation;
    // Billing Basic 36~54
    private String scac, invNo, invDate, paymentTerms, cur, invAmount, ttCharge,
            pickupDate, pickupTime, podDate, podTime, podName,
            grossWeight, gwQualifier, gwUnit, billWeight, bwQualifier, bwUnit, bol;
    // Billing Details 55~62
    private String description, rate, rateQual, ratedCurrency, exchangeRate, expressedCurrency, charge, weight;
    // Rated as Weight 63~65
    private String weightQual, weightType, spChargeCode;
    // Additional 66~71
    private String spChargeDesc, equipmentCode, equipmentType, totalWeight, totalWeightQual, serviceCode;
    // Volume 72~73
    private String volume, volumeQual;
    // Rated as Quantity 74~75
    private String ratedQuantity, ratedPackageType;
    // Billed as Quantity 76~77
    private String billedQuantity, billedQualifier;
    // Measurement 78~81
    private String length, width, height, measurementUnit;
    // Charge 明细级 82~91
    private String declaredValue, commodityClass, commodityCode, customerReference, correctionIndicator,
            carriersDescription, carriersQuantity, transportService, originZone, destinationZone;
    // Tax/Reference 92~112
    private String po, dn, rma, hawb, so, billOfLadingNumber, trackingNumber,
            customerVatRegistration, carrierVatRegistration,
            billingAccount, divisionIdentifier, billToAccount,
            invoiceStandardCode, carrierName, businessUnit, freightBillNumber,
            carrierTaxIdentity, senderTaxIdentity, recipientTaxIdentity,
            taxBureauName, taxBureauCode;

    /**
     * 转换为按 EDI210Schema 列序排列的对象数组（供 ExcelUtil 写入）
     */
    public Object[] toArray() {
        Object[] arr = new Object[EDI210Schema.TOTAL_COLUMNS];
        arr[EDI210Schema.COL_REGION] = region;
        arr[EDI210Schema.COL_BT_NAME1] = btName1;
        arr[EDI210Schema.COL_BT_NAME2] = btName2;
        arr[EDI210Schema.COL_BT_ADDR1] = btAddress1;
        arr[EDI210Schema.COL_BT_ADDR2] = btAddress2;
        arr[EDI210Schema.COL_BT_CITY] = btCity;
        arr[EDI210Schema.COL_BT_COUNTRY] = btCountry;
        arr[EDI210Schema.COL_BT_POSTAL] = btPostalCode;
        arr[EDI210Schema.COL_BT_STATE] = btStateProvince;
        arr[EDI210Schema.COL_BT_ACCOUNT] = btAccount;
        arr[EDI210Schema.COL_BT_LOCATION] = btLocation;
        arr[EDI210Schema.COL_BF_NAME] = bfName;
        arr[EDI210Schema.COL_BF_ADDRESS] = bfAddress;
        arr[EDI210Schema.COL_BF_CITY] = bfCity;
        arr[EDI210Schema.COL_BF_POSTAL] = bfPostalCode;
        arr[EDI210Schema.COL_BF_COUNTRY] = bfCountry;
        arr[EDI210Schema.COL_SH_NAME1] = shName1;
        arr[EDI210Schema.COL_SH_NAME2] = shName2;
        arr[EDI210Schema.COL_SH_ADDR1] = shAddress1;
        arr[EDI210Schema.COL_SH_ADDR2] = shAddress2;
        arr[EDI210Schema.COL_SH_CITY] = shCity;
        arr[EDI210Schema.COL_SH_COUNTRY] = shCountry;
        arr[EDI210Schema.COL_SH_POSTAL] = shPostalCode;
        arr[EDI210Schema.COL_SH_STATE] = shStateProvince;
        arr[EDI210Schema.COL_SH_ACCOUNT] = shAccount;
        arr[EDI210Schema.COL_SH_LOCATION] = shLocation;
        arr[EDI210Schema.COL_CN_NAME1] = cnName1;
        arr[EDI210Schema.COL_CN_NAME2] = cnName2;
        arr[EDI210Schema.COL_CN_ADDR1] = cnAddress1;
        arr[EDI210Schema.COL_CN_ADDR2] = cnAddress2;
        arr[EDI210Schema.COL_CN_CITY] = cnCity;
        arr[EDI210Schema.COL_CN_COUNTRY] = cnCountry;
        arr[EDI210Schema.COL_CN_POSTAL] = cnPostalCode;
        arr[EDI210Schema.COL_CN_STATE] = cnStateProvince;
        arr[EDI210Schema.COL_CN_ACCOUNT] = cnAccount;
        arr[EDI210Schema.COL_CN_LOCATION] = cnLocation;
        arr[EDI210Schema.COL_SCAC] = scac;
        arr[EDI210Schema.COL_INV_NO] = invNo;
        arr[EDI210Schema.COL_INV_DATE] = invDate;
        arr[EDI210Schema.COL_PAYMENT_TERMS] = paymentTerms;
        arr[EDI210Schema.COL_CUR] = cur;
        arr[EDI210Schema.COL_INV_AMOUNT] = invAmount;
        arr[EDI210Schema.COL_TT_CHARGE] = ttCharge;
        arr[EDI210Schema.COL_PICKUP_DATE] = pickupDate;
        arr[EDI210Schema.COL_PICKUP_TIME] = pickupTime;
        arr[EDI210Schema.COL_POD_DATE] = podDate;
        arr[EDI210Schema.COL_POD_TIME] = podTime;
        arr[EDI210Schema.COL_POD_NAME] = podName;
        arr[EDI210Schema.COL_GROSS_WEIGHT] = grossWeight;
        arr[EDI210Schema.COL_GW_QUALIFIER] = gwQualifier;
        arr[EDI210Schema.COL_GW_UNIT] = gwUnit;
        arr[EDI210Schema.COL_BILL_WEIGHT] = billWeight;
        arr[EDI210Schema.COL_BW_QUALIFIER] = bwQualifier;
        arr[EDI210Schema.COL_BW_UNIT] = bwUnit;
        arr[EDI210Schema.COL_BOL] = bol;
        arr[EDI210Schema.COL_DESCRIPTION] = description;
        arr[EDI210Schema.COL_RATE] = rate;
        arr[EDI210Schema.COL_RATE_QUAL] = rateQual;
        arr[EDI210Schema.COL_RATED_CURRENCY] = ratedCurrency;
        arr[EDI210Schema.COL_EXCHANGE_RATE] = exchangeRate;
        arr[EDI210Schema.COL_EXPRESSED_CURRENCY] = expressedCurrency;
        arr[EDI210Schema.COL_CHARGE] = charge;
        arr[EDI210Schema.COL_WEIGHT] = weight;
        arr[EDI210Schema.COL_WEIGHT_QUAL] = weightQual;
        arr[EDI210Schema.COL_WEIGHT_TYPE] = weightType;
        arr[EDI210Schema.COL_SP_CHARGE_CODE] = spChargeCode;
        arr[EDI210Schema.COL_SP_CHARGE_DESC] = spChargeDesc;
        arr[EDI210Schema.COL_EQUIPMENT_CODE] = equipmentCode;
        arr[EDI210Schema.COL_EQUIPMENT_TYPE] = equipmentType;
        arr[EDI210Schema.COL_TOTAL_WEIGHT] = totalWeight;
        arr[EDI210Schema.COL_TOTAL_WEIGHT_QUAL] = totalWeightQual;
        arr[EDI210Schema.COL_SERVICE_CODE] = serviceCode;
        arr[EDI210Schema.COL_VOLUME] = volume;
        arr[EDI210Schema.COL_VOLUME_QUAL] = volumeQual;
        arr[EDI210Schema.COL_RATED_QUANTITY] = ratedQuantity;
        arr[EDI210Schema.COL_RATED_PACKAGE_TYPE] = ratedPackageType;
        arr[EDI210Schema.COL_BILLED_QUANTITY] = billedQuantity;
        arr[EDI210Schema.COL_BILLED_QUALIFIER] = billedQualifier;
        arr[EDI210Schema.COL_LENGTH] = length;
        arr[EDI210Schema.COL_WIDTH] = width;
        arr[EDI210Schema.COL_HEIGHT] = height;
        arr[EDI210Schema.COL_MEASUREMENT_UNIT] = measurementUnit;
        arr[EDI210Schema.COL_DECLARED_VALUE] = declaredValue;
        arr[EDI210Schema.COL_COMMODITY_CLASS] = commodityClass;
        arr[EDI210Schema.COL_COMMODITY_CODE] = commodityCode;
        arr[EDI210Schema.COL_CUSTOMER_REFERENCE] = customerReference;
        arr[EDI210Schema.COL_CORRECTION_INDICATOR] = correctionIndicator;
        arr[EDI210Schema.COL_CARRIERS_DESCRIPTION] = carriersDescription;
        arr[EDI210Schema.COL_CARRIERS_QUANTITY] = carriersQuantity;
        arr[EDI210Schema.COL_TRANSPORT_SERVICE] = transportService;
        arr[EDI210Schema.COL_ORIGIN_ZONE] = originZone;
        arr[EDI210Schema.COL_DESTINATION_ZONE] = destinationZone;
        arr[EDI210Schema.COL_PO] = po;
        arr[EDI210Schema.COL_DN] = dn;
        arr[EDI210Schema.COL_RMA] = rma;
        arr[EDI210Schema.COL_HAWB] = hawb;
        arr[EDI210Schema.COL_SO] = so;
        arr[EDI210Schema.COL_BILL_OF_LADING_NUMBER] = billOfLadingNumber;
        arr[EDI210Schema.COL_TRACKING_NUMBER] = trackingNumber;
        arr[EDI210Schema.COL_CUSTOMER_VAT_REG] = customerVatRegistration;
        arr[EDI210Schema.COL_CARRIER_VAT_REG] = carrierVatRegistration;
        arr[EDI210Schema.COL_BILLING_ACCOUNT] = billingAccount;
        arr[EDI210Schema.COL_DIVISION_IDENTIFIER] = divisionIdentifier;
        arr[EDI210Schema.COL_BILL_TO_ACCOUNT] = billToAccount;
        arr[EDI210Schema.COL_INVOICE_STANDARD_CODE] = invoiceStandardCode;
        arr[EDI210Schema.COL_CARRIER_NAME] = carrierName;
        arr[EDI210Schema.COL_BUSINESS_UNIT] = businessUnit;
        arr[EDI210Schema.COL_FREIGHT_BILL_NUMBER] = freightBillNumber;
        arr[EDI210Schema.COL_CARRIER_TAX_IDENTITY] = carrierTaxIdentity;
        arr[EDI210Schema.COL_SENDER_TAX_IDENTITY] = senderTaxIdentity;
        arr[EDI210Schema.COL_RECIPIENT_TAX_IDENTITY] = recipientTaxIdentity;
        arr[EDI210Schema.COL_TAX_BUREAU_NAME] = taxBureauName;
        arr[EDI210Schema.COL_TAX_BUREAU_CODE] = taxBureauCode;
        return arr;
    }

    // ==================== getters & setters ====================
    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }
    public String getBtName1() { return btName1; }
    public void setBtName1(String btName1) { this.btName1 = btName1; }
    public String getBtName2() { return btName2; }
    public void setBtName2(String btName2) { this.btName2 = btName2; }
    public String getBtAddress1() { return btAddress1; }
    public void setBtAddress1(String btAddress1) { this.btAddress1 = btAddress1; }
    public String getBtAddress2() { return btAddress2; }
    public void setBtAddress2(String btAddress2) { this.btAddress2 = btAddress2; }
    public String getBtCity() { return btCity; }
    public void setBtCity(String btCity) { this.btCity = btCity; }
    public String getBtCountry() { return btCountry; }
    public void setBtCountry(String btCountry) { this.btCountry = btCountry; }
    public String getBtPostalCode() { return btPostalCode; }
    public void setBtPostalCode(String btPostalCode) { this.btPostalCode = btPostalCode; }
    public String getBtStateProvince() { return btStateProvince; }
    public void setBtStateProvince(String btStateProvince) { this.btStateProvince = btStateProvince; }
    public String getBtAccount() { return btAccount; }
    public void setBtAccount(String btAccount) { this.btAccount = btAccount; }
    public String getBtLocation() { return btLocation; }
    public void setBtLocation(String btLocation) { this.btLocation = btLocation; }
    public String getBfName() { return bfName; }
    public void setBfName(String bfName) { this.bfName = bfName; }
    public String getBfAddress() { return bfAddress; }
    public void setBfAddress(String bfAddress) { this.bfAddress = bfAddress; }
    public String getBfCity() { return bfCity; }
    public void setBfCity(String bfCity) { this.bfCity = bfCity; }
    public String getBfPostalCode() { return bfPostalCode; }
    public void setBfPostalCode(String bfPostalCode) { this.bfPostalCode = bfPostalCode; }
    public String getBfCountry() { return bfCountry; }
    public void setBfCountry(String bfCountry) { this.bfCountry = bfCountry; }
    public String getShName1() { return shName1; }
    public void setShName1(String shName1) { this.shName1 = shName1; }
    public String getShName2() { return shName2; }
    public void setShName2(String shName2) { this.shName2 = shName2; }
    public String getShAddress1() { return shAddress1; }
    public void setShAddress1(String shAddress1) { this.shAddress1 = shAddress1; }
    public String getShAddress2() { return shAddress2; }
    public void setShAddress2(String shAddress2) { this.shAddress2 = shAddress2; }
    public String getShCity() { return shCity; }
    public void setShCity(String shCity) { this.shCity = shCity; }
    public String getShCountry() { return shCountry; }
    public void setShCountry(String shCountry) { this.shCountry = shCountry; }
    public String getShPostalCode() { return shPostalCode; }
    public void setShPostalCode(String shPostalCode) { this.shPostalCode = shPostalCode; }
    public String getShStateProvince() { return shStateProvince; }
    public void setShStateProvince(String shStateProvince) { this.shStateProvince = shStateProvince; }
    public String getShAccount() { return shAccount; }
    public void setShAccount(String shAccount) { this.shAccount = shAccount; }
    public String getShLocation() { return shLocation; }
    public void setShLocation(String shLocation) { this.shLocation = shLocation; }
    public String getCnName1() { return cnName1; }
    public void setCnName1(String cnName1) { this.cnName1 = cnName1; }
    public String getCnName2() { return cnName2; }
    public void setCnName2(String cnName2) { this.cnName2 = cnName2; }
    public String getCnAddress1() { return cnAddress1; }
    public void setCnAddress1(String cnAddress1) { this.cnAddress1 = cnAddress1; }
    public String getCnAddress2() { return cnAddress2; }
    public void setCnAddress2(String cnAddress2) { this.cnAddress2 = cnAddress2; }
    public String getCnCity() { return cnCity; }
    public void setCnCity(String cnCity) { this.cnCity = cnCity; }
    public String getCnCountry() { return cnCountry; }
    public void setCnCountry(String cnCountry) { this.cnCountry = cnCountry; }
    public String getCnPostalCode() { return cnPostalCode; }
    public void setCnPostalCode(String cnPostalCode) { this.cnPostalCode = cnPostalCode; }
    public String getCnStateProvince() { return cnStateProvince; }
    public void setCnStateProvince(String cnStateProvince) { this.cnStateProvince = cnStateProvince; }
    public String getCnAccount() { return cnAccount; }
    public void setCnAccount(String cnAccount) { this.cnAccount = cnAccount; }
    public String getCnLocation() { return cnLocation; }
    public void setCnLocation(String cnLocation) { this.cnLocation = cnLocation; }
    public String getScac() { return scac; }
    public void setScac(String scac) { this.scac = scac; }
    public String getInvNo() { return invNo; }
    public void setInvNo(String invNo) { this.invNo = invNo; }
    public String getInvDate() { return invDate; }
    public void setInvDate(String invDate) { this.invDate = invDate; }
    public String getPaymentTerms() { return paymentTerms; }
    public void setPaymentTerms(String paymentTerms) { this.paymentTerms = paymentTerms; }
    public String getCur() { return cur; }
    public void setCur(String cur) { this.cur = cur; }
    public String getInvAmount() { return invAmount; }
    public void setInvAmount(String invAmount) { this.invAmount = invAmount; }
    public String getTtCharge() { return ttCharge; }
    public void setTtCharge(String ttCharge) { this.ttCharge = ttCharge; }
    public String getPickupDate() { return pickupDate; }
    public void setPickupDate(String pickupDate) { this.pickupDate = pickupDate; }
    public String getPickupTime() { return pickupTime; }
    public void setPickupTime(String pickupTime) { this.pickupTime = pickupTime; }
    public String getPodDate() { return podDate; }
    public void setPodDate(String podDate) { this.podDate = podDate; }
    public String getPodTime() { return podTime; }
    public void setPodTime(String podTime) { this.podTime = podTime; }
    public String getPodName() { return podName; }
    public void setPodName(String podName) { this.podName = podName; }
    public String getGrossWeight() { return grossWeight; }
    public void setGrossWeight(String grossWeight) { this.grossWeight = grossWeight; }
    public String getGwQualifier() { return gwQualifier; }
    public void setGwQualifier(String gwQualifier) { this.gwQualifier = gwQualifier; }
    public String getGwUnit() { return gwUnit; }
    public void setGwUnit(String gwUnit) { this.gwUnit = gwUnit; }
    public String getBillWeight() { return billWeight; }
    public void setBillWeight(String billWeight) { this.billWeight = billWeight; }
    public String getBwQualifier() { return bwQualifier; }
    public void setBwQualifier(String bwQualifier) { this.bwQualifier = bwQualifier; }
    public String getBwUnit() { return bwUnit; }
    public void setBwUnit(String bwUnit) { this.bwUnit = bwUnit; }
    public String getBol() { return bol; }
    public void setBol(String bol) { this.bol = bol; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getRate() { return rate; }
    public void setRate(String rate) { this.rate = rate; }
    public String getRateQual() { return rateQual; }
    public void setRateQual(String rateQual) { this.rateQual = rateQual; }
    public String getRatedCurrency() { return ratedCurrency; }
    public void setRatedCurrency(String ratedCurrency) { this.ratedCurrency = ratedCurrency; }
    public String getExchangeRate() { return exchangeRate; }
    public void setExchangeRate(String exchangeRate) { this.exchangeRate = exchangeRate; }
    public String getExpressedCurrency() { return expressedCurrency; }
    public void setExpressedCurrency(String expressedCurrency) { this.expressedCurrency = expressedCurrency; }
    public String getCharge() { return charge; }
    public void setCharge(String charge) { this.charge = charge; }
    public String getWeight() { return weight; }
    public void setWeight(String weight) { this.weight = weight; }
    public String getWeightQual() { return weightQual; }
    public void setWeightQual(String weightQual) { this.weightQual = weightQual; }
    public String getWeightType() { return weightType; }
    public void setWeightType(String weightType) { this.weightType = weightType; }
    public String getSpChargeCode() { return spChargeCode; }
    public void setSpChargeCode(String spChargeCode) { this.spChargeCode = spChargeCode; }
    public String getSpChargeDesc() { return spChargeDesc; }
    public void setSpChargeDesc(String spChargeDesc) { this.spChargeDesc = spChargeDesc; }
    public String getEquipmentCode() { return equipmentCode; }
    public void setEquipmentCode(String equipmentCode) { this.equipmentCode = equipmentCode; }
    public String getEquipmentType() { return equipmentType; }
    public void setEquipmentType(String equipmentType) { this.equipmentType = equipmentType; }
    public String getTotalWeight() { return totalWeight; }
    public void setTotalWeight(String totalWeight) { this.totalWeight = totalWeight; }
    public String getTotalWeightQual() { return totalWeightQual; }
    public void setTotalWeightQual(String totalWeightQual) { this.totalWeightQual = totalWeightQual; }
    public String getServiceCode() { return serviceCode; }
    public void setServiceCode(String serviceCode) { this.serviceCode = serviceCode; }
    public String getVolume() { return volume; }
    public void setVolume(String volume) { this.volume = volume; }
    public String getVolumeQual() { return volumeQual; }
    public void setVolumeQual(String volumeQual) { this.volumeQual = volumeQual; }
    public String getRatedQuantity() { return ratedQuantity; }
    public void setRatedQuantity(String ratedQuantity) { this.ratedQuantity = ratedQuantity; }
    public String getRatedPackageType() { return ratedPackageType; }
    public void setRatedPackageType(String ratedPackageType) { this.ratedPackageType = ratedPackageType; }
    public String getBilledQuantity() { return billedQuantity; }
    public void setBilledQuantity(String billedQuantity) { this.billedQuantity = billedQuantity; }
    public String getBilledQualifier() { return billedQualifier; }
    public void setBilledQualifier(String billedQualifier) { this.billedQualifier = billedQualifier; }
    public String getLength() { return length; }
    public void setLength(String length) { this.length = length; }
    public String getWidth() { return width; }
    public void setWidth(String width) { this.width = width; }
    public String getHeight() { return height; }
    public void setHeight(String height) { this.height = height; }
    public String getMeasurementUnit() { return measurementUnit; }
    public void setMeasurementUnit(String measurementUnit) { this.measurementUnit = measurementUnit; }
    public String getDeclaredValue() { return declaredValue; }
    public void setDeclaredValue(String declaredValue) { this.declaredValue = declaredValue; }
    public String getCommodityClass() { return commodityClass; }
    public void setCommodityClass(String commodityClass) { this.commodityClass = commodityClass; }
    public String getCommodityCode() { return commodityCode; }
    public void setCommodityCode(String commodityCode) { this.commodityCode = commodityCode; }
    public String getCustomerReference() { return customerReference; }
    public void setCustomerReference(String customerReference) { this.customerReference = customerReference; }
    public String getCorrectionIndicator() { return correctionIndicator; }
    public void setCorrectionIndicator(String correctionIndicator) { this.correctionIndicator = correctionIndicator; }
    public String getCarriersDescription() { return carriersDescription; }
    public void setCarriersDescription(String carriersDescription) { this.carriersDescription = carriersDescription; }
    public String getCarriersQuantity() { return carriersQuantity; }
    public void setCarriersQuantity(String carriersQuantity) { this.carriersQuantity = carriersQuantity; }
    public String getTransportService() { return transportService; }
    public void setTransportService(String transportService) { this.transportService = transportService; }
    public String getOriginZone() { return originZone; }
    public void setOriginZone(String originZone) { this.originZone = originZone; }
    public String getDestinationZone() { return destinationZone; }
    public void setDestinationZone(String destinationZone) { this.destinationZone = destinationZone; }
    public String getPo() { return po; }
    public void setPo(String po) { this.po = po; }
    public String getDn() { return dn; }
    public void setDn(String dn) { this.dn = dn; }
    public String getRma() { return rma; }
    public void setRma(String rma) { this.rma = rma; }
    public String getHawb() { return hawb; }
    public void setHawb(String hawb) { this.hawb = hawb; }
    public String getSo() { return so; }
    public void setSo(String so) { this.so = so; }
    public String getBillOfLadingNumber() { return billOfLadingNumber; }
    public void setBillOfLadingNumber(String billOfLadingNumber) { this.billOfLadingNumber = billOfLadingNumber; }
    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }
    public String getCustomerVatRegistration() { return customerVatRegistration; }
    public void setCustomerVatRegistration(String customerVatRegistration) { this.customerVatRegistration = customerVatRegistration; }
    public String getCarrierVatRegistration() { return carrierVatRegistration; }
    public void setCarrierVatRegistration(String carrierVatRegistration) { this.carrierVatRegistration = carrierVatRegistration; }
    public String getBillingAccount() { return billingAccount; }
    public void setBillingAccount(String billingAccount) { this.billingAccount = billingAccount; }
    public String getDivisionIdentifier() { return divisionIdentifier; }
    public void setDivisionIdentifier(String divisionIdentifier) { this.divisionIdentifier = divisionIdentifier; }
    public String getBillToAccount() { return billToAccount; }
    public void setBillToAccount(String billToAccount) { this.billToAccount = billToAccount; }
    public String getInvoiceStandardCode() { return invoiceStandardCode; }
    public void setInvoiceStandardCode(String invoiceStandardCode) { this.invoiceStandardCode = invoiceStandardCode; }
    public String getCarrierName() { return carrierName; }
    public void setCarrierName(String carrierName) { this.carrierName = carrierName; }
    public String getBusinessUnit() { return businessUnit; }
    public void setBusinessUnit(String businessUnit) { this.businessUnit = businessUnit; }
    public String getFreightBillNumber() { return freightBillNumber; }
    public void setFreightBillNumber(String freightBillNumber) { this.freightBillNumber = freightBillNumber; }
    public String getCarrierTaxIdentity() { return carrierTaxIdentity; }
    public void setCarrierTaxIdentity(String carrierTaxIdentity) { this.carrierTaxIdentity = carrierTaxIdentity; }
    public String getSenderTaxIdentity() { return senderTaxIdentity; }
    public void setSenderTaxIdentity(String senderTaxIdentity) { this.senderTaxIdentity = senderTaxIdentity; }
    public String getRecipientTaxIdentity() { return recipientTaxIdentity; }
    public void setRecipientTaxIdentity(String recipientTaxIdentity) { this.recipientTaxIdentity = recipientTaxIdentity; }
    public String getTaxBureauName() { return taxBureauName; }
    public void setTaxBureauName(String taxBureauName) { this.taxBureauName = taxBureauName; }
    public String getTaxBureauCode() { return taxBureauCode; }
    public void setTaxBureauCode(String taxBureauCode) { this.taxBureauCode = taxBureauCode; }
}
