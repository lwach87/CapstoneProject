package com.example.lukaszwachowski.capstoneproject.fragments.map;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import com.example.lukaszwachowski.capstoneproject.EarthquakeApp;
import com.example.lukaszwachowski.capstoneproject.fragments.CustomViewModelFactory;
import com.example.lukaszwachowski.capstoneproject.network.model.Feature;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.maps.android.clustering.ClusterManager;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;

public class MapFragment extends SupportMapFragment implements OnMapReadyCallback {

  @Inject
  CustomViewModelFactory factory;

  private GoogleMap map;
  private MapViewModel viewModel;
  private CompositeDisposable disposable = new CompositeDisposable();
  private ClusterManager<MarkerItem> clusterManager;

  @Override
  public void onMapReady(GoogleMap googleMap) {

    map = googleMap;
    map.getUiSettings().setZoomControlsEnabled(true);
    map.setMapType(GoogleMap.MAP_TYPE_HYBRID);

    clusterManager = new ClusterManager<>(getContext(), map);
    map.setOnCameraIdleListener(clusterManager);
    getDataFromDatabase();
  }

  @Override
  public void onCreate(Bundle bundle) {
    super.onCreate(bundle);

    ((EarthquakeApp) getActivity().getApplication()).getComponent().inject(this);

    viewModel = ViewModelProviders.of(this, factory).get(MapViewModel.class);
  }

  private void getDataFromDatabase() {
    disposable.add(viewModel.getFeatures()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(features -> {
          for (Feature feature : features) {
            MarkerItem markerItem = new MarkerItem(feature);
            clusterManager.addItem(markerItem);
          }
        }));
  }

  @Override
  public void onStart() {
    super.onStart();

    if (map == null) {
      getMapAsync(this);
    }
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    disposable.clear();
  }
}
