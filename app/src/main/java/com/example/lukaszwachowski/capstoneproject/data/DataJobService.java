package com.example.lukaszwachowski.capstoneproject.data;


import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import dagger.android.AndroidInjection;
import javax.inject.Inject;

public class DataJobService extends JobService {

  @Inject
  DataManager dataManager;

  public DataJobService() {
  }

  @Override
  public boolean onStartJob(JobParameters job) {
    dataManager.syncData();
    jobFinished(job, false);
    return true;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    AndroidInjection.inject(this);
  }

  @Override
  public boolean onStopJob(JobParameters job) {
    return true;
  }
}
