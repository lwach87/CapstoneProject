package com.example.lukaszwachowski.capstoneproject.ui.map;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MapFragmentProvider {

  @ContributesAndroidInjector(modules = MapFragmentModule.class)
  abstract MapFragment provideMapFragment();
}