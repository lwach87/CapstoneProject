package com.example.lukaszwachowski.capstoneproject.data;


import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import javax.inject.Inject;

public class DataJobService extends JobService {

  @Inject
  DataManager dataManager;

  public DataJobService() {
  }

  @Override
  public boolean onStartJob(JobParameters job) {
    dataManager.syncData().subscribe();
    jobFinished(job, false);
    return true;
  }

  @Override
  public boolean onStopJob(JobParameters job) {
    return true;
  }
}
