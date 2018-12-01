package com.example.lukaszwachowski.capstoneproject.ui.list;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ListFragmentProvider {

  @ContributesAndroidInjector(modules = ListFragmentModule.class)
  abstract ListFragment provideListFragment();
}
