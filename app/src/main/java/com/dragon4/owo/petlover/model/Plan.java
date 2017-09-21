package com.dragon4.owo.petlover.model;

/**
 * Created by InKyung on 2017. 6. 16..
 */

public class Plan {
    private String title;
    private int num;
    private String userId;

    public Plan() {

    }

    public Plan(String title, String userId, int num)
    {
        this.title = title;
        this.userId = userId;
        this.num = num;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }




    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
