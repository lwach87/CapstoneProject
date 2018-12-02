package com.example.lukaszwachowski.capstoneproject.ui.map;

import android.arch.lifecycle.ViewModelProvider;
import com.example.lukaszwachowski.capstoneproject.ViewModelProviderFactory;
import com.example.lukaszwachowski.capstoneproject.data.DataManager;
import com.example.lukaszwachowski.capstoneproject.utils.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;

@Module
public class MapFragmentModule {

  @Provides
  MapFragmentViewModel provideMapFragmentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
    return new MapFragmentViewModel(dataManager, schedulerProvider);
  }

  @Provides
  ViewModelProvider.Factory mapViewModelProvider(MapFragmentViewModel mapFragmentViewModel) {
    return new ViewModelProviderFactory<>(mapFragmentViewModel);
  }
}
