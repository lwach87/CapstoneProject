package com.example.lukaszwachowski.capstoneproject.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.lukaszwachowski.capstoneproject.EarthquakeApp;
import com.example.lukaszwachowski.capstoneproject.R;
import com.example.lukaszwachowski.capstoneproject.di.components.DaggerMainActivityComponent;
import com.example.lukaszwachowski.capstoneproject.di.modules.MainActivityModule;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

  @Inject
  ListAdapter listAdapter;

  @Inject
  MainActivityViewModelFactory factory;

  @BindView(R.id.recycler_view)
  RecyclerView recyclerView;

  @BindView(R.id.main_activity)
  ViewGroup rootView;

  private MainActivityViewModel viewModel;
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

    viewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel.class);

    getDataFromDatabase();

    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(listAdapter);
  }

  private void getDataFromDatabase() {
    disposable.add(viewModel.getFeatures()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(features -> listAdapter.swapData(features), throwable ->
            Snackbar.make(rootView, throwable.getLocalizedMessage(), Snackbar.LENGTH_SHORT)
                .show()));
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    disposable.clear();
  }
}
