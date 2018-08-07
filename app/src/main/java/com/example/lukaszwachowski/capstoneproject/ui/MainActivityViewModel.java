package com.example.lukaszwachowski.capstoneproject.ui;

import android.arch.lifecycle.ViewModel;
import android.util.Log;
import com.example.lukaszwachowski.capstoneproject.db.Repository;
import com.example.lukaszwachowski.capstoneproject.network.ModelService;
import com.example.lukaszwachowski.capstoneproject.network.model.Feature;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import timber.log.Timber;

public class MainActivityViewModel extends ViewModel {

  private CompositeDisposable disposable;
  private ModelService service;
  private Repository repository;

  public MainActivityViewModel(ModelService service, Repository repository) {
    this.service = service;
    this.repository = repository;
    disposable = new CompositeDisposable();
  }

  public void getDataFromService() {
    disposable.add(service.getData()
        .concatMap(model -> Observable.fromIterable(model.features))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(feature -> repository.insetFeature(feature),
            error -> Timber.d(error.getLocalizedMessage())));
  }

  public Flowable<List<Feature>> getFeatures() {
    return repository.getFeatures();
  }

  @Override
  protected void onCleared() {
    super.onCleared();
    disposable.clear();
  }
}
