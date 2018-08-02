package com.example.lukaszwachowski.capstoneproject.di.modules;

import com.example.lukaszwachowski.capstoneproject.ui.MainActivity;
import com.example.lukaszwachowski.capstoneproject.di.MainActivityScope;
import com.example.lukaszwachowski.capstoneproject.ui.ListAdapter;
import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

  private MainActivity mainActivity;

  public MainActivityModule(MainActivity mainActivity) {
    this.mainActivity = mainActivity;
  }

  @Provides
  @MainActivityScope
  public ListAdapter listAdapter() {
    return new ListAdapter(mainActivity);
  }
}
