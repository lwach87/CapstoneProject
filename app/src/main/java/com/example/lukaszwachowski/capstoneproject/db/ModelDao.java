package com.example.lukaszwachowski.capstoneproject.db;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.example.lukaszwachowski.capstoneproject.network.model.Feature;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.util.List;

@Dao
public interface ModelDao {

  @Query("SELECT * FROM feature")
  Flowable<List<Feature>> getFeatures();

  @Query("SELECT * FROM feature WHERE apiId = :apiId")
  Single<Feature> getFeatureById(String apiId);

  @Insert(onConflict = REPLACE)
  void insertFeature(Feature feature);

  @Delete
  void deleteFeature(Feature feature);
}
