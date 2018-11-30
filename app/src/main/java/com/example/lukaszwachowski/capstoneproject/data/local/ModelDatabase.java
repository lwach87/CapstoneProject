package com.example.lukaszwachowski.capstoneproject.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import com.example.lukaszwachowski.capstoneproject.data.local.CoordinatesConverter;
import com.example.lukaszwachowski.capstoneproject.data.local.ModelDao;
import com.example.lukaszwachowski.capstoneproject.data.model.Feature;

@Database(entities = Feature.class, version = 1, exportSchema = false)
@TypeConverters(CoordinatesConverter.class)
public abstract class ModelDatabase extends RoomDatabase {

  public abstract ModelDao modelDao();
}
