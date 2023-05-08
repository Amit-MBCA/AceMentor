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
    private int day,month,year;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mconfirmed);
        getSupportActionBar().hide();
        ImageView backbtn = findViewById(R.id.imageView);
        TextView showDate = findViewById(R.id.showDate);


        //Getting data from the scheduleMeet Activity
        Intent it=getIntent();
        String date = it.getStringExtra("selectedDate");
        String userMail = it.getStringExtra("UserMail");
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

    /// to call the java mail api sending mail method
    private void sendMail(String userMail) {
        String subject = "Confirmation Mail";
        String message = "Your Request for mentor support is sent to your selected mentor.";

        JavaMailAPI javaMailAPI = new JavaMailAPI(this, userMail,subject,message);

        javaMailAPI.execute();
    }
}