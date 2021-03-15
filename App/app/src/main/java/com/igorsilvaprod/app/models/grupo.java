package com.igorsilvaprod.app.models;

import java.io.Serializable;

public class grupo implements Serializable {
    private int groupID;
    private String groupName;

    public grupo() {
    }

    public grupo(int groupID, String groupName) {
        this.groupID = groupID;
        this.groupName = groupName;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
