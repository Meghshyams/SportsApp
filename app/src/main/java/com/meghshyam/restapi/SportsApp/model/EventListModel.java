package com.meghshyam.restapi.SportsApp.model;

public class EventListModel {

    private String idEvent;
    private String strEvent;
    private String strDate;
    private String strTime;


    public String getIdEvent() {
        return idEvent;
    }

    public String getStrDate() {
        return strDate;
    }

    public String getStrEvent() {
        return strEvent;
    }

    public String getStrTime() {
        return strTime;
    }

    public void setIdEvent(String idEvent) {
        this.idEvent = idEvent;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    public void setStrEvent(String strEvent) {
        this.strEvent = strEvent;
    }

    public void setStrTime(String strTime) {
        this.strTime = strTime;
    }

}
