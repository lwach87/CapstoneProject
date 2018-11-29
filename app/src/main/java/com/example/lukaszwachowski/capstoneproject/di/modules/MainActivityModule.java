package com.example.lukaszwachowski.capstoneproject.di.modules;

import com.example.lukaszwachowski.capstoneproject.ui.MainActivity;
import com.example.lukaszwachowski.capstoneproject.ui.PagerAdapter;
import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

  private MainActivity mainActivity;

  public MainActivityModule(MainActivity mainActivity) {
    this.mainActivity = mainActivity;
  }

  @Provides
  public PagerAdapter pagerAdapter() {
    return new PagerAdapter(mainActivity, mainActivity.getSupportFragmentManager());
  }
}
