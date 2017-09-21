package com.dragon4.owo.petlover.model;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by InKyung on 2017. 6. 16..
 */

public class Volunteer {

    private Bitmap volunteerImageBitmap;
    private String name;
    private List<Plan> volunPlans;

    public Volunteer() {

    }

    public Volunteer(Bitmap volunteerImageBitmap, String name, List<Plan> volunPlans) {
        this.volunteerImageBitmap = volunteerImageBitmap;
        this.name = name;
        this.volunPlans = volunPlans;
    }

    public Bitmap getVolunteerImageUrl() {
        return volunteerImageBitmap;
    }

    public void setVolunteerImageUrl(Bitmap volunteerImageUrl) {
        this.volunteerImageBitmap = volunteerImageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Plan> getVolunPlans() {
        return volunPlans;
    }

    public void setVolunPlans(List<Plan> volunPlans) {
        this.volunPlans = volunPlans;
    }
}
