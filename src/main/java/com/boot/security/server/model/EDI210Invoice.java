package com.boot.security.server.model;

import java.util.Date;

/**
 * EDI 210 发票主表
 */
public class EDI210Invoice {
    private Long id;
    private String region;
    private String scac;
    private String invNo;
    private String invDate;
    private String paymentTerms;
    private String cur;
    private String invAmount;
    private String ttCharge;
    private String pickupDate;
    private String pickupTime;
    private String podDate;
    private String podTime;
    private String podName;
    private String grossWeight;
    private String gwQualifier;
    private String gwUnit;
    private String billWeight;
    private String bwQualifier;
    private String bwUnit;
    private String bol;
    private Integer status;
    private String importBatch;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region == null ? null : region.trim(); }
    public String getScac() { return scac; }
    public void setScac(String scac) { this.scac = scac == null ? null : scac.trim(); }
    public String getInvNo() { return invNo; }
    public void setInvNo(String invNo) { this.invNo = invNo == null ? null : invNo.trim(); }
    public String getInvDate() { return invDate; }
    public void setInvDate(String invDate) { this.invDate = invDate == null ? null : invDate.trim(); }
    public String getPaymentTerms() { return paymentTerms; }
    public void setPaymentTerms(String paymentTerms) { this.paymentTerms = paymentTerms == null ? null : paymentTerms.trim(); }
    public String getCur() { return cur; }
    public void setCur(String cur) { this.cur = cur == null ? null : cur.trim(); }
    public String getInvAmount() { return invAmount; }
    public void setInvAmount(String invAmount) { this.invAmount = invAmount == null ? null : invAmount.trim(); }
    public String getTtCharge() { return ttCharge; }
    public void setTtCharge(String ttCharge) { this.ttCharge = ttCharge == null ? null : ttCharge.trim(); }
    public String getPickupDate() { return pickupDate; }
    public void setPickupDate(String pickupDate) { this.pickupDate = pickupDate == null ? null : pickupDate.trim(); }
    public String getPickupTime() { return pickupTime; }
    public void setPickupTime(String pickupTime) { this.pickupTime = pickupTime == null ? null : pickupTime.trim(); }
    public String getPodDate() { return podDate; }
    public void setPodDate(String podDate) { this.podDate = podDate == null ? null : podDate.trim(); }
    public String getPodTime() { return podTime; }
    public void setPodTime(String podTime) { this.podTime = podTime == null ? null : podTime.trim(); }
    public String getPodName() { return podName; }
    public void setPodName(String podName) { this.podName = podName == null ? null : podName.trim(); }
    public String getGrossWeight() { return grossWeight; }
    public void setGrossWeight(String grossWeight) { this.grossWeight = grossWeight == null ? null : grossWeight.trim(); }
    public String getGwQualifier() { return gwQualifier; }
    public void setGwQualifier(String gwQualifier) { this.gwQualifier = gwQualifier == null ? null : gwQualifier.trim(); }
    public String getGwUnit() { return gwUnit; }
    public void setGwUnit(String gwUnit) { this.gwUnit = gwUnit == null ? null : gwUnit.trim(); }
    public String getBillWeight() { return billWeight; }
    public void setBillWeight(String billWeight) { this.billWeight = billWeight == null ? null : billWeight.trim(); }
    public String getBwQualifier() { return bwQualifier; }
    public void setBwQualifier(String bwQualifier) { this.bwQualifier = bwQualifier == null ? null : bwQualifier.trim(); }
    public String getBwUnit() { return bwUnit; }
    public void setBwUnit(String bwUnit) { this.bwUnit = bwUnit == null ? null : bwUnit.trim(); }
    public String getBol() { return bol; }
    public void setBol(String bol) { this.bol = bol == null ? null : bol.trim(); }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public String getImportBatch() { return importBatch; }
    public void setImportBatch(String importBatch) { this.importBatch = importBatch == null ? null : importBatch.trim(); }
    public String getCreateUser() { return createUser; }
    public void setCreateUser(String createUser) { this.createUser = createUser; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public String getUpdateUser() { return updateUser; }
    public void setUpdateUser(String updateUser) { this.updateUser = updateUser; }
    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
}
