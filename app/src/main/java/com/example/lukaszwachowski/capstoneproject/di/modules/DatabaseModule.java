package com.example.lukaszwachowski.capstoneproject.di.modules;

import static com.example.lukaszwachowski.capstoneproject.helper.Constants.DATABASE_NAME;

import android.arch.persistence.room.Room;
import android.content.Context;
import com.example.lukaszwachowski.capstoneproject.db.ModelDao;
import com.example.lukaszwachowski.capstoneproject.db.ModelDatabase;
import com.example.lukaszwachowski.capstoneproject.db.Repository;
import com.example.lukaszwachowski.capstoneproject.network.NetworkDataSource;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class DatabaseModule {

  private Context context;

  public DatabaseModule(Context context) {
    this.context = context;
  }

  @Provides
  @Singleton
  public Context provideContext() {
    return context;
  }

  @Provides
  @Singleton
  public ModelDatabase modelDatabase(Context context) {
    return Room.databaseBuilder(context, ModelDatabase.class, DATABASE_NAME)
        .build();
  }

  @Provides
  @Singleton
  public ModelDao provideDao(ModelDatabase modelDatabase) {
    return modelDatabase.modelDao();
  }

  @Provides
  @Singleton
  public Repository provideRepository(ModelDao modelDao, NetworkDataSource dataSource) {
    return new Repository(modelDao, dataSource);
  }
}
