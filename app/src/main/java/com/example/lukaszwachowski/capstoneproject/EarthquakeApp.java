package com.example.lukaszwachowski.capstoneproject;

import android.app.Activity;
import android.app.Application;
import com.example.lukaszwachowski.capstoneproject.di.components.ApplicationComponent;
import com.example.lukaszwachowski.capstoneproject.di.components.DaggerApplicationComponent;
import com.example.lukaszwachowski.capstoneproject.di.modules.DatabaseModule;

public class EarthquakeApp extends Application {

  private ApplicationComponent component;

  public static EarthquakeApp get(Activity activity) {
    return (EarthquakeApp) activity.getApplication();
  }

  @Override
  public void onCreate() {
    super.onCreate();

    component = DaggerApplicationComponent.builder()
        .databaseModule(new DatabaseModule(this))
        .build();
  }

  public ApplicationComponent getComponent() {
    return component;
  }
}
