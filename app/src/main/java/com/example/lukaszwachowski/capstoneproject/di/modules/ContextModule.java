package com.example.lukaszwachowski.capstoneproject.di.modules;

import android.content.Context;
import com.example.lukaszwachowski.capstoneproject.di.ApplicationScope;
import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

  private Context context;

  public ContextModule(Context context) {
    this.context = context;
  }

  @ApplicationScope
  @Provides
  public Context Context() {
    return context;
  }
}
