package com.example.lukaszwachowski.capstoneproject.data;

import static com.example.lukaszwachowski.capstoneproject.helper.Constants.DATA_TAG;
import static com.example.lukaszwachowski.capstoneproject.helper.Constants.SYNC_FLEXTIME_SECONDS;
import static com.example.lukaszwachowski.capstoneproject.helper.Constants.SYNC_INTERVAL_SECONDS;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;
import com.example.lukaszwachowski.capstoneproject.data.model.Feature;
import com.example.lukaszwachowski.capstoneproject.data.model.Model;
import com.example.lukaszwachowski.capstoneproject.data.remote.ApiHelper;
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

public class NetworkDataSource {

  private ApiHelper apiHelper;
  private Context context;
  private MutableLiveData<List<Feature>> results = new MutableLiveData<>();

  public NetworkDataSource(ApiHelper apiHelper, Context context) {
    this.apiHelper = apiHelper;
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

    apiHelper.getData().enqueue(new Callback<Model>() {
      @Override
      public void onResponse(@NonNull Call<Model> call, @NonNull Response<Model> response) {
        if (response.isSuccessful()) {
          results.postValue(response.body().getFeatures());
        }
      }

      @Override
      public void onFailure(@NonNull Call<Model> call, @NonNull Throwable t) {
        Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
      }
    });
  }
}