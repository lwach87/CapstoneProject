package com.example.lukaszwachowski.capstoneproject.network.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "feature")
public class Feature {

  @PrimaryKey(autoGenerate = true)
  public int id;

  @Embedded
  @SerializedName("properties")
  public Properties properties;

  @Embedded
  @SerializedName("geometry")
  public Geometry geometry;

  @SerializedName("id")
  public String apiId;

  public Feature(int id, Properties properties, Geometry geometry, String apiId) {
    this.id = id;
    this.properties = properties;
    this.geometry = geometry;
    this.apiId = apiId;
  }

  public int getId() {
    return id;
  }

  public Properties getProperties() {
    return properties;
  }

  public Geometry getGeometry() {
    return geometry;
  }

  public String getApiId() {
    return apiId;
  }
}
