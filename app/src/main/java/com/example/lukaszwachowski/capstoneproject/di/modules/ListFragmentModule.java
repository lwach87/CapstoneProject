package com.example.lukaszwachowski.capstoneproject.di.modules;

import com.example.lukaszwachowski.capstoneproject.di.ListFragmentScope;
import com.example.lukaszwachowski.capstoneproject.fragments.list.ListAdapter;
import dagger.Module;
import dagger.Provides;

@Module
public class ListFragmentModule {

  @Provides
  @ListFragmentScope
  ListAdapter listAdapter() {
    return new ListAdapter();
  }
}
