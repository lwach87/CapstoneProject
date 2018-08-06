package com.example.lukaszwachowski.capstoneproject.network.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "feature")
public class Feature {

  @PrimaryKey(autoGenerate = true)
  public int databaseId;

  @Embedded
  public Properties properties;

  @Embedded
  public Geometry geometry;

  @SerializedName("id")
  public String apiId;

  public Feature(int databaseId, Properties properties, Geometry geometry, String apiId) {
    this.databaseId = databaseId;
    this.properties = properties;
    this.geometry = geometry;
    this.apiId = apiId;
  }
}
