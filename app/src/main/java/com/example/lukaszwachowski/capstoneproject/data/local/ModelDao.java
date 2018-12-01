package com.example.lukaszwachowski.capstoneproject.data.local;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.example.lukaszwachowski.capstoneproject.data.model.Feature;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.util.List;

@Dao
public interface ModelDao {

  @Query("SELECT * FROM feature ORDER BY date DESC")
  Flowable<List<Feature>> getFeatures();

  @Query("SELECT * FROM feature WHERE sig = (SELECT max(sig) FROM feature)")
  Single<Feature> getFeatureBySig();

  @Insert(onConflict = REPLACE)
  void insertFeatures(List<Feature> features);

  @Query("DELETE FROM feature WHERE date < :date")
  void deleteFeature(long date);
}
