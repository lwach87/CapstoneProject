package com.example.lukaszwachowski.capstoneproject.fragments.map;

import com.example.lukaszwachowski.capstoneproject.network.model.Feature;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class MarkerItem implements ClusterItem {

  private LatLng position;
  private String title;
  private String snippet;

  public MarkerItem(Feature feature) {
    this.position = new LatLng(feature.geometry.coordinates.get(1),
        feature.geometry.coordinates.get(0));
    this.title = feature.properties.place;
    this.snippet = "Magnitude: " + feature.properties.mag;
  }

  @Override
  public LatLng getPosition() {
    return position;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public String getSnippet() {
    return snippet;
  }
}
