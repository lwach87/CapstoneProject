package com.example.lukaszwachowski.capstoneproject.di.modules;

import com.example.lukaszwachowski.capstoneproject.ui.MainActivity;
import com.example.lukaszwachowski.capstoneproject.ui.PagerAdapter;
import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

  @Provides
  PagerAdapter pagerAdapter(MainActivity mainActivity) {
    return new PagerAdapter(mainActivity, mainActivity.getSupportFragmentManager());
  }
}
