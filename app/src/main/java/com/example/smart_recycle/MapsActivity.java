package com.example.smart_recycle;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.smart_recycle.databinding.ActivityMapsBinding;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private final int ZOOM_LEVEL = 18;
    private final int ANIMATION_DURATION_MILISECONDS = 5000;
    private List<GarbageMarker> garbageMarkers;

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        garbageMarkers = GetAllGarbageMarkers();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    private List<GarbageMarker> GetAllGarbageMarkers() {
        // Here we can get all the garbage markes but for now we are gonna hard code it
        List<GarbageMarker> garbageMarkers = new ArrayList<GarbageMarker>();

        garbageMarkers.add(new GarbageMarker("Bottle of Water", 45.7574195,21.2280983));
        garbageMarkers.add(new GarbageMarker("Dorna", 45.7573986,21.2278718));
        garbageMarkers.add(new GarbageMarker("Windows", 45.7576492,21.2271313));
        garbageMarkers.add(new GarbageMarker("Hello World", 45.7571009,21.228577));
        garbageMarkers.add(new GarbageMarker("Idk to put here", 45.7568755,21.2286756));
        
        return garbageMarkers;
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng startingPoint = new LatLng(45.7568755, 21.2286756);
        for(GarbageMarker gm : garbageMarkers){
            LatLng gmpos = new LatLng(gm.Longitude, gm.Latitude);
            mMap.addMarker(new MarkerOptions().position(gmpos).title(gm.Name));
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(startingPoint, ZOOM_LEVEL),ANIMATION_DURATION_MILISECONDS, null);
    }
}