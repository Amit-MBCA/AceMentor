package com.example.ib;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class mConfirmed extends AppCompatActivity {
    private String date;
    private int day,month,year;
    private TextView showDate;
    private ImageView backbtn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mconfirmed);
        getSupportActionBar().hide();
        backbtn=findViewById(R.id.imageView);
        showDate=findViewById(R.id.showDate);
        Intent it=getIntent();
        day=it.getIntExtra("day",day);
        month=it.getIntExtra("month",month);
        year=it.getIntExtra("year",year);
        date=day+"-"+month+"-"+year;
        showDate.setText(date);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(mConfirmed.this,HomePage.class);
                startActivity(it);
                finish();
            }
        });
    }
}