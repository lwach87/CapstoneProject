package com.example.lukaszwachowski.capstoneproject.ui.details;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class DetailsFragmentProvider {

  @ContributesAndroidInjector(modules = DetailsFragmentModule.class)
  abstract DetailsFragment provideDetailsFragment();
}
