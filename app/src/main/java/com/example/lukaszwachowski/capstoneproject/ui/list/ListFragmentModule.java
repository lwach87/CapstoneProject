package com.example.lukaszwachowski.capstoneproject.ui.list;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import com.example.lukaszwachowski.capstoneproject.ViewModelProviderFactory;
import com.example.lukaszwachowski.capstoneproject.data.DataManager;
import com.example.lukaszwachowski.capstoneproject.ui.main.MainActivity;
import com.example.lukaszwachowski.capstoneproject.utils.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;

@Module
public class ListFragmentModule {

  @Provides
  ListAdapter providesListAdapter(Context context) {
    return new ListAdapter(context);
  }

  @Provides
  ListFragmentViewModel provideListFragmentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
    return new ListFragmentViewModel(dataManager, schedulerProvider);
  }

  @Provides
  ViewModelProvider.Factory listViewModelProvider(ListFragmentViewModel listFragmentViewModel) {
    return new ViewModelProviderFactory<>(listFragmentViewModel);
  }
}
