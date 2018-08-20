package com.example.lukaszwachowski.capstoneproject.fragments;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import com.example.lukaszwachowski.capstoneproject.db.Repository;
import com.example.lukaszwachowski.capstoneproject.fragments.details.DetailsFragmentViewModel;
import com.example.lukaszwachowski.capstoneproject.fragments.list.ListFragmentViewModel;
import com.example.lukaszwachowski.capstoneproject.fragments.map.MapViewModel;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CustomViewModelFactory implements ViewModelProvider.Factory {

  private Repository repository;

  @Inject
  public CustomViewModelFactory(Repository repository) {
    this.repository = repository;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  @Override
  public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    if (modelClass.isAssignableFrom(ListFragmentViewModel.class)) {
      return (T) new ListFragmentViewModel(repository);
    } else if (modelClass.isAssignableFrom(MapViewModel.class)) {
      return (T) new MapViewModel(repository);
    } else if (modelClass.isAssignableFrom(DetailsFragmentViewModel.class)) {
      return (T) new DetailsFragmentViewModel(repository);
    } else {
      throw new IllegalArgumentException("ViewModel Not Found");
    }
  }
}
