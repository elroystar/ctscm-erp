package com.boot.security.server.dto;

/**
 * EDI 210 列表展示 DTO（主表关键字段 + 3 个 Party 名称 + 计费数）
 */
public class EDI210ListDTO {
    private Long id;
    private String region;
    private String scac;
    private String invNo;
    private String invDate;
    private String bol;
    private String cur;
    private String invAmount;
    private Integer status;
    private String createTime;
    private String billToName;
    private String shipperName;
    private String consigneeName;
    private Integer chargeCount;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }
    public String getScac() { return scac; }
    public void setScac(String scac) { this.scac = scac; }
    public String getInvNo() { return invNo; }
    public void setInvNo(String invNo) { this.invNo = invNo; }
    public String getInvDate() { return invDate; }
    public void setInvDate(String invDate) { this.invDate = invDate; }
    public String getBol() { return bol; }
    public void setBol(String bol) { this.bol = bol; }
    public String getCur() { return cur; }
    public void setCur(String cur) { this.cur = cur; }
    public String getInvAmount() { return invAmount; }
    public void setInvAmount(String invAmount) { this.invAmount = invAmount; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public String getCreateTime() { return createTime; }
    public void setCreateTime(String createTime) { this.createTime = createTime; }
    public String getBillToName() { return billToName; }
    public void setBillToName(String billToName) { this.billToName = billToName; }
    public String getShipperName() { return shipperName; }
    public void setShipperName(String shipperName) { this.shipperName = shipperName; }
    public String getConsigneeName() { return consigneeName; }
    public void setConsigneeName(String consigneeName) { this.consigneeName = consigneeName; }
    public Integer getChargeCount() { return chargeCount; }
    public void setChargeCount(Integer chargeCount) { this.chargeCount = chargeCount; }
}
