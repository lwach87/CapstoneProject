package com.example.lukaszwachowski.capstoneproject.di.components;

import com.example.lukaszwachowski.capstoneproject.di.ListFragmentScope;
import com.example.lukaszwachowski.capstoneproject.di.modules.ListFragmentModule;
import com.example.lukaszwachowski.capstoneproject.fragments.list.ListFragment;
import dagger.Component;

@ListFragmentScope
@Component(modules = ListFragmentModule.class, dependencies = ApplicationComponent.class)
public interface ListFragmentComponent {

  void inject(ListFragment listFragment);
}
