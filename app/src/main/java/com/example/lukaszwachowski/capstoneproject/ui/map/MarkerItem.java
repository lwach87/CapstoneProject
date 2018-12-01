package com.example.lukaszwachowski.capstoneproject.ui.map;

import com.example.lukaszwachowski.capstoneproject.data.model.Feature;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class MarkerItem implements ClusterItem {

  private LatLng position;
  private String title;
  private String snippet;

  public MarkerItem(Feature feature) {
    this.position = new LatLng(feature.getGeometry().getCoordinates().get(1),
        feature.getGeometry().getCoordinates().get(0));
    this.title = feature.getProperties().getPlace();
    this.snippet = "Magnitude: " + feature.getProperties().getMag();
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
