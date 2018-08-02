package com.example.lukaszwachowski.capstoneproject.network.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Geometry {

  @SerializedName("coordinates")
  public List<Double> coordinates;

  public List<Double> getCoordinates() {
    return coordinates;
  }
}
