package com.example.lukaszwachowski.capstoneproject.network.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Ignore;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class Properties implements Parcelable {

  public double mag;

  public String place;

  @SerializedName("time")
  public long date;

  public String url;

  public String alert;

  public int sig;

  @ColumnInfo(name = "mag_type")
  public String magType;

  public String title;

  public Properties() {

  }

  public Properties(Parcel in) {
    mag = in.readDouble();
    place = in.readString();
    date = in.readLong();
    url = in.readString();
    alert = in.readString();
    sig = in.readInt();
    magType = in.readString();
    title = in.readString();
  }

  public static final Creator<Properties> CREATOR = new Creator<Properties>() {
    @Override
    public Properties createFromParcel(Parcel in) {
      return new Properties(in);
    }

    @Override
    public Properties[] newArray(int size) {
      return new Properties[size];
    }
  };

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeDouble(mag);
    dest.writeString(place);
    dest.writeLong(date);
    dest.writeString(url);
    dest.writeString(alert);
    dest.writeInt(sig);
    dest.writeString(magType);
    dest.writeString(title);
  }
}

