package com.example.lukaszwachowski.capstoneproject.ui.list;

import android.arch.lifecycle.MutableLiveData;
import com.example.lukaszwachowski.capstoneproject.data.DataManager;
import com.example.lukaszwachowski.capstoneproject.data.model.Feature;
import com.example.lukaszwachowski.capstoneproject.ui.base.BaseViewModel;
import com.example.lukaszwachowski.capstoneproject.utils.rx.SchedulerProvider;
import java.util.List;
import timber.log.Timber;

public class ListFragmentViewModel extends BaseViewModel {

  private MutableLiveData<List<Feature>> featuresLiveData;

  public ListFragmentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
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
            .subscribe(features -> featuresLiveData.setValue(features),
                error -> Timber.d("Error getting data: %s", error.getLocalizedMessage())));
  }

  public MutableLiveData<List<Feature>> getFeaturesLiveData() {
    return featuresLiveData;
  }
}
