package com.example.lukaszwachowski.capstoneproject.ui.details;


import static com.example.lukaszwachowski.capstoneproject.helper.Coordinates.distance;
import static com.example.lukaszwachowski.capstoneproject.helper.ValueComparator.sortByValue;
import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

import android.Manifest.permission;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.lukaszwachowski.capstoneproject.R;
import com.example.lukaszwachowski.capstoneproject.data.model.Feature;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import dagger.android.support.AndroidSupportInjection;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;

public class DetailsFragment extends Fragment {

  @Inject
  ViewModelProvider.Factory factory;

  private DetailsFragmentViewModel viewModel;
  private Map<String, Double> map = new HashMap<>();
  private CompositeDisposable disposable = new CompositeDisposable();

  @BindView(R.id.tv_distance)
  TextView distance;

  @BindView(R.id.web_view)
  WebView webView;

  public DetailsFragment() {
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    AndroidSupportInjection.inject(this);

    viewModel = ViewModelProviders.of(this, factory).get(DetailsFragmentViewModel.class);
  }

  @Override
  public void onStart() {
    super.onStart();

    startLocationUpdates();
  }

  private void startLocationUpdates() {
    LocationRequest locationRequest = new LocationRequest();
    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    locationRequest.setInterval(10 * 1000);
    locationRequest.setFastestInterval(2000);

    LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
    builder.addLocationRequest(locationRequest);
    LocationSettingsRequest locationSettingsRequest = builder.build();

    SettingsClient settingsClient = LocationServices.getSettingsClient(getContext());
    settingsClient.checkLocationSettings(locationSettingsRequest);

    if (ActivityCompat.checkSelfPermission(getContext(), permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat
          .requestPermissions(getActivity(), new String[]{permission.ACCESS_FINE_LOCATION}, 20);
      return;
    }

    getFusedLocationProviderClient(getContext())
        .requestLocationUpdates(locationRequest, new LocationCallback() {
          @Override
          public void onLocationResult(LocationResult locationResult) {
            Location location = locationResult.getLastLocation();
            addDataToView(location.getLatitude(), location.getLongitude());
          }
        }, Looper.myLooper());
  }

  private void addDataToView(double lat, double lon) {
    disposable.add(viewModel.getFeatures()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(features -> {
          for (Feature feature : features) {
            map.put(feature.getId(), distance(feature.getGeometry().getCoordinates().get(1),
                feature.getGeometry().getCoordinates().get(0), lat, lon));
          }
          Map<String, Double> result = sortByValue(map);
          String[] array = String.valueOf(result.entrySet().iterator().next()).split("=");

          distance.setText(String.valueOf((int) Double.parseDouble(array[1])));

          for (Feature feature : features) {
            if (feature.getId().equals(array[0])) {
              webView.getSettings().setLoadsImagesAutomatically(true);
              webView.loadUrl(feature.getProperties().getUrl());
            }
          }
        }));
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_details, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    disposable.clear();
  }
}
