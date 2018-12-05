package com.example.lukaszwachowski.capstoneproject.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.lukaszwachowski.capstoneproject.R;
import com.example.lukaszwachowski.capstoneproject.widget.WidgetService;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import javax.inject.Inject;
import javax.inject.Named;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {

  @Inject
  DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

  @Inject
  @Named("MainActivity")
  ViewModelProvider.Factory factory;

  @Inject
  PagerAdapter pagerAdapter;

  @BindView(R.id.toolbar)
  Toolbar toolbar;

  @BindView(R.id.view_pager)
  ViewPager viewPager;

  @BindView(R.id.tab_layout)
  TabLayout tabLayout;

  private MainActivityViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    viewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel.class);
    viewModel.getMaxSigFeature();
    subscribeToLiveData();

    if (savedInstanceState == null) {
      viewModel.getDataManager().initializeData();
    }

    setSupportActionBar(toolbar);
    viewPager.setAdapter(pagerAdapter);
    viewPager.setOffscreenPageLimit(2);
    tabLayout.setupWithViewPager(viewPager);
  }

  @Override
  public AndroidInjector<Fragment> supportFragmentInjector() {
    return fragmentDispatchingAndroidInjector;
  }

  private void subscribeToLiveData() {
    viewModel.getFeatureLiveData().observe(this,
        feature -> WidgetService.startActionUpdateRecipeWidgets(getApplicationContext(), feature));
  }
}
