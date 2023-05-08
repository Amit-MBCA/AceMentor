package com.example.ib;

import static com.example.ib.Signup.hideSoftKeyboard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomePage extends AppCompatActivity {
    private SharedPreferences shrd1;
    private String img,std,ID, tempSubject;
    private AppCompatButton gtmlbtn,gts,gts11;
    Uri uri;
    CircleImageView civ,subimg,subimg11,gtmli;
    private Spinner subjSpinner;
    private ArrayAdapter subjAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //get intent content
        Intent intent = getIntent();
        ID = intent.getStringExtra("ID");


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
                it.putExtra("Standard",std);
                startActivity(it);
            }
        });
        gtmlbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkConnected()) {
                    openDialog();
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
                    openDialog();
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
                    Intent intent = new Intent(HomePage.this, ninethStudents.class);
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
                    Intent intent = new Intent(HomePage.this,ninethStudents.class);
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



    ///DIALOG TO SELECT THE SUBJECT TO SEARCH FOR MENTOR
    public void openDialog(){

        //spinner code
        if (std.equals("11th") || std.equals("12th")) {
            subjAdapter = ArrayAdapter.createFromResource(this, R.array.eSubjSpinner,R.layout.subj_spinner);
        } else {
            subjAdapter = ArrayAdapter.createFromResource(this, R.array.nSubjSpinner, R.layout.subj_spinner);
        }

        final AlertDialog.Builder alert = new AlertDialog.Builder(HomePage.this);

        View view = getLayoutInflater().inflate(R.layout.custom_dialog,null);
        alert.setView(view);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.custom_dialog_box);

        //Ok and Cancel Button code
        Button btn_cancel = (Button)view.findViewById(R.id.btn_cancel);
        Button btn_ok = (Button)view.findViewById(R.id.btn_ok);

        subjSpinner =(Spinner)view.findViewById(R.id.subjectSpinner);
        subjSpinner.setAdapter(subjAdapter);

        subjAdapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
        int spinnerpos = subjAdapter.getPosition("Science");
        subjSpinner.setSelection(spinnerpos);


        subjSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tempSubject = subjSpinner.getSelectedItem().toString();
                hideSoftKeyboard(HomePage.this, view);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Please select your subject", Toast.LENGTH_LONG).show();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(HomePage.this, SlotActivity.class);
                    it.putExtra("Standard",std);
                    it.putExtra("Subject",tempSubject);
                    startActivity(it);

                    alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

}