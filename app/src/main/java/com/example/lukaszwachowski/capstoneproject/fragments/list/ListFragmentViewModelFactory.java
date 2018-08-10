package com.example.lukaszwachowski.capstoneproject.fragments.list;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import com.example.lukaszwachowski.capstoneproject.db.Repository;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ListFragmentViewModelFactory implements ViewModelProvider.Factory {

  private Repository repository;

  @Inject
  public ListFragmentViewModelFactory(Repository repository) {
    this.repository = repository;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  @Override
  public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    return (T) new ListFragmentViewModel(repository);
  }
}
