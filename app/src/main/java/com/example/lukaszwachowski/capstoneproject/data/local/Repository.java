package com.example.lukaszwachowski.capstoneproject.data.local;

import android.arch.lifecycle.LiveData;
import com.example.lukaszwachowski.capstoneproject.data.NetworkDataSource;
import com.example.lukaszwachowski.capstoneproject.data.local.ModelDao;
import com.example.lukaszwachowski.capstoneproject.data.model.Feature;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.Calendar;
import java.util.List;

public class Repository {

  private ModelDao modelDao;
  private NetworkDataSource dataSource;
  private boolean initialized = false;

  public Repository(ModelDao modelDao, NetworkDataSource dataSource) {
    this.modelDao = modelDao;
    this.dataSource = dataSource;

    LiveData<List<Feature>> results = dataSource.getCurrentFeatures();
    results.observeForever(
        features -> Completable.fromAction(() -> {
          deleteOldData();
          modelDao.insertFeatures(features);
        }).subscribeOn(Schedulers.io())
            .subscribe());
  }

  /**
   * Creates periodic sync tasks and starts an immediate sync.
   */
  private void initializeData() {

    // Only perform initialization once per app lifetime. If initialization has already been
    // performed, we have nothing to do in this method.
    if (initialized) {
      return;
    }
    initialized = true;

    // This method call triggers to create its task to synchronize earthquake data
    // periodically.
    dataSource.scheduleRecurringFetchDataSync();

    //Starts an intent service which fetches the network data
    Completable.fromAction(() -> dataSource.startFetchService())
        .subscribeOn(Schedulers.io())
        .subscribe();
  }

  public Flowable<List<Feature>> getFeatures() {
    initializeData();
    return modelDao.getFeatures();
  }

  /**
   * Deletes data older than 30 days
   */
  private void deleteOldData() {
    Calendar pastDate = Calendar.getInstance();
    pastDate.add(Calendar.DAY_OF_YEAR, -30);
    modelDao.deleteFeature(pastDate.getTimeInMillis());
  }

  public Single<Feature> getFeatureBySig() {
    return modelDao.getFeatureBySig();
  }
}
