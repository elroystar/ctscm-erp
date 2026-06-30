package com.boot.security.server.model;

/**
 * EDI 210 税务及业务参考编号（与发票 1:1）
 */
public class EDI210Tax {
    private Long invoiceId;
    private String customerReference;
    private String po;
    private String dn;
    private String rma;
    private String hawb;
    private String so;
    private String billOfLadingNumber;
    private String trackingNumber;
    private String freightBillNumber;
    private String billingAccount;
    private String billToAccount;
    private String divisionIdentifier;
    private String invoiceStandardCode;
    private String carrierName;
    private String businessUnit;
    private String customerVatRegistration;
    private String carrierVatRegistration;
    private String carrierTaxIdentity;
    private String senderTaxIdentity;
    private String recipientTaxIdentity;
    private String taxBureauName;
    private String taxBureauCode;

    public Long getInvoiceId() { return invoiceId; }
    public void setInvoiceId(Long invoiceId) { this.invoiceId = invoiceId; }
    public String getCustomerReference() { return customerReference; }
    public void setCustomerReference(String customerReference) { this.customerReference = customerReference == null ? null : customerReference.trim(); }
    public String getPo() { return po; }
    public void setPo(String po) { this.po = po == null ? null : po.trim(); }
    public String getDn() { return dn; }
    public void setDn(String dn) { this.dn = dn == null ? null : dn.trim(); }
    public String getRma() { return rma; }
    public void setRma(String rma) { this.rma = rma == null ? null : rma.trim(); }
    public String getHawb() { return hawb; }
    public void setHawb(String hawb) { this.hawb = hawb == null ? null : hawb.trim(); }
    public String getSo() { return so; }
    public void setSo(String so) { this.so = so == null ? null : so.trim(); }
    public String getBillOfLadingNumber() { return billOfLadingNumber; }
    public void setBillOfLadingNumber(String billOfLadingNumber) { this.billOfLadingNumber = billOfLadingNumber == null ? null : billOfLadingNumber.trim(); }
    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber == null ? null : trackingNumber.trim(); }
    public String getFreightBillNumber() { return freightBillNumber; }
    public void setFreightBillNumber(String freightBillNumber) { this.freightBillNumber = freightBillNumber == null ? null : freightBillNumber.trim(); }
    public String getBillingAccount() { return billingAccount; }
    public void setBillingAccount(String billingAccount) { this.billingAccount = billingAccount == null ? null : billingAccount.trim(); }
    public String getBillToAccount() { return billToAccount; }
    public void setBillToAccount(String billToAccount) { this.billToAccount = billToAccount == null ? null : billToAccount.trim(); }
    public String getDivisionIdentifier() { return divisionIdentifier; }
    public void setDivisionIdentifier(String divisionIdentifier) { this.divisionIdentifier = divisionIdentifier == null ? null : divisionIdentifier.trim(); }
    public String getInvoiceStandardCode() { return invoiceStandardCode; }
    public void setInvoiceStandardCode(String invoiceStandardCode) { this.invoiceStandardCode = invoiceStandardCode == null ? null : invoiceStandardCode.trim(); }
    public String getCarrierName() { return carrierName; }
    public void setCarrierName(String carrierName) { this.carrierName = carrierName == null ? null : carrierName.trim(); }
    public String getBusinessUnit() { return businessUnit; }
    public void setBusinessUnit(String businessUnit) { this.businessUnit = businessUnit == null ? null : businessUnit.trim(); }
    public String getCustomerVatRegistration() { return customerVatRegistration; }
    public void setCustomerVatRegistration(String customerVatRegistration) { this.customerVatRegistration = customerVatRegistration == null ? null : customerVatRegistration.trim(); }
    public String getCarrierVatRegistration() { return carrierVatRegistration; }
    public void setCarrierVatRegistration(String carrierVatRegistration) { this.carrierVatRegistration = carrierVatRegistration == null ? null : carrierVatRegistration.trim(); }
    public String getCarrierTaxIdentity() { return carrierTaxIdentity; }
    public void setCarrierTaxIdentity(String carrierTaxIdentity) { this.carrierTaxIdentity = carrierTaxIdentity == null ? null : carrierTaxIdentity.trim(); }
    public String getSenderTaxIdentity() { return senderTaxIdentity; }
    public void setSenderTaxIdentity(String senderTaxIdentity) { this.senderTaxIdentity = senderTaxIdentity == null ? null : senderTaxIdentity.trim(); }
    public String getRecipientTaxIdentity() { return recipientTaxIdentity; }
    public void setRecipientTaxIdentity(String recipientTaxIdentity) { this.recipientTaxIdentity = recipientTaxIdentity == null ? null : recipientTaxIdentity.trim(); }
    public String getTaxBureauName() { return taxBureauName; }
    public void setTaxBureauName(String taxBureauName) { this.taxBureauName = taxBureauName == null ? null : taxBureauName.trim(); }
    public String getTaxBureauCode() { return taxBureauCode; }
    public void setTaxBureauCode(String taxBureauCode) { this.taxBureauCode = taxBureauCode == null ? null : taxBureauCode.trim(); }
}
