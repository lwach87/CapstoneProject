package com.example.lukaszwachowski.capstoneproject.di.modules;

import static com.example.lukaszwachowski.capstoneproject.helper.Constants.URL;

import android.content.Context;
import com.example.lukaszwachowski.capstoneproject.network.ModelService;
import com.example.lukaszwachowski.capstoneproject.network.NetworkDataSource;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ModelServiceModule {

  @Provides
  @Singleton
  public Retrofit retrofit() {
    return new Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  @Provides
  @Singleton
  public ModelService modelService(Retrofit retrofit) {
    return retrofit.create(ModelService.class);
  }

  @Provides
  @Singleton
  public NetworkDataSource provideDataSource(ModelService service, Context context) {
    return new NetworkDataSource(service, context);
  }
}
