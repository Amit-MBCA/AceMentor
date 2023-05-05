package com.example.ib;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import Api.JavaMailAPI;

public class mConfirmed extends AppCompatActivity {
    private String date,userMail;
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
        date=it.getStringExtra("selectedDate");
        userMail = it.getStringExtra("UserMail");

//        int trynum=it.getIntExtra("trynum",10);
        showDate.setText(date);

      //  sendMail(userMail);         /// <<<< To send confirmation mail to user >>>>///


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(mConfirmed.this,HomePage.class);
                startActivity(it);
                finish();
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(mConfirmed.this,HomePage.class));
    }


    private void sendMail(String userMail) {
        String subject = "Confirmation Mail";
        String message = "Your Request for mentor support is sent to your selected mentor.";

        JavaMailAPI javaMailAPI = new JavaMailAPI(this, userMail,subject,message);

        javaMailAPI.execute();
    }
}