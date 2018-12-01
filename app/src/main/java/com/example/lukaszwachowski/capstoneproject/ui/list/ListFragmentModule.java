package com.example.lukaszwachowski.capstoneproject.ui.list;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import com.example.lukaszwachowski.capstoneproject.ViewModelProviderFactory;
import com.example.lukaszwachowski.capstoneproject.data.local.Repository;
import dagger.Module;
import dagger.Provides;

@Module
public class ListFragmentModule {

  @Provides
  ListAdapter providesListAdapter(Context context) {
    return new ListAdapter(context);
  }

  @Provides
  ListFragmentViewModel provideListFragmentViewModel(Repository repository) {
    return new ListFragmentViewModel(repository);
  }

  @Provides
  ViewModelProvider.Factory listViewModelProvider(ListFragmentViewModel listFragmentViewModel) {
    return new ViewModelProviderFactory<>(listFragmentViewModel);
  }
}
