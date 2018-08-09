package com.example.lukaszwachowski.capstoneproject.network;

import static com.example.lukaszwachowski.capstoneproject.helper.Constants.DATA_TAG;
import static com.example.lukaszwachowski.capstoneproject.helper.Constants.SYNC_FLEXTIME_SECONDS;
import static com.example.lukaszwachowski.capstoneproject.helper.Constants.SYNC_INTERVAL_SECONDS;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import com.example.lukaszwachowski.capstoneproject.network.model.Feature;
import com.example.lukaszwachowski.capstoneproject.network.model.Model;
import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class NetworkDataSource {

  private ModelService service;
  private Context context;
  private MutableLiveData<List<Feature>> results = new MutableLiveData<>();

  public NetworkDataSource(ModelService service, Context context) {
    this.service = service;
    this.context = context;
  }

  public LiveData<List<Feature>> getCurrentFeatures() {
    return results;
  }

  /**
   * Starts an intent service to fetch the network data.
   */
  public void startFetchService() {
    Intent intent = new Intent(context, DataSyncIntentService.class);
    context.startService(intent);
  }

  /**
   * Schedules a repeating job service which fetches the network data.
   */
  public void scheduleRecurringFetchDataSync() {
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

  public void getDataFromService() {

    service.getData().enqueue(new Callback<Model>() {
      @Override
      public void onResponse(@NonNull Call<Model> call, @NonNull Response<Model> response) {
        if (response.isSuccessful()) {
          results.postValue(response.body().features);
        }
      }

      @Override
      public void onFailure(@NonNull Call<Model> call, @NonNull Throwable t) {
        Timber.d(t.getLocalizedMessage());
      }
    });
  }
}