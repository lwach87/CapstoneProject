package com.example.lukaszwachowski.capstoneproject.data.local;

import android.arch.persistence.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class CoordinatesConverter {

  @TypeConverter
  public static List<Double> stringToCoordinates(String data) {
    if (data == null) {
      return Collections.emptyList();
    }
    Gson gson = new Gson();
    Type listType = new TypeToken<List<Double>>() {
    }.getType();
    return gson.fromJson(data, listType);
  }

  @TypeConverter
  public static String coordinatesToString(List<Double> coordinates) {
    Gson gson = new Gson();
    return gson.toJson(coordinates);
  }
}
