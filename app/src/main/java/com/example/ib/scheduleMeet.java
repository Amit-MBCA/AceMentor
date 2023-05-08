package com.example.ib;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Api.JavaMailAPI;

public class scheduleMeet extends AppCompatActivity {
    private TextView pickDay, pickTime, getQuery;
    private int day,currentMonth=0;
    private int month ;
    private int year;
    private Button getForm;
    private int selectedhours=0;
    private ImageView backbtn;
    private String am_pm="am",query;
    private String currentDay,selectedTime,selectedDay="2040/04/30", userMail, userName;
    private int upcompingday=1;

    public static String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_meet);
        getSupportActionBar().hide();

        SharedPreferences shrd1=getSharedPreferences(Signup.PREFS_NAME,MODE_PRIVATE);
        userMail = shrd1.getString("uEmail", "User Mail");
        userName = shrd1.getString("uName","User Name");

        //Getting Intent data from the MentorsAdapter
        Bundle extras = getIntent().getExtras();
        String MentorEmail = extras.getString("email");
        String MentorName = extras.getString("MentorName");
        String MentorSubject = extras.getString("mentorSubject");

        String[] addresses = {MentorEmail};
        String[] cc = {userMail};

        pickDay = findViewById(R.id.pickday);
        pickTime = findViewById(R.id.picktime);
        getQuery = findViewById(R.id.getQuery);
        Button getForm = findViewById(R.id.getFormBtn);

        currentDay= new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date());
        ImageView backbtn = findViewById(R.id.imageView);



        // Create an Activity Result Launcher for the email intent
        ActivityResultLauncher<Intent> emailLauncher =registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Toast.makeText(scheduleMeet.this, "resultCode : " + result, Toast.LENGTH_SHORT).show();
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // Email sent successfully
                        Intent it = new Intent(scheduleMeet.this, mConfirmed.class);
                        it.putExtra("selectedDate", selectedDay);
                        it.putExtra("UserMail", userMail);
                        startActivity(it);
                    } else {
                        // Activity was cancelled by the user
                        String errorMessage = (result.getData() != null && result.getData().getStringExtra("error") != null) ? result.getData().getStringExtra("error") : "Unknown error";
                        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
                    }
                });

        // Launch the email intent using the Activity Result Launcher


        // TO HANDLE THE ON CLICK OF THE DATE PICKER
        pickDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting
                // the instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        scheduleMeet.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our text view.
                                int moy=monthOfYear+1;
                                currentMonth=moy;

                                if(dayOfMonth<10||moy<10){
                                    if(dayOfMonth<10 && moy<10){
                                        selectedDay = year + "/0" + moy + "/0" + dayOfMonth;
                                        pickDay.setText(selectedDay);
                                    }
                                    else if(dayOfMonth<10) {
                                        selectedDay = year + "/0" + moy + "/" + dayOfMonth;
                                        pickDay.setText(selectedDay);
                                    }
                                    else{
                                        selectedDay = year + "/0" + moy + "/" + dayOfMonth;
                                        pickDay.setText(selectedDay);
                                    }
                                }
                                else{
                                    selectedDay = year + "/" + moy + "/" + dayOfMonth;
                                    pickDay.setText(selectedDay);
                                }

                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }
        });


        // TO HANDLE THE ON CLICK OF TIME PICKER
        pickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting the
                // instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting our hour, minute.
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);


                // on below line we are initializing our Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(scheduleMeet.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                // on below line we are setting selected time
                                // in our text view.
                                am_pm = hourOfDay < 12 ? "AM" : "PM";
                                selectedhours=hourOfDay;

                                if(hourOfDay==12){
                                    pickTime.setText(hourOfDay + ":" + minute + " " + am_pm);
                                    selectedTime=hourOfDay + ":" + minute + " " + am_pm;
                                }

                                else{
                                    pickTime.setText(hourOfDay%12 + ":" + minute + " " + am_pm);
                                    selectedTime=hourOfDay%12 + ":" + minute + " " + am_pm;
                                }

                            }
                        }, hour, minute, false);
                // at last we are calling show to
                // display our time picker dialog.
                timePickerDialog.show();
            }
        });


        // TO HANDLE THE ON CLICK SUBMIT BUTTON
        getForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query=getQuery.getText().toString();

//                SimpleDateFormat dtobj = new SimpleDateFormat("dd/MM/yyyy");
//
//                try {
//                    Date cd= dtobj.parse(currentDay);
//                    Date sd=dtobj.parse(selectedDay);
//                    upcompingday=sd.compareTo(cd);
//
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }

                int upcompingday=selectedDay.compareTo(currentDay);
                if (!(am_pm.equals("am"))&&!(currentMonth==0)) {
                    if(selectedhours>7&&selectedhours<18) {
                        if(!(query.isEmpty())) {
                            if (upcompingday == 1) {
                                Intent intent = new Intent(Intent.ACTION_SENDTO);
                                intent.setData(Uri.parse("mailto:")); // Only email apps handle this.
                                intent.putExtra(Intent.EXTRA_EMAIL, addresses);
                                intent.putExtra(Intent.EXTRA_CC, cc);
                                intent.putExtra(Intent.EXTRA_SUBJECT, "Mentor Guidance");
                                intent.putExtra(Intent.EXTRA_TEXT, "I need guidance in " + MentorSubject + ". I will be very grateful if you provide your valuable time. \n\n"
                                        + getQuery.getText().toString() + "\n\n\n Meeting Schedule \n\nDate - " + pickDay.getText().toString() +
                                        "\n\nTime - " + pickTime.getText().toString() + "\n\nRegards,\n" + userName);


                                if (intent.resolveActivity(getPackageManager()) != null) {
//                                    emailLauncher.launch(Intent.createChooser(intent, "Choose an Email client :"));
                                    startActivityForResult(Intent.createChooser(intent, "Choose an Email client :"), 1);

                                }
                                else{
                                    Toast.makeText(scheduleMeet.this,"no apps present in your phone",Toast.LENGTH_SHORT).show();
                                }

                            }
                            else{
                                Toast.makeText(scheduleMeet.this, "Select upcoming day", Toast.LENGTH_LONG).show();
                            }
                        }

                        else{
                            Toast.makeText(scheduleMeet.this,"Please tell us how we can assist you",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(scheduleMeet.this,"Select time according to working hours",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(scheduleMeet.this,"Please select date and time",Toast.LENGTH_SHORT).show();
                }

            }

        });


        /// TO HANDLE THE BACK BUTTON PRESS IN SCHEDULE_ACTIVITY
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(scheduleMeet.this,HomePage.class);
                startActivity(it);
                finish();
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(scheduleMeet.this,HomePage.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {

            Intent it=new Intent(scheduleMeet.this,mConfirmed.class);
            it.putExtra("selectedDate",selectedDay);
            it.putExtra("UserMail",userMail);
            startActivity(it);
        }
        else{
            Intent it=new Intent(scheduleMeet.this,scheduleMeet.class);
            Toast.makeText(this,"Retry to send the mail",Toast.LENGTH_SHORT).show();
            startActivity(it);
        }
    } //onActivityResult

}

