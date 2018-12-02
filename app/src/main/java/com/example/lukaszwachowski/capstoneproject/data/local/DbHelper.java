package com.example.lukaszwachowski.capstoneproject.data.local;

import com.example.lukaszwachowski.capstoneproject.data.model.Feature;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.util.List;

public interface DbHelper {

  Flowable<List<Feature>> getFeatures();

  Single<Feature> getFeatureBySig();

  void insertFeatures(List<Feature> features);

  void deleteFeature(long date);
}
