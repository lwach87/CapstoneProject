package com.example.lukaszwachowski.capstoneproject.di.builder;

import com.example.lukaszwachowski.capstoneproject.di.modules.MainActivityModule;
import com.example.lukaszwachowski.capstoneproject.ui.MainActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

  @ContributesAndroidInjector(modules = MainActivityModule.class)
  abstract MainActivity bindMainActivity();
}
