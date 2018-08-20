package com.example.lukaszwachowski.capstoneproject.fragments.details;


import static com.example.lukaszwachowski.capstoneproject.helper.Coordinates.distance;
import static com.example.lukaszwachowski.capstoneproject.helper.ValueComparator.sortByValue;

import android.Manifest;
import android.Manifest.permission;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
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
import com.example.lukaszwachowski.capstoneproject.EarthquakeApp;
import com.example.lukaszwachowski.capstoneproject.R;
import com.example.lukaszwachowski.capstoneproject.fragments.CustomViewModelFactory;
import com.example.lukaszwachowski.capstoneproject.network.model.Feature;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;

public class DetailsFragment extends Fragment {

  @Inject
  CustomViewModelFactory factory;

  private FusedLocationProviderClient fusedLocationProviderClient;
  private DetailsFragmentViewModel viewModel;
  private Map<String, Double> map = new HashMap<>();
  private CompositeDisposable disposable = new CompositeDisposable();

  @BindView(R.id.text_view_distance)
  TextView distance;

  @BindView(R.id.web_view)
  WebView webView;

  public DetailsFragment() {
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

    ((EarthquakeApp) getActivity().getApplication()).getComponent().inject(this);

    viewModel = ViewModelProviders.of(this, factory).get(DetailsFragmentViewModel.class);

    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat
          .requestPermissions(getActivity(), new String[]{permission.ACCESS_FINE_LOCATION}, 20);
      return;
    }

    LocationRequest locationRequest = new LocationRequest();
    locationRequest.setInterval(120000);
    locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

    fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
  }

  LocationCallback locationCallback = new LocationCallback() {
    @Override
    public void onLocationResult(LocationResult locationResult) {
      super.onLocationResult(locationResult);
      Location location = locationResult.getLastLocation();
      addDataToView(location.getLatitude(), location.getLongitude());
    }
  };

  private void addDataToView(double lat, double lon) {
    disposable.add(viewModel.getFeatures()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(features -> {
          for (Feature feature : features) {
            map.put(feature.id, distance(feature.geometry.coordinates.get(1),
                feature.geometry.coordinates.get(0), lat, lon));
          }
          Map<String, Double> result = sortByValue(map);
          String[] array = String.valueOf(result.entrySet().iterator().next()).split("=");

          distance.setText(String.valueOf((int) Double.parseDouble(array[1])));

          for (Feature feature : features) {
            if (feature.id.equals(array[0])) {
              webView.getSettings().setLoadsImagesAutomatically(true);
              webView.loadUrl(feature.properties.url);
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
  public void onPause() {
    super.onPause();
    if (fusedLocationProviderClient != null) {
      fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    disposable.clear();
  }
}
