package com.example.lukaszwachowski.capstoneproject.data;

import static com.example.lukaszwachowski.capstoneproject.utils.Constants.SYNC_SERVICE;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import dagger.android.AndroidInjection;
import javax.inject.Inject;

//Job dispatcher will not trigger a job immediately. This should be called when the application is on the screen
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
    Log.d("Debugger", "SERVICE STARTED");
    dataManager.syncData();
  }
}
