package com.example.lukaszwachowski.capstoneproject.data.remote;

import com.example.lukaszwachowski.capstoneproject.data.model.Model;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiHelper {

  @GET("fdsnws/event/1/query?format=geojson&minsig=470")
  Call<Model> getData();
}
