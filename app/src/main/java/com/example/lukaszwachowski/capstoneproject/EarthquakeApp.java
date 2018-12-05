package com.example.lukaszwachowski.capstoneproject;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import com.example.lukaszwachowski.capstoneproject.di.components.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasServiceInjector;
import javax.inject.Inject;

public class EarthquakeApp extends Application implements HasActivityInjector, HasServiceInjector {

  @Inject
  DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

  @Inject
  DispatchingAndroidInjector<Service> serviceDispatchingAndroidInjector;

  @Override
  public AndroidInjector<Activity> activityInjector() {
    return activityDispatchingAndroidInjector;
  }

  @Override
  public AndroidInjector<Service> serviceInjector() {
    return serviceDispatchingAndroidInjector;
  }

  @Override
  public void onCreate() {
    super.onCreate();

    //Inject Application into app component
    DaggerAppComponent
        .builder()
        .application(this)
        .build()
        .inject(this);
  }
}
