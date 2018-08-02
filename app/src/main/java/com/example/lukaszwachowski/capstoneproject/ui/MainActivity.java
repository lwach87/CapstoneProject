package com.example.lukaszwachowski.capstoneproject.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import com.example.lukaszwachowski.capstoneproject.EarthquakeApp;
import com.example.lukaszwachowski.capstoneproject.R;
import com.example.lukaszwachowski.capstoneproject.di.components.DaggerMainActivityComponent;
import com.example.lukaszwachowski.capstoneproject.di.modules.MainActivityModule;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

  @Inject
  ListAdapter listAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    DaggerMainActivityComponent.builder()
        .applicationComponent(EarthquakeApp.get(this).getComponent())
        .mainActivityModule(new MainActivityModule(this))
        .build().inject(this);
  }
}
