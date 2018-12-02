package com.example.lukaszwachowski.capstoneproject.ui.details;

import android.arch.lifecycle.ViewModelProvider;
import com.example.lukaszwachowski.capstoneproject.ViewModelProviderFactory;
import com.example.lukaszwachowski.capstoneproject.data.DataManager;
import com.example.lukaszwachowski.capstoneproject.utils.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;

@Module
public class DetailsFragmentModule {

  @Provides
  DetailsFragmentViewModel provideDetailViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
    return new DetailsFragmentViewModel(dataManager, schedulerProvider);
  }

  @Provides
  ViewModelProvider.Factory detailsViewModelProvider(DetailsFragmentViewModel detailViewModel) {
    return new ViewModelProviderFactory<>(detailViewModel);
  }
}
