package com.example.lukaszwachowski.capstoneproject.db;

import com.example.lukaszwachowski.capstoneproject.network.model.Feature;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import timber.log.Timber;

public class Repository {

  private ModelDao modelDao;

  public Repository(ModelDao modelDao) {
    this.modelDao = modelDao;
  }

  public void insetFeature(Feature feature) {

    Completable.fromAction(() -> modelDao.insertFeature(feature))
        .subscribeOn(Schedulers.io())
        .subscribe(new CompletableObserver() {
          @Override
          public void onSubscribe(Disposable d) {

          }

          @Override
          public void onComplete() {

          }

          @Override
          public void onError(Throwable e) {
            Timber.d(e.getLocalizedMessage());
          }
        });
  }

  public Flowable<List<Feature>> getFeatures() {
    return modelDao.getFeatures();
  }

  public Single<Feature> getFeatureById(String id) {
    return modelDao.getFeatureById(id);
  }

  public void deleteFeature(Feature feature) {

    Completable.fromAction(() -> modelDao.deleteFeature(feature))
        .subscribeOn(Schedulers.io())
        .subscribe(new CompletableObserver() {
          @Override
          public void onSubscribe(Disposable d) {

          }

          @Override
          public void onComplete() {

          }

          @Override
          public void onError(Throwable e) {
            Timber.d(e.getLocalizedMessage());
          }
        });
  }
}
