package com.example.lukaszwachowski.capstoneproject.network.model;

import static com.example.lukaszwachowski.capstoneproject.helper.Constants.TABLE_NAME;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = TABLE_NAME)
public class Feature implements Parcelable {

  @PrimaryKey
  @NonNull
  public String id;

  @Embedded
  public Properties properties;

  @Embedded
  public Geometry geometry;

  public Feature() {

  }

  public Feature(Parcel in) {
    id = in.readString();
    properties = in.readParcelable(getClass().getClassLoader());
  }

  public static final Creator<Feature> CREATOR = new Creator<Feature>() {
    @Override
    public Feature createFromParcel(Parcel in) {
      return new Feature(in);
    }

    @Override
    public Feature[] newArray(int size) {
      return new Feature[size];
    }
  };

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(id);
    dest.writeParcelable(properties, flags);
  }
}
