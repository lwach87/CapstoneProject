package com.example.lukaszwachowski.capstoneproject.data;

import static com.example.lukaszwachowski.capstoneproject.utils.Constants.DATA_TAG;
import static com.example.lukaszwachowski.capstoneproject.utils.Constants.SYNC_FLEXTIME_SECONDS;
import static com.example.lukaszwachowski.capstoneproject.utils.Constants.SYNC_INTERVAL_SECONDS;

import android.content.Context;
import android.content.Intent;
import com.example.lukaszwachowski.capstoneproject.data.local.DbHelper;
import com.example.lukaszwachowski.capstoneproject.data.remote.ApiHelper;
import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.Calendar;
import timber.log.Timber;

public class DataManager {

  private ApiHelper apiHelper;
  private DbHelper dbHelper;
  private Context context;
  private boolean initialized = false;

  public DataManager(ApiHelper apiHelper, DbHelper dbHelper, Context context) {
    this.apiHelper = apiHelper;
    this.dbHelper = dbHelper;
    this.context = context;
  }

  public DbHelper getDbHelper() {
    return dbHelper;
  }

  public void initializeData() {

    if (initialized) {
      return;
    }
    initialized = true;

    scheduleRecurringFetchDataSync();

    Completable.fromAction(this::startFetchService)
        .subscribeOn(Schedulers.io())
        .subscribe();
  }

  /**
   * Starts an intent service to fetch the network data.
   */
  private void startFetchService() {
    Intent intent = new Intent(context, DataSyncIntentService.class);
    context.startService(intent);
  }

  /**
   * Schedules a repeating job service which fetches the network data.
   */
  private void scheduleRecurringFetchDataSync() {
    Driver driver = new GooglePlayDriver(context);
    FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);

    Job syncJob = dispatcher.newJobBuilder()
        .setService(DataJobService.class)
        .setTag(DATA_TAG)
        .setConstraints(Constraint.ON_ANY_NETWORK)
        .setLifetime(Lifetime.FOREVER)
        .setRecurring(true)
        .setTrigger(Trigger.executionWindow(
            SYNC_INTERVAL_SECONDS,
            SYNC_INTERVAL_SECONDS + SYNC_FLEXTIME_SECONDS
        )).setReplaceCurrent(true)
        .build();

    dispatcher.schedule(syncJob);
  }

  public void syncData() {
    apiHelper.getData()
        .subscribeOn(Schedulers.io())
        .flatMapCompletable(model -> Completable.create(emitter -> {

              dbHelper.insertFeatures(model.getFeatures());

              //deletes data older than 30 days
              Calendar pastDate = Calendar.getInstance();
              pastDate.add(Calendar.DAY_OF_YEAR, -30);
              dbHelper.deleteFeature(pastDate.getTimeInMillis());
            })
        ).subscribe(new CompletableObserver() {
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