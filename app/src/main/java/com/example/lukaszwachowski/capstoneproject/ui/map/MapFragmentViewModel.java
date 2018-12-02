package com.example.lukaszwachowski.capstoneproject.ui.map;

import android.arch.lifecycle.MutableLiveData;
import com.example.lukaszwachowski.capstoneproject.data.DataManager;
import com.example.lukaszwachowski.capstoneproject.data.model.Feature;
import com.example.lukaszwachowski.capstoneproject.ui.base.BaseViewModel;
import com.example.lukaszwachowski.capstoneproject.utils.rx.SchedulerProvider;
import java.util.List;

public class MapFragmentViewModel extends BaseViewModel {

  private MutableLiveData<List<Feature>> featuresLiveData;

  public MapFragmentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
    super(dataManager, schedulerProvider);
    featuresLiveData = new MutableLiveData<>();
  }

  public void getFeatures() {
    getDisposable()
        .add(getDataManager()
            .getDbHelper()
            .getFeatures()
            .subscribeOn(getSchedulerProvider().io())
            .observeOn(getSchedulerProvider().ui())
            .subscribe(features -> featuresLiveData.setValue(features)));
  }

  public MutableLiveData<List<Feature>> getFeaturesLiveData() {
    return featuresLiveData;
  }
}
