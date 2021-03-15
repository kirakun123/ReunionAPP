package com.igorsilvaprod.app.models;

import java.io.Serializable;

public class reunion implements Serializable {
    private int reunionID ;
    private int fk_groupID ;
    private int ReunionName ;
    private int date ;
    private int hour;

    public reunion() {
    }

    public reunion(int reunionID, int fk_groupID, int reunionName, int date, int hour) {
        this.reunionID = reunionID;
        this.fk_groupID = fk_groupID;
        ReunionName = reunionName;
        this.date = date;
        this.hour = hour;
    }

    public int getReunionID() {
        return reunionID;
    }

    public void setReunionID(int reunionID) {
        this.reunionID = reunionID;
    }

    public int getFk_groupID() {
        return fk_groupID;
    }

    public void setFk_groupID(int fk_groupID) {
        this.fk_groupID = fk_groupID;
    }

    public int getReunionName() {
        return ReunionName;
    }

    public void setReunionName(int reunionName) {
        ReunionName = reunionName;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int hour) {
        this.hour = hour;
    }
    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }
}
