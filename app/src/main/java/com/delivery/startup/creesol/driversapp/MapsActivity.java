package com.delivery.startup.creesol.driversapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private List<MarkerData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        list = new ArrayList<>();
        list.add(new MarkerData(33.618626,73.108370,"abdulkadeer"));
        list.add(new MarkerData(33.619815,33.619815,"abdullah"));
        list.add(new MarkerData(33.619511,73.106836,"khan"));
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.clear();
        Marker[] allMarkers = new Marker[list.size()];

        for (int i = 0; i < list.size(); i++)
        {
            LatLng latLng = new LatLng(list.get(i).latitude, list.get(i).longitude);
            if (googleMap != null) {
                googleMap.setOnMarkerClickListener(this);
                allMarkers[i] = googleMap.addMarker(new MarkerOptions().position(latLng).title(list.get(i).title));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17.0f));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));

            }
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if(marker.getTitle().equals("abdulkadeer")){
            Toast.makeText(getApplicationContext(),"abulkadeer",Toast.LENGTH_SHORT).show();
        }
        if(marker.getTitle().equals("abdullah")){
            Toast.makeText(getApplicationContext(),"abdullah",Toast.LENGTH_SHORT).show();
        }
        if(marker.getTitle().equals("khan")){
            Toast.makeText(getApplicationContext(),"khan",Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
