package com.example.lukaszwachowski.capstoneproject.di.components;

import com.example.lukaszwachowski.capstoneproject.db.Repository;
import com.example.lukaszwachowski.capstoneproject.di.modules.DatabaseModule;
import com.example.lukaszwachowski.capstoneproject.di.modules.ModelServiceModule;
import com.example.lukaszwachowski.capstoneproject.fragments.details.DetailsFragment;
import com.example.lukaszwachowski.capstoneproject.fragments.list.ListFragment;
import com.example.lukaszwachowski.capstoneproject.fragments.map.MapFragment;
import com.example.lukaszwachowski.capstoneproject.network.DataSyncIntentService;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {DatabaseModule.class, ModelServiceModule.class})
public interface ApplicationComponent {

  Repository getRepository();

  void inject(DataSyncIntentService service);

  void inject(ListFragment listFragment);

  void inject(MapFragment mapFragment);

  void inject(DetailsFragment detailsFragment);
}
