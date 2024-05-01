package com.boot.security.server.dto.load;

public class SendPddInfoDTO {

    private String shipDate;

    private String hawb;

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getHawb() {
        return hawb;
    }

    public void setHawb(String hawb) {
        this.hawb = hawb;
    }
}
