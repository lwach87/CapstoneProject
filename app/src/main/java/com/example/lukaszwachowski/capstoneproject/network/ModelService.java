package com.example.lukaszwachowski.capstoneproject.network;

import com.example.lukaszwachowski.capstoneproject.network.model.Model;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ModelService {

  @GET("fdsnws/event/1/query?format=geojson&minsig=500")
  Observable<Model> getData();
}
