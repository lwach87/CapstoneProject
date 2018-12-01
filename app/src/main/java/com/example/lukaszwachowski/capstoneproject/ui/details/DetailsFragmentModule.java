package com.example.lukaszwachowski.capstoneproject.ui.details;

import android.arch.lifecycle.ViewModelProvider;
import com.example.lukaszwachowski.capstoneproject.ViewModelProviderFactory;
import com.example.lukaszwachowski.capstoneproject.data.local.Repository;
import dagger.Module;
import dagger.Provides;

@Module
public class DetailsFragmentModule {

  @Provides
  DetailsFragmentViewModel provideDetailViewModel(Repository repository) {
    return new DetailsFragmentViewModel(repository);
  }

  @Provides
  ViewModelProvider.Factory detailsViewModelProvider(DetailsFragmentViewModel detailViewModel) {
    return new ViewModelProviderFactory<>(detailViewModel);
  }
}
