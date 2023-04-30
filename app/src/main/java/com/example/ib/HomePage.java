package com.example.ib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomePage extends AppCompatActivity {
    private SharedPreferences shrd1;
    private String img,std;
    private AppCompatButton gtmlbtn,gts,gts11;
    Uri uri;
    CircleImageView civ,subimg,subimg11,gtmli;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getSupportActionBar().hide();
        shrd1=getSharedPreferences(Signup.PREFS_NAME,MODE_PRIVATE);
        img=shrd1.getString("image_data","image");
        uri=Uri.parse(img);
        std=shrd1.getString("selectedStd","8th");

        civ=findViewById(R.id.gotopfpic);
        gtmlbtn=findViewById(R.id.gotomentorlistbtn);
        gtmli=findViewById(R.id.gotomentorlist);
        gts11=findViewById(R.id.subbtn2);
        subimg11=findViewById(R.id.subimg2);

        civ.setImageURI(uri);
        subimg=findViewById(R.id.subimg);
        gts=findViewById(R.id.subbtn);
        if(std.equals("8th")||std.equals("9th")||std.equals("10th")){
            gts11.setVisibility(View.INVISIBLE);
            subimg11.setVisibility(View.INVISIBLE);
            gts.setVisibility(View.VISIBLE);
            subimg.setVisibility(View.VISIBLE);

        }
        else{
            gts.setVisibility(View.INVISIBLE);
            subimg.setVisibility(View.INVISIBLE);
            gts11.setVisibility(View.VISIBLE);
            subimg11.setVisibility(View.VISIBLE);
        }


        civ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(HomePage.this,Profile.class);
                startActivity(it);
            }
        });
        gtmlbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkConnected()) {
                    Intent it = new Intent(HomePage.this, SlotActivity.class);
                    startActivity(it);
                }
                else{
                    Toast.makeText(HomePage.this,"Check your internet connection",Toast.LENGTH_SHORT).show();
                }
            }
        });
        gtmli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkConnected()) {
                    Intent it = new Intent(HomePage.this, SlotActivity.class);
                    startActivity(it);
                }
                else{
                    Toast.makeText(HomePage.this,"Check your internet connection",Toast.LENGTH_SHORT).show();
                }
            }
        });
        subimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkConnected()) {
                    Intent intent = new Intent(HomePage.this, ninethStudents.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Check your Internet Connection",Toast.LENGTH_SHORT).show();
                }
            }
        });
        gts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkConnected()) {
                    Intent intent = new Intent(HomePage.this, ninethStudents.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Check your Internet Connection",Toast.LENGTH_SHORT).show();
                }
            }
        });
        subimg11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkConnected()) {
                    Intent intent = new Intent(HomePage.this, eleventhStudents.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Check your Internet Connection",Toast.LENGTH_SHORT).show();
                }
            }
        });
        gts11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkConnected()) {
                    Intent intent = new Intent(HomePage.this,eleventhStudents.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Check your Internet Connection",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}