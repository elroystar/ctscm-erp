package com.boot.security.server.model;

/**
 * EDI 210 参与方（Bill-To / Bill-From / Shipper / Consignee）
 */
public class EDI210Party {
    public static final String ROLE_BILL_TO   = "BILL_TO";
    public static final String ROLE_BILL_FROM = "BILL_FROM";
    public static final String ROLE_SHIPPER   = "SHIPPER";
    public static final String ROLE_CONSIGNEE = "CONSIGNEE";

    private Long id;
    private Long invoiceId;
    private String partyRole;
    private String name1;
    private String name2;
    private String address1;
    private String address2;
    private String city;
    private String country;
    private String postalCode;
    private String stateProvince;
    private String account;
    private String location;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getInvoiceId() { return invoiceId; }
    public void setInvoiceId(Long invoiceId) { this.invoiceId = invoiceId; }
    public String getPartyRole() { return partyRole; }
    public void setPartyRole(String partyRole) { this.partyRole = partyRole; }
    public String getName1() { return name1; }
    public void setName1(String name1) { this.name1 = name1 == null ? null : name1.trim(); }
    public String getName2() { return name2; }
    public void setName2(String name2) { this.name2 = name2 == null ? null : name2.trim(); }
    public String getAddress1() { return address1; }
    public void setAddress1(String address1) { this.address1 = address1 == null ? null : address1.trim(); }
    public String getAddress2() { return address2; }
    public void setAddress2(String address2) { this.address2 = address2 == null ? null : address2.trim(); }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city == null ? null : city.trim(); }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country == null ? null : country.trim(); }
    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode == null ? null : postalCode.trim(); }
    public String getStateProvince() { return stateProvince; }
    public void setStateProvince(String stateProvince) { this.stateProvince = stateProvince == null ? null : stateProvince.trim(); }
    public String getAccount() { return account; }
    public void setAccount(String account) { this.account = account == null ? null : account.trim(); }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location == null ? null : location.trim(); }
}
