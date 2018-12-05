package com.example.lukaszwachowski.capstoneproject.di.builder;

import com.example.lukaszwachowski.capstoneproject.ui.main.MainActivityModule;
import com.example.lukaszwachowski.capstoneproject.ui.details.DetailsFragmentProvider;
import com.example.lukaszwachowski.capstoneproject.ui.list.ListFragmentProvider;
import com.example.lukaszwachowski.capstoneproject.ui.map.MapFragmentProvider;
import com.example.lukaszwachowski.capstoneproject.ui.main.MainActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

//We map all activities here
@Module
public abstract class ActivityBuilder {

  //Perform members-injection on Activities, Fragments constructed not by dagger
  @ContributesAndroidInjector(modules = {MainActivityModule.class, DetailsFragmentProvider.class,
      ListFragmentProvider.class, MapFragmentProvider.class})
  abstract MainActivity bindMainActivity();
}
