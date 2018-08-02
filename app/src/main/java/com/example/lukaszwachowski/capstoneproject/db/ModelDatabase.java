package com.example.lukaszwachowski.capstoneproject.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.example.lukaszwachowski.capstoneproject.network.model.Feature;

@Database(entities = Feature.class, version = 1, exportSchema = false)
public abstract class ModelDatabase extends RoomDatabase {

  public abstract ModelDao modelDao();
}
