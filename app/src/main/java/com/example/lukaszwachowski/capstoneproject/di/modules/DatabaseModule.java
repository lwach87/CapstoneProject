package com.example.lukaszwachowski.capstoneproject.di.modules;

import android.arch.persistence.room.Room;
import android.content.Context;
import com.example.lukaszwachowski.capstoneproject.db.ModelDao;
import com.example.lukaszwachowski.capstoneproject.db.ModelDatabase;
import com.example.lukaszwachowski.capstoneproject.db.Repository;
import com.example.lukaszwachowski.capstoneproject.di.ApplicationScope;
import com.example.lukaszwachowski.capstoneproject.network.NetworkDataSource;
import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

  @Provides
  @ApplicationScope
  public ModelDatabase modelDatabase(Context context) {
    return Room.databaseBuilder(context.getApplicationContext(), ModelDatabase.class, "features")
        .build();
  }

  @Provides
  @ApplicationScope
  public ModelDao provideDao(ModelDatabase modelDatabase) {
    return modelDatabase.modelDao();
  }

  @Provides
  @ApplicationScope
  public Repository provideRepository(ModelDao modelDao, NetworkDataSource dataSource) {
    return new Repository(modelDao, dataSource);
  }
}
