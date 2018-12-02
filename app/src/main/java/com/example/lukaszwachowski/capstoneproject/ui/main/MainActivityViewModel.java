package com.example.lukaszwachowski.capstoneproject.ui.main;

import android.arch.lifecycle.MutableLiveData;
import com.example.lukaszwachowski.capstoneproject.data.DataManager;
import com.example.lukaszwachowski.capstoneproject.data.model.Feature;
import com.example.lukaszwachowski.capstoneproject.ui.base.BaseViewModel;
import com.example.lukaszwachowski.capstoneproject.utils.rx.SchedulerProvider;

public class MainActivityViewModel extends BaseViewModel {

  private MutableLiveData<Feature> featureLiveData;

  public MainActivityViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
    super(dataManager, schedulerProvider);
    featureLiveData = new MutableLiveData<>();
  }

  public void getMaxSigFeature() {
    getDisposable()
        .add(getDataManager()
            .getDbHelper()
            .getFeatureBySig()
            .subscribeOn(getSchedulerProvider().io())
            .observeOn(getSchedulerProvider().ui())
            .subscribe(feature -> featureLiveData.setValue(feature)));
  }

  public MutableLiveData<Feature> getFeatureLiveData() {
    return featureLiveData;
  }
}
