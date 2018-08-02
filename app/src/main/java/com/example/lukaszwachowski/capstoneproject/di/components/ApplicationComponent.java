package com.example.lukaszwachowski.capstoneproject.di.components;

import com.example.lukaszwachowski.capstoneproject.db.Repository;
import com.example.lukaszwachowski.capstoneproject.di.ApplicationScope;
import com.example.lukaszwachowski.capstoneproject.di.modules.ContextModule;
import com.example.lukaszwachowski.capstoneproject.di.modules.DatabaseModule;
import dagger.Component;

@ApplicationScope
@Component(modules = {ContextModule.class, DatabaseModule.class})
public interface ApplicationComponent {

  Repository getRepository();
}
