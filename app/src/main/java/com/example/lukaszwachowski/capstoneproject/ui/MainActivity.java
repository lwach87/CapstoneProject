package com.example.lukaszwachowski.capstoneproject.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.lukaszwachowski.capstoneproject.EarthquakeApp;
import com.example.lukaszwachowski.capstoneproject.R;
import com.example.lukaszwachowski.capstoneproject.di.components.DaggerMainActivityComponent;
import com.example.lukaszwachowski.capstoneproject.di.modules.MainActivityModule;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

  @Inject
  PagerAdapter pagerAdapter;

  @BindView(R.id.toolbar)
  Toolbar toolbar;

  @BindView(R.id.view_pager)
  ViewPager viewPager;

  @BindView(R.id.tab_layout)
  TabLayout tabLayout;

  @BindView(R.id.activity_main)
  ViewGroup rootView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    DaggerMainActivityComponent.builder()
        .applicationComponent(EarthquakeApp.get(this).getComponent())
        .mainActivityModule(new MainActivityModule(this))
        .build().inject(this);

    setSupportActionBar(toolbar);
    viewPager.setAdapter(pagerAdapter);
    tabLayout.setupWithViewPager(viewPager);
  }
}
