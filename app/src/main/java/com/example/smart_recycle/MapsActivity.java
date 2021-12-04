package com.example.smart_recycle;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.smart_recycle.databinding.ActivityMapsBinding;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private final int ZOOM_LEVEL = 18;
    private final int ANIMATION_DURATION_MILISECONDS = 5000;

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private DatabaseReference mDatabase;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    Button scan, maps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                // Ask the user to collect the garbage - get the scanner window - if it's good the marker goes off
                //System.out.println(marker.getPosition());
                return false;
            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference("GarbageMarkers");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mMap.clear();
                for(DataSnapshot garbageSnapshot: snapshot.getChildren()){
                    GarbageMarker gm = garbageSnapshot.getValue(GarbageMarker.class);
                    LatLng gmpos = new LatLng(gm.Longitude, gm.Latitude);
                    mMap.addMarker(new MarkerOptions().position(gmpos).title(gm.Name));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Sorry, we could not retrive the data");
            }
        });
        // Add a marker in Sydney and move the camera
        LatLng startingPoint = new LatLng(45.7568755, 21.2286756);

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(startingPoint, ZOOM_LEVEL),ANIMATION_DURATION_MILISECONDS, null);
    }
    //lol

}