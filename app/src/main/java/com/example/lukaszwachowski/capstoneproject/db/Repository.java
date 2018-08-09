package com.example.lukaszwachowski.capstoneproject.db;

import android.arch.lifecycle.LiveData;
import com.example.lukaszwachowski.capstoneproject.helper.AppExecutors;
import com.example.lukaszwachowski.capstoneproject.network.NetworkDataSource;
import com.example.lukaszwachowski.capstoneproject.network.model.Feature;
import io.reactivex.Flowable;
import io.reactivex.Single;
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
        features -> AppExecutors.getInstance().diskIO()
            .execute(() -> {
              deleteOldData();
              modelDao.insertFeatures(features);
            }));
  }

  /**
   * Creates periodic sync tasks and checks to see if an immediate sync is required. If an immediate
   * sync is required, this method will take care of making sure that sync occurs.
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
    AppExecutors.getInstance().diskIO()
        .execute(() -> dataSource.startFetchService());
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

  public Single<Feature> getFeatureById(String id) {
    return modelDao.getFeatureById(id);
  }
}
