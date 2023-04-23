package com.example.ib;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ninethStudents extends AppCompatActivity {
    private TextView tvScience,tvMath,tvLangandLat,tvIndividuals,tvAcquisition,tvArts,tvCore;
    private String std,subj;
    SharedPreferences shrd;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nineth_students);
        getSupportActionBar().hide();
        tvScience=findViewById(R.id.tvScience);
        tvMath=findViewById(R.id.tvMath);
        tvLangandLat=findViewById(R.id.tvLangandLit);
        tvIndividuals=findViewById(R.id.tvIndividuals);
        tvAcquisition=findViewById(R.id.tvAcqusition);
        tvArts=findViewById(R.id.tvArts);
        tvCore=findViewById(R.id.tvCore);
        shrd=getSharedPreferences(Signup.PREFS_NAME,MODE_PRIVATE);
        std=shrd.getString("selectedStd","8th");
        tvScience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(ninethStudents.this,SlotActivity.class);
                subj="Science";
                it.putExtra("Subject",subj);
                it.putExtra("std",std);
                startActivity(it);
            }
        });
        tvMath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(ninethStudents.this,SlotActivity.class);
                subj="Math";
                it.putExtra("Subject",subj);
                it.putExtra("std",std);
                startActivity(it);
            }
        });
        tvLangandLat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(ninethStudents.this,SlotActivity.class);
                subj="Language and Literature";
                it.putExtra("Subject",subj);
                it.putExtra("std",std);
                startActivity(it);
            }
        });
        tvIndividuals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(ninethStudents.this,SlotActivity.class);
                subj="Individuals and Societies";
                it.putExtra("Subject",subj);
                it.putExtra("std",std);
                startActivity(it);
            }
        });
        tvAcquisition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(ninethStudents.this,SlotActivity.class);
                subj="Language Acquisition";
                it.putExtra("Subject",subj);
                it.putExtra("std",std);
                startActivity(it);
            }
        });
        tvArts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(ninethStudents.this,SlotActivity.class);
                subj="The Arts";
                it.putExtra("Subject",subj);
                it.putExtra("std",std);
                startActivity(it);
            }
        });
        tvCore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(ninethStudents.this,SlotActivity.class);
                subj="Core Components";
                it.putExtra("Subject",subj);
                it.putExtra("std",std);
                startActivity(it);
            }
        });


    }
}