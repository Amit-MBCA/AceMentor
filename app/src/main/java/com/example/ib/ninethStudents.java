package com.example.ib;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ninethStudents extends AppCompatActivity {
    private TextView tvScience,tvMath,tvLangandLat,tvIndividuals,tvAcquisition,tvArts,tvCore;
    private String std,subj;
    SharedPreferences shrd;
    private ImageView backbtn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nineth_students);
        getSupportActionBar().hide();
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        tvScience=findViewById(R.id.tvScience);
        tvMath=findViewById(R.id.tvMath);
        tvLangandLat=findViewById(R.id.tvLangandLit);
        tvIndividuals=findViewById(R.id.tvIndividuals);
        tvAcquisition=findViewById(R.id.tvAcqusition);
        tvArts=findViewById(R.id.tvArts);
        backbtn=findViewById(R.id.fab);
        tvCore=findViewById(R.id.tvCore);
        shrd=getSharedPreferences(Signup.PREFS_NAME,MODE_PRIVATE);
        std=shrd.getString("selectedStd","8th");
        tvScience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent it=new Intent(ninethStudents.this,SlotActivity.class);
//                subj="Science";
//                it.putExtra("Subject",subj);
//                it.putExtra("std",std);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ibo.org/programmes/diploma-programme/curriculum/sciences/"));
                startActivity(browserIntent);
//                startActivity(browserIntent);
            }
        });
        tvMath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent it=new Intent(ninethStudents.this,SlotActivity.class);
//                subj="Math";
//                it.putExtra("Subject",subj);
//                it.putExtra("std",std);
//                startActivity(it);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ibo.org/programmes/diploma-programme/curriculum/mathematics/"));
                startActivity(browserIntent);

            }
        });
        tvLangandLat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent it=new Intent(ninethStudents.this,SlotActivity.class);
//                subj="Language and Literature";
//                it.putExtra("Subject",subj);
//                it.putExtra("std",std);
//                startActivity(it);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ibo.org/programmes/diploma-programme/curriculum/language-and-literature/"));
                startActivity(browserIntent);
            }
        });
        tvIndividuals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent it=new Intent(ninethStudents.this,SlotActivity.class);
//                subj="Individuals and Societies";
//                it.putExtra("Subject",subj);
//                it.putExtra("std",std);
//                startActivity(it);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ibo.org/programmes/diploma-programme/curriculum/individuals-and-societies/"));
                startActivity(browserIntent);
            }
        });
        tvAcquisition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent it=new Intent(ninethStudents.this,SlotActivity.class);
//                subj="Language Acquisition";
//                it.putExtra("Subject",subj);
//                it.putExtra("std",std);
//                startActivity(it);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ibo.org/programmes/diploma-programme/curriculum/language-acquisition/"));
                startActivity(browserIntent);
            }
        });
        tvArts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent it=new Intent(ninethStudents.this,SlotActivity.class);
//                subj="The Arts";
//                it.putExtra("Subject",subj);
//                it.putExtra("std",std);
//                startActivity(it);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ibo.org/programmes/diploma-programme/curriculum/the-arts/"));
                startActivity(browserIntent);
            }
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(ninethStudents.this,HomePage.class);
                startActivity(it);
                finish();
            }
        });
        tvCore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://img.nordangliaeducation.com/resources/asia/_filecache/3a6/e9c/98079-iboptions_core_ncm.pdf"));
                startActivity(browserIntent);
            }
        });


    }
}