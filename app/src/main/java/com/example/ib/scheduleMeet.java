package com.example.ib;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class scheduleMeet extends AppCompatActivity {
    private TextView pickDay,pickTime,getQuery;
    private int day,month,year;
    private Button getForm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_meet);


        //get intent data from the MentorsAdapter
        Bundle extras = getIntent().getExtras();
        String MentorEmail= extras.getString("email");


        getSupportActionBar().hide();
        pickDay = findViewById(R.id.pickday);
        pickTime = findViewById(R.id.picktime);
        getQuery= findViewById(R.id.getQuery);
        getForm=findViewById(R.id.getFormBtn);
        // on below line we are adding click listener for our pick date button
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
                                pickDay.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

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
                                String am_pm= hourOfDay < 12  ? "AM" : "PM";
                                pickTime.setText(hourOfDay + ":" + minute+" "+am_pm);
                            }
                        }, hour, minute, false);
                // at last we are calling show to
                // display our time picker dialog.
                timePickerDialog.show();
            }
        });
        getForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + MentorEmail));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Mentor Guidance");
                intent.putExtra(Intent.EXTRA_TEXT,  getQuery + ",\n\nI am writing to you regarding your studies. Please let me know if you need any assistance.\n\nRegards,\nYour teacher");


                if (intent.resolveActivity(getPackageManager()) != null) {
                    v.getContext().startActivity(Intent.createChooser(intent, "Choose an email app"));
                }
                else{
                    Toast.makeText(scheduleMeet.this,"No App is Installed", Toast.LENGTH_LONG).show();
                }




            }
        });
    }
}