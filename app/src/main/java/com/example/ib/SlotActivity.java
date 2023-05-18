package com.example.ib;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SlotActivity extends AppCompatActivity {

    private SharedPreferences shrd1;
    public static String PREFS_NAME = "MyPrefsFile";
    FloatingActionButton backbtn;
    // std = < fetch the subject value from shared preferences
    String standard = "8";

    List<Mentors> mentorsList = new ArrayList<>();

    Integer tempStandard;

    //Recycler View
    RecyclerView recview;
    MentorsAdapter adapter;

    ProgressBar progressBar;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot);

        progressBar =findViewById(R.id.progressBarLoading);
        progressBar.setVisibility(View.VISIBLE);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        String studentStd = intent.getStringExtra("Standard");     //user standard
        String subject = intent.getStringExtra("Subject");                                      //user selected subject



        //temp standard of student (int type)
        switch (studentStd) {
            case "8th":
                tempStandard = 8;
                break;
            case "9th":
                tempStandard = 9;
                break;
            case "10th":
                tempStandard = 10;
                break;
            case "11th":
                tempStandard = 11;
                break;
            default:
                tempStandard = 12;
        }

        backbtn = (FloatingActionButton) findViewById(R.id.floating_back_button);

//        List<Mentors> mentorList = new ArrayList<>();

        recview = (RecyclerView) findViewById(R.id.slotsrv);
        recview.setLayoutManager(new LinearLayoutManager(this));

//        DatabaseReference db = FirebaseDatabase.getInstance().getReference();

        // attach a ValueEventListener to the query to traverse all subjects and mentor IDs
//        db.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {

//                if (dataSnapshot != null) {
//                    for (DataSnapshot mentorSnapshot : dataSnapshot.getChildren()) {
//                        for (DataSnapshot subjectSnapshot : mentorSnapshot.getChildren()) {
//                            for (DataSnapshot studentID : subjectSnapshot.getChildren()) {
//                                // do something with each student snapshot
//                                Mentors mentor = studentID.getValue(Mentors.class);
//                                mentorList.add(mentor);
//                            }
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // handle database error
//            }
//        });


        Query bookQuery= FirebaseDatabase
                .getInstance()
                .getReference()
                .child("Mentors").child(subject).orderByChild("std").startAt(tempStandard + 1);
//                .child("Mentors").child(subject).orderByChild("std").startAt(tempStandard + 1);

        bookQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Check if the data retrieved from Firebase is null
                progressBar.setVisibility(View.GONE);
                if (snapshot.getValue() == null) {
                    Toast.makeText(SlotActivity.this, "No Mentor Present", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SlotActivity.this, "Error In Fetching data", Toast.LENGTH_SHORT).show();
            }
        });

        FirebaseRecyclerOptions<Mentors> options =
                new FirebaseRecyclerOptions.Builder<Mentors>()
                        .setQuery(bookQuery, Mentors.class)
                        .build();


        adapter = new MentorsAdapter(options);
        recview.setAdapter(adapter);


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(SlotActivity.this, HomePage.class);
                startActivity(it);
                finish();
            }
        });
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