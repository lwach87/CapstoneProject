package com.example.lukaszwachowski.capstoneproject.di.components;

import com.example.lukaszwachowski.capstoneproject.MainActivity;
import com.example.lukaszwachowski.capstoneproject.di.MainActivityScope;
import com.example.lukaszwachowski.capstoneproject.di.modules.ModelServiceModule;
import dagger.Component;

@MainActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ModelServiceModule.class)
public interface MainActivityComponent {

  void inject(MainActivity mainActivity);
}
