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
import com.example.lukaszwachowski.capstoneproject.db.Repository;
import com.example.lukaszwachowski.capstoneproject.di.components.DaggerMainActivityComponent;
import com.example.lukaszwachowski.capstoneproject.di.modules.MainActivityModule;
import com.example.lukaszwachowski.capstoneproject.widget.WidgetService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

  @Inject
  PagerAdapter pagerAdapter;

  @Inject
  Repository repository;

  @BindView(R.id.toolbar)
  Toolbar toolbar;

  @BindView(R.id.view_pager)
  ViewPager viewPager;

  @BindView(R.id.tab_layout)
  TabLayout tabLayout;

  @BindView(R.id.activity_main)
  ViewGroup rootView;

  private CompositeDisposable disposable = new CompositeDisposable();

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
    viewPager.setOffscreenPageLimit(2);
    tabLayout.setupWithViewPager(viewPager);

    getFeature();
  }

  private void getFeature() {
    disposable.add(repository.getFeatureBySig()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(feature -> WidgetService.startActionUpdateRecipeWidgets(this, feature)));
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    disposable.clear();
  }
}
