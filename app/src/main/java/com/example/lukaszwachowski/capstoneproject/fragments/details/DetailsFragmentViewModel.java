package com.example.lukaszwachowski.capstoneproject.fragments.details;

import android.arch.lifecycle.ViewModel;
import com.example.lukaszwachowski.capstoneproject.db.Repository;
import com.example.lukaszwachowski.capstoneproject.network.model.Feature;
import io.reactivex.Flowable;
import java.util.List;

public class DetailsFragmentViewModel extends ViewModel {

  private Repository repository;

  public DetailsFragmentViewModel(Repository repository) {
    this.repository = repository;
  }

  public Flowable<List<Feature>> getFeatures() {
    return repository.getFeatures();
  }
}
