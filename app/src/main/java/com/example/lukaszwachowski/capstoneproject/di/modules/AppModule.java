package com.example.lukaszwachowski.capstoneproject.di.modules;

import static com.example.lukaszwachowski.capstoneproject.helper.Constants.DATABASE_NAME;
import static com.example.lukaszwachowski.capstoneproject.helper.Constants.URL;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import com.example.lukaszwachowski.capstoneproject.data.local.ModelDao;
import com.example.lukaszwachowski.capstoneproject.data.local.ModelDatabase;
import com.example.lukaszwachowski.capstoneproject.data.local.Repository;
import com.example.lukaszwachowski.capstoneproject.data.remote.ApiHelper;
import com.example.lukaszwachowski.capstoneproject.data.NetworkDataSource;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

  @Provides
  @Singleton
  Context context(Application application) {
    return application;
  }

  @Provides
  @Singleton
  ModelDatabase modelDatabase(Context context) {
    return Room.databaseBuilder(context, ModelDatabase.class, DATABASE_NAME)
        .build();
  }

  @Provides
  @Singleton
  ModelDao provideDao(ModelDatabase modelDatabase) {
    return modelDatabase.modelDao();
  }

  @Provides
  @Singleton
  Retrofit retrofit() {
    return new Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  @Provides
  @Singleton
  ApiHelper provideApiHelper(Retrofit retrofit) {
    return retrofit.create(ApiHelper.class);
  }

  @Provides
  @Singleton
  NetworkDataSource provideDataSource(ApiHelper apiHelper, Context context) {
    return new NetworkDataSource(apiHelper, context);
  }

  @Provides
  @Singleton
  Repository provideRepository(ModelDao modelDao, NetworkDataSource dataSource) {
    return new Repository(modelDao, dataSource);
  }
}
