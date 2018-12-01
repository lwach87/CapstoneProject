package com.example.lukaszwachowski.capstoneproject.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.lukaszwachowski.capstoneproject.R;
import com.example.lukaszwachowski.capstoneproject.widget.WidgetService;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
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

  @BindView(R.id.activity_main)
  ViewGroup rootView;

  private CompositeDisposable disposable = new CompositeDisposable();
  private MainActivityViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    AndroidInjection.inject(this);

    viewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel.class);

    setSupportActionBar(toolbar);
    viewPager.setAdapter(pagerAdapter);
    viewPager.setOffscreenPageLimit(2);
    tabLayout.setupWithViewPager(viewPager);

    getFeature();
  }

  @Override
  public AndroidInjector<Fragment> supportFragmentInjector() {
    return fragmentDispatchingAndroidInjector;
  }

  private void getFeature() {
    disposable.add(viewModel.getFeatureBySig()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(feature -> WidgetService.startActionUpdateRecipeWidgets(this, feature),
            error -> Snackbar.make(rootView, error.getLocalizedMessage(), Snackbar.LENGTH_SHORT)
        ));
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    disposable.clear();
  }
}
