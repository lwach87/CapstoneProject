package com.example.lukaszwachowski.capstoneproject.data;

import static android.content.Context.MODE_PRIVATE;
import static com.example.lukaszwachowski.capstoneproject.utils.Constants.DATA_TAG;
import static com.example.lukaszwachowski.capstoneproject.utils.Constants.PREFS_INITIALIZED;
import static com.example.lukaszwachowski.capstoneproject.utils.Constants.PREFS_NAME;
import static com.example.lukaszwachowski.capstoneproject.utils.Constants.SYNC_FLEXTIME_SECONDS;
import static com.example.lukaszwachowski.capstoneproject.utils.Constants.SYNC_INTERVAL_SECONDS;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import com.example.lukaszwachowski.capstoneproject.data.local.DbHelper;
import com.example.lukaszwachowski.capstoneproject.data.model.Feature;
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

  public DataManager(ApiHelper apiHelper, DbHelper dbHelper, Context context) {
    this.apiHelper = apiHelper;
    this.dbHelper = dbHelper;
    this.context = context;
  }

  public DbHelper getDbHelper() {
    return dbHelper;
  }

  public void initializeData() {
    SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

    Log.d("Debugger", String.valueOf(preferences.getBoolean("initialized", false)));

    if (preferences.getBoolean(PREFS_INITIALIZED, false)) {
      return;
    }

    Completable.fromAction(this::startFetchService)
        .subscribeOn(Schedulers.io())
        .subscribe();

    scheduleRecurringFetchDataSync();

    SharedPreferences.Editor editor = preferences.edit();
    editor.putBoolean(PREFS_INITIALIZED, true);
    editor.apply();
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

              for (Feature feature : model.getFeatures()) {
                Log.d("Debugger", feature.getProperties().getPlace());
              }

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