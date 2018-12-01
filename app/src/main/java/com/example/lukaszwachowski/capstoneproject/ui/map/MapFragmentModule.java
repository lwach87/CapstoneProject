package com.example.lukaszwachowski.capstoneproject.ui.map;

import android.arch.lifecycle.ViewModelProvider;
import com.example.lukaszwachowski.capstoneproject.ViewModelProviderFactory;
import com.example.lukaszwachowski.capstoneproject.data.local.Repository;
import dagger.Module;
import dagger.Provides;

@Module
public class MapFragmentModule {

  @Provides
  MapFragmentViewModel provideMapFragmentViewModel(Repository repository) {
    return new MapFragmentViewModel(repository);
  }

  @Provides
  ViewModelProvider.Factory mapViewModelProvider(MapFragmentViewModel mapFragmentViewModel) {
    return new ViewModelProviderFactory<>(mapFragmentViewModel);
  }
}
