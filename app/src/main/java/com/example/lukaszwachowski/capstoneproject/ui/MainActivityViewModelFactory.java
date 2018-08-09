package com.example.lukaszwachowski.capstoneproject.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import com.example.lukaszwachowski.capstoneproject.db.Repository;
import com.example.lukaszwachowski.capstoneproject.di.MainActivityScope;
import javax.inject.Inject;

@MainActivityScope
public class MainActivityViewModelFactory implements ViewModelProvider.Factory {

  private Repository repository;

  @Inject
  public MainActivityViewModelFactory(Repository repository) {
    this.repository = repository;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  @Override
  public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    return (T) new MainActivityViewModel(repository);
  }
}
