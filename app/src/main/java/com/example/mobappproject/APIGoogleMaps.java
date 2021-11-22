package com.example.mobappproject;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.mobappproject.databinding.ActivityApigoogleMapsBinding;

public class APIGoogleMaps extends FragmentActivity implements OnMapReadyCallback {
    Button btndirection2;

    private GoogleMap mMap;
    private ActivityApigoogleMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityApigoogleMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        btndirection2=(Button) findViewById(R.id.btndirection2);

        // Add a marker in Sydney and move the camera
        // map = googleMap;
        //        home = new LatLng(-6.257385, 106.618320);
        //        map.addMarker(new MarkerOptions().position(home).title("welcome my umn")).showInfoWindow();
        //        map.moveCamera(CameraUpdateFactory.newLatLng(home));
//                map.moveCamera(CameraUpdateFactory.newLatLngZoom(home, 16));
//                map.setTrafficEnabled(true);

        LatLng sydney = new LatLng(-6.215303, 106.8198675);

        mMap.addMarker(new MarkerOptions().position(sydney).title("Expedia Company"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 16));
        mMap.setTrafficEnabled(true);


        btndirection2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                mMap.addMarker(new MarkerOptions().position(sydney).title("Expedia Company"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 16));
                mMap.setTrafficEnabled(true);
            }
        });
    }
}