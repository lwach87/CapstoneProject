package com.example.lukaszwachowski.capstoneproject.network.model;

import android.arch.persistence.room.ColumnInfo;

public class Properties {

  public double mag;

  public String place;

  public long time;

  public String url;

  public String alert;

  public int sig;

  @ColumnInfo(name = "mag_type")
  public String magType;

  public String title;
}

