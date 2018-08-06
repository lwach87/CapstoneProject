package com.example.lukaszwachowski.capstoneproject.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import com.example.lukaszwachowski.capstoneproject.db.Repository;
import com.example.lukaszwachowski.capstoneproject.di.MainActivityScope;
import com.example.lukaszwachowski.capstoneproject.network.ModelService;
import javax.inject.Inject;

@MainActivityScope
public class MainActivityViewModelFactory implements ViewModelProvider.Factory {

  private ModelService service;
  private Repository repository;

  @Inject
  public MainActivityViewModelFactory(ModelService service, Repository repository) {
    this.service = service;
    this.repository = repository;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  @Override
  public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    return (T) new MainActivityViewModel(service, repository);
  }
}
