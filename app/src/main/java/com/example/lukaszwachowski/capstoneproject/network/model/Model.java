package com.example.lukaszwachowski.capstoneproject.network.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Model {

  @SerializedName("features")
  public List<Feature> features;

  public List<Feature> getFeatures() {
    return features;
  }
}
