package com.example.lukaszwachowski.capstoneproject.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class Properties implements Parcelable {

  private double mag;
  private String place;
  @SerializedName("time")
  private long date;
  private String url;
  private String alert;
  private int sig;
  @ColumnInfo(name = "mag_type")
  private String magType;
  private String title;

  public Properties(){

  }

  public double getMag() {
    return mag;
  }

  public void setMag(double mag) {
    this.mag = mag;
  }

  public String getPlace() {
    return place;
  }

  public void setPlace(String place) {
    this.place = place;
  }

  public long getDate() {
    return date;
  }

  public void setDate(long date) {
    this.date = date;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getAlert() {
    return alert;
  }

  public void setAlert(String alert) {
    this.alert = alert;
  }

  public int getSig() {
    return sig;
  }

  public void setSig(int sig) {
    this.sig = sig;
  }

  public String getMagType() {
    return magType;
  }

  public void setMagType(String magType) {
    this.magType = magType;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
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

