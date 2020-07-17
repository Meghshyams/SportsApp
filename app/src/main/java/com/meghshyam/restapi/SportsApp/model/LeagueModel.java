package com.meghshyam.restapi.SportsApp.model;

public class LeagueModel {

    private String idLeague;
    private String strLeague;
    private String strSport;
    private String strLeagueAlternate;

    public LeagueModel()
    {

    }

    public String getIdLeague() {
        return idLeague;
    }

    public String getStrLeague() {
        return strLeague;
    }

    public String getStrLeagueAlternate() {
        return strLeagueAlternate;
    }

    public String getStrSport() {
        return strSport;
    }

    public void setIdLeague(String idLeague) {
        this.idLeague = idLeague;
    }

    public void setStrLeague(String strLeague) {
        this.strLeague = strLeague;
    }

    public void setStrLeagueAlternate(String strLeagueAlternate) {
        this.strLeagueAlternate = strLeagueAlternate;
    }

    public void setStrSport(String strSport) {
        this.strSport = strSport;
    }
}
