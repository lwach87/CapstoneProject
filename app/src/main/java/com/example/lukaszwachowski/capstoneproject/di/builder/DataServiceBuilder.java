package com.example.lukaszwachowski.capstoneproject.di.builder;

import com.example.lukaszwachowski.capstoneproject.data.DataJobService;
import com.example.lukaszwachowski.capstoneproject.data.DataSyncIntentService;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class DataServiceBuilder {

  @ContributesAndroidInjector
  abstract DataSyncIntentService provideIntentService();

  @ContributesAndroidInjector
  abstract DataJobService provideDataJobService();
}
