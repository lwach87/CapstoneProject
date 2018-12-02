package com.example.lukaszwachowski.capstoneproject.di.modules;

import static com.example.lukaszwachowski.capstoneproject.utils.Constants.DATABASE_NAME;
import static com.example.lukaszwachowski.capstoneproject.utils.Constants.URL;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import com.example.lukaszwachowski.capstoneproject.data.DataManager;
import com.example.lukaszwachowski.capstoneproject.data.local.AppDbHelper;
import com.example.lukaszwachowski.capstoneproject.data.local.DbHelper;
import com.example.lukaszwachowski.capstoneproject.data.local.ModelDao;
import com.example.lukaszwachowski.capstoneproject.data.local.ModelDatabase;
import com.example.lukaszwachowski.capstoneproject.data.remote.ApiHelper;
import com.example.lukaszwachowski.capstoneproject.ui.main.MainActivity;
import com.example.lukaszwachowski.capstoneproject.utils.rx.AppSchedulerProvider;
import com.example.lukaszwachowski.capstoneproject.utils.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
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
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();
  }

  @Provides
  @Singleton
  ApiHelper provideApiHelper(Retrofit retrofit) {
    return retrofit.create(ApiHelper.class);
  }

  @Provides
  @Singleton
  DbHelper provideDbHelper(AppDbHelper appDbHelper) {
    return appDbHelper;
  }

  @Provides
  @Singleton
  DataManager provideDataManager(ApiHelper apiHelper, DbHelper dbHelper, Context context) {
    return new DataManager(apiHelper, dbHelper, context);
  }

  @Provides
  SchedulerProvider provideSchedulerProvider() {
    return new AppSchedulerProvider();
  }
}
