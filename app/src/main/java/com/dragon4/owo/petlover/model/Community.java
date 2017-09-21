package com.dragon4.owo.petlover.model;

/**
 * Created by InKyung on 2017. 6. 16..
 */

public class Community {

    private String communityTitle;
    private String content;
    private int reviewNum;
    private int countNum;


    public String getCommunityTitle() {
        return communityTitle;
    }

    public void setCommunityTitle(String communityTitle) {
        this.communityTitle = communityTitle;
    }

    public int getReviewNum() {
        return reviewNum;
    }

    public void setReviewNum(int reviewNum) {
        this.reviewNum = reviewNum;
    }

    public int getCountNum() {
        return countNum;
    }

    public void setCountNum(int countNum) {
        this.countNum = countNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

