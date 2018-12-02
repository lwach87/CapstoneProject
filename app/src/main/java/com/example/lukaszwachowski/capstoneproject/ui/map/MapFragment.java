package com.example.lukaszwachowski.capstoneproject.ui.map;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import com.example.lukaszwachowski.capstoneproject.data.model.Feature;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.maps.android.clustering.ClusterManager;
import dagger.android.support.AndroidSupportInjection;
import javax.inject.Inject;

public class MapFragment extends SupportMapFragment implements OnMapReadyCallback {

  @Inject
  ViewModelProvider.Factory factory;

  private GoogleMap map;
  private ClusterManager<MarkerItem> clusterManager;
  private MapFragmentViewModel viewModel;

  @Override
  public void onMapReady(GoogleMap googleMap) {

    map = googleMap;
    map.getUiSettings().setZoomControlsEnabled(true);
    map.setMapType(GoogleMap.MAP_TYPE_HYBRID);

    clusterManager = new ClusterManager<>(getContext(), map);
    map.setOnCameraIdleListener(clusterManager);

    viewModel.getFeatures();
    subscribeToLiveData();
  }

  @Override
  public void onCreate(Bundle bundle) {
    super.onCreate(bundle);

    AndroidSupportInjection.inject(this);

    viewModel = ViewModelProviders.of(this, factory).get(MapFragmentViewModel.class);
  }

  private void subscribeToLiveData() {
    viewModel.getFeaturesLiveData().observe(this, features -> {
      for (Feature feature : features) {
        MarkerItem markerItem = new MarkerItem(feature);
        clusterManager.addItem(markerItem);
      }
    });
  }

  @Override
  public void onStart() {
    super.onStart();
    if (map == null) {
      getMapAsync(this);
    }
  }
}
