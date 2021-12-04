package com.example.smart_recycle;

import static com.google.android.gms.tasks.Tasks.await;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

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
    private Query query = dbUsers.child("Users").orderByChild("garbagePoints").limitToFirst(10);
    public TextView lista;
    String listastring="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_garbage_collectors);
        lista=(TextView)findViewById(R.id.listatop);
        lista.setText(listastring);
        DisplayTopUsers();
    }
    private void DisplayTopUsers() {

        List<User> topUsers = new ArrayList<User>();
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long i = dataSnapshot.getChildrenCount();
                if(i>10){
                    i=10;
                }
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    System.out.println(ds.getValue(User.class).name);
                    User user = ds.getValue(User.class);
                    listastring = i+". "+ user.name +": "+ user.garbagePoints+ "\n"+listastring;
                    i--;
                    lista.setText(listastring);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        query.addListenerForSingleValueEvent(eventListener);
    }
}