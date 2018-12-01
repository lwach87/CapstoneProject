package com.example.lukaszwachowski.capstoneproject.data;


import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import javax.inject.Inject;

public class DataJobService extends JobService {

  @Inject
  NetworkDataSource dataSource;

  public DataJobService() {
  }

  @Override
  public boolean onStartJob(JobParameters job) {
    dataSource.getDataFromService();
    jobFinished(job, false);
    return true;
  }

  @Override
  public boolean onStopJob(JobParameters job) {
    return true;
  }
}
