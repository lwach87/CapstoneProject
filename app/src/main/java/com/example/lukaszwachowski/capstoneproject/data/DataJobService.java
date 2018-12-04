package com.example.lukaszwachowski.capstoneproject.data;


import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import io.reactivex.CompletableObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import javax.inject.Inject;
import timber.log.Timber;

public class DataJobService extends JobService {

  @Inject
  DataManager dataManager;

  public DataJobService() {
  }

  @Override
  public boolean onStartJob(JobParameters job) {
    dataManager.syncData().subscribe(new CompletableObserver() {
      @Override
      public void onSubscribe(@NonNull Disposable d) {
        Timber.d("Sync started...");
      }

      @Override
      public void onComplete() {
        Timber.d("Sync finished...");
        jobFinished(job, false);
      }

      @Override
      public void onError(@NonNull Throwable e) {
        Timber.d("Sync failed! Error: %s", e.getMessage());
      }
    });
    return true;
  }

  @Override
  public boolean onStopJob(JobParameters job) {
    return true;
  }
}
