package com.example.lukaszwachowski.capstoneproject.data;

import static com.example.lukaszwachowski.capstoneproject.helper.Constants.SYNC_SERVICE;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import javax.inject.Inject;

public class DataSyncIntentService extends IntentService {

  @Inject
  NetworkDataSource dataSource;

  public DataSyncIntentService() {
    super(SYNC_SERVICE);
  }

  @Override
  public void onCreate() {
    super.onCreate();
//    ((EarthquakeApp) getApplication()).getComponent().inject(this);
  }

  @Override
  protected void onHandleIntent(@Nullable Intent intent) {
    dataSource.getDataFromService();
  }
}
