package com.example.lukaszwachowski.capstoneproject.ui.main;

import android.arch.lifecycle.ViewModel;
import com.example.lukaszwachowski.capstoneproject.data.local.Repository;
import com.example.lukaszwachowski.capstoneproject.data.model.Feature;
import io.reactivex.Single;

public class MainActivityViewModel extends ViewModel {

  private Repository repository;

  public MainActivityViewModel(Repository repository) {
    this.repository = repository;
  }

  public Single<Feature> getFeatureBySig() {
    return repository.getFeatureBySig();
  }
}
