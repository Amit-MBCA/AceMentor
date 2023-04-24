package com.example.ib;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class mConfirmed extends AppCompatActivity {
    private String date;
    private int day,month,year;
    private TextView showDate;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mconfirmed);
        getSupportActionBar().hide();

        showDate=findViewById(R.id.showDate);
        Intent it=getIntent();
        day=it.getIntExtra("day",day);
        month=it.getIntExtra("month",month);
        year=it.getIntExtra("year",year);
        date=day+"-"+month+"-"+year;
        showDate.setText(date);

    }
}