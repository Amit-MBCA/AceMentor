package com.example.ib;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        Thread thread=new Thread(){
            public void run(){
                try {
                    sleep(3000);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent intent=new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        thread.start();
    }
}