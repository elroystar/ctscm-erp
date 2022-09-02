package com.boot.security.server.model;

import org.springframework.stereotype.Repository;

@Repository
public class EdiWzCancel {
    private Integer id;

    private Integer status;

    private String trackno;

    private String deviceid;

    private String trackendtime;

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

    public String getTrackno() {
        return trackno;
    }

    public void setTrackno(String trackno) {
        this.trackno = trackno == null ? null : trackno.trim();
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid == null ? null : deviceid.trim();
    }

    public String getTrackendtime() {
        return trackendtime;
    }

    public void setTrackendtime(String trackendtime) {
        this.trackendtime = trackendtime == null ? null : trackendtime.trim();
    }
}