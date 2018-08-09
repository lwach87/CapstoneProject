package com.example.lukaszwachowski.capstoneproject.network;

import com.example.lukaszwachowski.capstoneproject.network.model.Model;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ModelService {

  @GET("fdsnws/event/1/query?format=geojson&minsig=470")
  Call<Model> getData();
}
