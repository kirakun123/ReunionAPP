package com.igorsilvaprod.app.models;

import java.io.Serializable;

public class userlinks implements Serializable {
    private int ID;
    private int fk_groupID;
    private int fk_userID;
    private int owner;

    public userlinks() {
    }

    public userlinks(int ID, int fk_groupID, int fk_userID, int owner) {
        this.ID = ID;
        this.fk_groupID = fk_groupID;
        this.fk_userID = fk_userID;
        this.owner = owner;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getFk_groupID() {
        return fk_groupID;
    }

    public void setFk_groupID(int fk_groupID) {
        this.fk_groupID = fk_groupID;
    }

    public int getFk_userID() {
        return fk_userID;
    }

    public void setFk_userID(int fk_userID) {
        this.fk_userID = fk_userID;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }
}
