package com.meghshyam.restapi.SportsApp.model;

import android.support.annotation.Nullable;

public class DataModel {
    private String title;
    private String idSport;
    private String strSprot;
    private String strFormat;
    private String strSportThumb;
    private String strSportThumbGreen;
    private String strSportDescription;

    public DataModel() {
    }

    public String getIdSport() {
        return idSport;
    }

    public String getStrFormat() {
        return strFormat;
    }

    public String getStrSportDescription() {
        return strSportDescription;
    }

    public String getStrSportThumb() {
        return strSportThumb;
    }

    public String getStrSportThumbGreen() {
        return strSportThumbGreen;
    }

    public String getStrSprot() {
        return strSprot;
    }

    public void setIdSport(String idSport) {
        this.idSport = idSport;
    }

    public void setStrFormat(String strFormat) {
        this.strFormat = strFormat;
    }

    public void setStrSportDescription(String strSportDescription) {
        this.strSportDescription = strSportDescription;
    }

    public void setStrSportThumb(String strSportThumb) {
        this.strSportThumb = strSportThumb;
    }

    public void setStrSportThumbGreen(String strSportThumbGreen) {
        this.strSportThumbGreen = strSportThumbGreen;
    }

    public void setStrSprot(String strSprot) {
        this.strSprot = strSprot;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }
}
