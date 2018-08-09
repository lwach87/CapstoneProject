package com.example.lukaszwachowski.capstoneproject.network.model;

import android.arch.persistence.room.ColumnInfo;
import com.google.gson.annotations.SerializedName;

public class Properties {

  public double mag;

  public String place;

  @SerializedName("time")
  public long date;

  public String url;

  public String alert;

  public int sig;

  @ColumnInfo(name = "mag_type")
  public String magType;

  public String title;
}

