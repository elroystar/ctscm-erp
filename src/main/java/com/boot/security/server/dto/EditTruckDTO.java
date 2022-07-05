package com.boot.security.server.dto;

import java.io.Serializable;

public class EditTruckDTO implements Serializable {

    private Integer[] ids;

    private String truckPlantNumber;

    private String ctTracking;

    private String gpsDevice;

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public String getTruckPlantNumber() {
        return truckPlantNumber;
    }

    public void setTruckPlantNumber(String truckPlantNumber) {
        this.truckPlantNumber = truckPlantNumber;
    }

    public String getCtTracking() {
        return ctTracking;
    }

    public void setCtTracking(String ctTracking) {
        this.ctTracking = ctTracking;
    }

    public String getGpsDevice() {
        return gpsDevice;
    }

    public void setGpsDevice(String gpsDevice) {
        this.gpsDevice = gpsDevice;
    }
}
