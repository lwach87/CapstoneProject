package com.example.lukaszwachowski.capstoneproject.di.components;

import android.app.Application;
import com.example.lukaszwachowski.capstoneproject.EarthquakeApp;
import com.example.lukaszwachowski.capstoneproject.di.builder.ActivityBuilder;
import com.example.lukaszwachowski.capstoneproject.di.builder.DataServiceBuilder;
import com.example.lukaszwachowski.capstoneproject.di.modules.AppModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import javax.inject.Singleton;

//Component will provide injected instances by using modules
//AndroidInjectionModule - internal class, provides activities and fragments with given module
@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class,
    DataServiceBuilder.class})
public interface AppComponent {

  void inject(EarthquakeApp app);

  //Bind Application to component
  @Component.Builder
  interface Builder {

    @BindsInstance
    Builder application(Application application);

    AppComponent build();
  }
}
