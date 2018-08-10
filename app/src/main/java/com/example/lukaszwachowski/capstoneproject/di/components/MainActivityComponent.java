package com.example.lukaszwachowski.capstoneproject.di.components;

import com.example.lukaszwachowski.capstoneproject.di.MainActivityScope;
import com.example.lukaszwachowski.capstoneproject.di.modules.MainActivityModule;
import com.example.lukaszwachowski.capstoneproject.ui.MainActivity;
import dagger.Component;

@MainActivityScope
@Component(dependencies = ApplicationComponent.class, modules = {MainActivityModule.class})
public interface MainActivityComponent {

  void inject(MainActivity mainActivity);
}
