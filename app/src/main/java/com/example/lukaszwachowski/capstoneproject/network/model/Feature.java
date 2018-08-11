package com.example.lukaszwachowski.capstoneproject.network.model;

import static com.example.lukaszwachowski.capstoneproject.helper.Constants.TABLE_NAME;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = TABLE_NAME)
public class Feature {

  @PrimaryKey
  @NonNull
  public String id;

  @Embedded
  public Properties properties;

  @Embedded
  public Geometry geometry;
}
