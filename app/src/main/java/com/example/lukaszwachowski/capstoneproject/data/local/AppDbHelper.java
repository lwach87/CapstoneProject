package com.example.lukaszwachowski.capstoneproject.data.local;

import com.example.lukaszwachowski.capstoneproject.data.model.Feature;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppDbHelper implements DbHelper {

  private ModelDatabase modelDatabase;

  @Inject
  public AppDbHelper(ModelDatabase modelDatabase) {
    this.modelDatabase = modelDatabase;
  }

  @Override
  public Flowable<List<Feature>> getFeatures() {
    return Flowable.fromCallable(() -> modelDatabase.modelDao().getFeatures());
  }

  @Override
  public Single<Feature> getFeatureBySig() {
    return Single.fromCallable(() -> modelDatabase.modelDao().getFeatureBySig());
  }

  @Override
  public void insertFeatures(List<Feature> features) {
    modelDatabase.modelDao().insertFeatures(features);
  }

  @Override
  public void deleteFeature(long date) {
    modelDatabase.modelDao().deleteFeature(date);
  }
}
