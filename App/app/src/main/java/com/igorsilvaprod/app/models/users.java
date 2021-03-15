package com.igorsilvaprod.app.models;

import java.io.Serializable;

public class users implements Serializable {
    private int userID;
    private String email;
    private String password;
    private String username;

    public users() {
    }

    public users(int userID, String email, String password, String username) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private static final users ourInstance = new users();
    public static users getInstance() {
        return ourInstance;
    }
}
