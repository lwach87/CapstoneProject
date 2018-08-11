package com.example.lukaszwachowski.capstoneproject.helper;

import com.example.lukaszwachowski.capstoneproject.network.NetworkDataSource;
import java.util.concurrent.TimeUnit;

public class Constants {

  public static final String URL = "https://earthquake.usgs.gov";
  public static final String SYNC_SERVICE = "DataSyncIntentService";
  public static final String DATABASE_NAME = "features";
  public static final String TABLE_NAME = "feature";

  public static final String DATA_TAG = NetworkDataSource.class.getSimpleName();
  private static final int SYNC_INTERVAL_HOURS = 3;
  public static final int SYNC_INTERVAL_SECONDS = (int) TimeUnit.HOURS
      .toSeconds(SYNC_INTERVAL_HOURS);
  public static final int SYNC_FLEXTIME_SECONDS = SYNC_INTERVAL_SECONDS / 3;

}
