package com.example.appearthqueke;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Earthquake implements Parcelable {
    private String id;
    private String place;
    private double mangnitude;
    private long time;
    private double latitude;
    private double longitude;

    public Earthquake(String id, String place, double mangnitude, long time, double latitude, double longitude) {
        this.id = id;
        this.place = place;
        this.mangnitude = mangnitude;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Earthquake() {
    }

    protected Earthquake(Parcel in) {
        id = in.readString();
        place = in.readString();
        mangnitude = in.readDouble();
        time = in.readLong();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    public static final Creator<Earthquake> CREATOR = new Creator<Earthquake>() {
        @Override
        public Earthquake createFromParcel(Parcel in) {
            return new Earthquake(in);
        }

        @Override
        public Earthquake[] newArray(int size) {
            return new Earthquake[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public double getMangnitude() {
        return mangnitude;
    }

    public void setMangnitude(double mangnitude) {
        this.mangnitude = mangnitude;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(place);
        dest.writeDouble(mangnitude);
        dest.writeLong(time);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }
}
