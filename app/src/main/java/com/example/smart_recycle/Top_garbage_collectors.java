package com.example.smart_recycle;

import static com.google.android.gms.tasks.Tasks.await;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Top_garbage_collectors extends AppCompatActivity {
    private DatabaseReference dbUsers = FirebaseDatabase.getInstance().getReference();
    private Query query = dbUsers.child("Users").orderByChild("garbagePoints");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_garbage_collectors);
        DisplayTopUsers();
    }
    private void DisplayTopUsers() {
        List<User> topUsers = new ArrayList<User>();
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    System.out.println(ds.getValue(User.class).name);
                    topUsers.add(ds.getValue(User.class));
                }
                // Sort the users
                //Collections.sort(topUsers, new Sortbypoints());
                for(User u: topUsers){
                    // Display all the users in app
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        query.addListenerForSingleValueEvent(eventListener);
    }
}