package com.example.lukaszwachowski.capstoneproject.data;

import static com.example.lukaszwachowski.capstoneproject.utils.Constants.SYNC_SERVICE;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import dagger.android.AndroidInjection;
import io.reactivex.CompletableObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import javax.inject.Inject;
import timber.log.Timber;

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
    dataManager.syncData().subscribe(new CompletableObserver() {
      @Override
      public void onSubscribe(@NonNull Disposable d) {
        Timber.d("Sync started...");
      }

      @Override
      public void onComplete() {
        Timber.d("Sync finished...");
      }

      @Override
      public void onError(@NonNull Throwable e) {
        Timber.d("Sync failed! Error: %s", e.getMessage());
      }
    });
  }
}
