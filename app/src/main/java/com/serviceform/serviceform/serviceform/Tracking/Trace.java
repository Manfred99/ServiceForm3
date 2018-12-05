package com.serviceform.serviceform.serviceform.Tracking;

public class Trace {
    private int id;
    private String userEmail;
    private String server;
    private String descriptionBinnacle;
    private String entryTime;
    private String exitTime;
    private String dateUser;
    private String userUsed;

    public Trace(int id, String userEmail,String servidor, String descriptionBinnacle, String entryTime, String exitTime,String dateUser, String userUsed) {
        this.id = id;
        this.userEmail = userEmail;
        this.server = servidor;
        this.descriptionBinnacle = descriptionBinnacle;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.dateUser = dateUser;
        this.userUsed = userUsed;
    }

    public String getDateUser() {
        return dateUser;
    }

    public void setDateUsser(String dateUsser) {
        this.dateUser = dateUsser;
    }

    public Trace() {
        this.id = 0;
        this.userEmail = "";
        this.server = "";
        this.descriptionBinnacle = "";
        this.entryTime = "";
        this.exitTime = "";
        this.dateUser = "";
        this.userUsed = "";
    }

    public String getServidor() {
        return server;
    }

    public void setServidor(String servidor) {
        this.server = servidor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getDescriptionBinnacle() {
        return descriptionBinnacle;
    }

    public void setDescriptionBinnacle(String descriptionBinnacle) {
        this.descriptionBinnacle = descriptionBinnacle;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getExitTime() {
        return exitTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }

    public String getUserUsed() {
        return userUsed;
    }

    public void setUserUsed(String userUsed) {
        this.userUsed = userUsed;
    }

    @Override
    public String toString() {
        return "Binnacle{" + "id=" + id + ", userEmail=" + userEmail + ", servidor=" + server + ", descriptionBinnacle=" + descriptionBinnacle + ", entryTime=" + entryTime + ", exitTime=" + exitTime + ", userUsed=" + userUsed + '}';
    }
}
