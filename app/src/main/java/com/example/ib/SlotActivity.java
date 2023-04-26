package com.example.ib;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class SlotActivity extends AppCompatActivity {

    private SharedPreferences shrd1;
    public static String PREFS_NAME="MyPrefsFile";
    private ImageView backbtn;
    // std = < fetch the subject value from shared preferences
    String standard = "8";


    List<Mentors> mentorsList = new ArrayList<>();
    DatabaseReference db;

    
    //Recycler View
    RecyclerView recview;
    MentorsAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot);

        getSupportActionBar().hide();
        Bundle extras = getIntent().getExtras();
        String subject= extras.getString("Subject");
        backbtn=(ImageView) findViewById(R.id.imageView);
        db = FirebaseDatabase.getInstance().getReference().child("Mentors");

        recview =(RecyclerView)findViewById(R.id.slotsrv);
        recview.setLayoutManager(new LinearLayoutManager(this));

        Query bookQuery= FirebaseDatabase
                .getInstance()
                .getReference()
                .child("Mentors").child(subject).orderByChild("user");

        FirebaseRecyclerOptions<Mentors> options =
                new FirebaseRecyclerOptions.Builder<Mentors>()
                        .setQuery(bookQuery, Mentors.class)
                        .build();

        adapter = new MentorsAdapter(options);
        recview.setAdapter(adapter);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(SlotActivity.this,HomePage.class);
                startActivity(it);
                finish();
            }
        });


                              //<<<<<<<<<< New Adapter setup Using Array of List >>>>>>>>>>>//

//        adapter = new MentorsAdapter(mentorsList,this);
//
//        db.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                Mentors pojo = dataSnapshot.getValue(Mentors.class);
//                adapter.notifyDataSetChanged();
//                if (pojo.mentSubj == "Science" ){
//                    mentorsList.add(pojo);
//                }
//
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@androidx.annotation.NonNull DatabaseError error) {
//
//            }
//        });
//        adapter.notifyDataSetChanged();
//        recview.setAdapter(adapter);
                                //<<<<<<<<<< New Adapter setup Using Array of List >>>>>>>>>>>//
    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}