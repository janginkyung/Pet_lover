package com.dragon4.owo.petlover.model;

/**
 * Created by InKyung on 2017. 6. 16..
 */

public class User {

    private static final User ourInstance = new User();

    public static User getInstance() {
        return ourInstance;
    }

    private String userID;
    private String userName;
    private String userEmail;
    private String userPhotoURL;

    private User() {

    }

    public java.lang.String getUserID() {
        return userID;
    }

    public void setUserID(java.lang.String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }


    public java.lang.String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(java.lang.String userEmail) {
        this.userEmail = userEmail;
    }

    public java.lang.String getUserPhotoURL() {
        return userPhotoURL;
    }

    public void setUserPhotoURL(java.lang.String userPhotoURL) {
        this.userPhotoURL = userPhotoURL;
    }


}
