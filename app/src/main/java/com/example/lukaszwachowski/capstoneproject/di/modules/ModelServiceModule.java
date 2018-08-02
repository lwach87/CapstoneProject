package com.example.lukaszwachowski.capstoneproject.di.modules;

import com.example.lukaszwachowski.capstoneproject.di.MainActivityScope;
import com.example.lukaszwachowski.capstoneproject.network.ModelService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ModelServiceModule {

  @Provides
  @MainActivityScope
  public Retrofit retrofit() {
    return new Retrofit.Builder()
        .baseUrl("https://earthquake.usgs.gov")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  @Provides
  @MainActivityScope
  public ModelService modelService(Retrofit retrofit) {
    return retrofit.create(ModelService.class);
  }
}
