package com.delivery.startup.creesol.driversapp;

import android.graphics.Bitmap;

import com.google.android.gms.maps.model.LatLng;

public class loginData  {



    String title;

    public loginData (String title){

        this.title = title;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

