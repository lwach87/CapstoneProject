package com.example.lukaszwachowski.capstoneproject.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import com.example.lukaszwachowski.capstoneproject.ViewModelProviderFactory;
import com.example.lukaszwachowski.capstoneproject.data.local.Repository;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

@Module
public class MainActivityModule {

  @Provides
  MainActivityViewModel provideMainViewModel(Repository repository) {
    return new MainActivityViewModel(repository);
  }

  @Provides
  @Named("MainActivity")
  ViewModelProvider.Factory mainViewModelProvider(MainActivityViewModel mainViewModel) {
    return new ViewModelProviderFactory<>(mainViewModel);
  }

  @Provides
  PagerAdapter pagerAdapter(MainActivity mainActivity) {
    return new PagerAdapter(mainActivity, mainActivity.getSupportFragmentManager());
  }
}
