package com.example.lukaszwachowski.capstoneproject.network.model;

import com.google.gson.annotations.SerializedName;

public class Feature {

    @SerializedName("properties")
    public Properties properties;

    @SerializedName("geometry")
    public Geometry geometry;

    @SerializedName("id")
    public String id;

    public Properties getProperties() {
        return properties;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public String getId() {
        return id;
    }
}
