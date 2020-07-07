package com.boot.security.server.model;

public class EDIDetailOEM2020WithBLOBs extends EDIDetailOEM2020 {
    private String stAddr1;

    private String stCompany;

    public String getStAddr1() {
        return stAddr1;
    }

    public void setStAddr1(String stAddr1) {
        this.stAddr1 = stAddr1 == null ? null : stAddr1.trim();
    }

    public String getStCompany() {
        return stCompany;
    }

    public void setStCompany(String stCompany) {
        this.stCompany = stCompany == null ? null : stCompany.trim();
    }
}