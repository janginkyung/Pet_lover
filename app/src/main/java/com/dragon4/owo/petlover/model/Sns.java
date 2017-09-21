package com.dragon4.owo.petlover.model;

/**
 * Created by InKyung on 2017. 6. 16..
 */

public class Sns {

    private String userName;
    private String userImageUrl;
    private String date;
    private String snsImageUrl;
    private String snsText;

    public Sns() {

    }

    public Sns(String userName, String userImageUrl, String date, String snsImageUrl, String snsText) {
        this.userName = userName;
        this.userImageUrl = userImageUrl;
        this.date = date;
        this.snsImageUrl = userImageUrl;
        this.snsText = snsText;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSnsImageUrl() {
        return snsImageUrl;
    }

    public void setSnsImageUrl(String snsImageUrl) {
        this.snsImageUrl = snsImageUrl;
    }

    public String getSnsText() {
        return snsText;
    }

    public void setSnsText(String snsText) {
        this.snsText = snsText;
    }
}
