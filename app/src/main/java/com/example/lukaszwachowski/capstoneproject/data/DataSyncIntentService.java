package com.example.lukaszwachowski.capstoneproject.data;

import static com.example.lukaszwachowski.capstoneproject.utils.Constants.SYNC_SERVICE;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import dagger.android.AndroidInjection;
import javax.inject.Inject;

public class DataSyncIntentService extends IntentService {

  @Inject
  DataManager dataManager;

  public DataSyncIntentService() {
    super(SYNC_SERVICE);
  }

  @Override
  public void onCreate() {
    super.onCreate();
    AndroidInjection.inject(this);
  }

  @Override
  protected void onHandleIntent(@Nullable Intent intent) {
    dataManager.syncData();
  }
}
