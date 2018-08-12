package com.delivery.startup.creesol.driversapp;

import android.graphics.Bitmap;

import com.google.android.gms.maps.model.LatLng;

public class MarkerData {


    double latitude;
    double longitude;
    String title;

    public MarkerData(double latitude, double longitude, String title){
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
