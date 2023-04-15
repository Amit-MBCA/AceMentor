package com.example.ib;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ib.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private SharedPreferences shrd;
    private Boolean hasSignedUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        shrd=getSharedPreferences(Signup.PREFS_NAME,MODE_PRIVATE);
        hasSignedUp=shrd.getBoolean("hasSignedUp",false);
        if(hasSignedUp){
            Intent it=new Intent(MainActivity.this,HomePage.class);
            startActivity(it);
            finish();
        }
        else{
            Intent it1=new Intent(MainActivity.this,Signup.class);
            startActivity(it1);
            finish();
        }
    }
}