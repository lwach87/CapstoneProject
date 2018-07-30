package com.example.lukaszwachowski.capstoneproject.network.model;

import com.google.gson.annotations.SerializedName;

public class Properties {

    @SerializedName("mag")
    public Double mag;

    @SerializedName("place")
    public String place;

    @SerializedName("time")
    public Long time;

    @SerializedName("url")
    public String url;

    @SerializedName("alert")
    public String alert;

    @SerializedName("sig")
    public Integer sig;

    @SerializedName("magType")
    public String magType;

    @SerializedName("title")
    public String title;

    public Double getMag() {
        return mag;
    }

    public String getPlace() {
        return place;
    }

    public Long getTime() {
        return time;
    }

    public String getUrl() {
        return url;
    }

    public String getAlert() {
        return alert;
    }

    public Integer getSig() {
        return sig;
    }

    public String getMagType() {
        return magType;
    }

    public String getTitle() {
        return title;
    }
}

