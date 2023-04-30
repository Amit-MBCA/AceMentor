package com.example.ib;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class scheduleMeet extends AppCompatActivity {
    private TextView pickDay, pickTime, getQuery;
    private int day,currentMonth=0;
    private int month;
    private int year;
    private Button getForm;
    private int selectedhours=0;
    private ImageView backbtn;
    private String am_pm="am",query;
    private String currentDay,selectedTime,selectedDay="2040/04/30";
    private int upcompingday=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_meet);
        getSupportActionBar().hide();
        //get intent data from the MentorsAdapter
        Bundle extras = getIntent().getExtras();
        String MentorEmail = extras.getString("email");
        String[] addresses = {MentorEmail};

        pickDay = findViewById(R.id.pickday);
        pickTime = findViewById(R.id.picktime);
        getQuery = findViewById(R.id.getQuery);
        getForm = findViewById(R.id.getFormBtn);
//        iday= Integer.parseInt(pickDay.getText().toString());
//        itime=Integer.parseInt(pickTime.getText().toString());
//        day=Integer.toString(iday);
//        time=Integer.toString(itime);
        currentDay= new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date());
        backbtn=findViewById(R.id.imageView);
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
                                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                                intent.putExtra(Intent.EXTRA_EMAIL, addresses);
                                intent.putExtra(Intent.EXTRA_SUBJECT, "Mentor Guidance");
                                intent.putExtra(Intent.EXTRA_TEXT, "I need guidance in " + "Subject Name" + ". I will be very grateful if you provide your valuable time. \n\n"
                                        + getQuery.getText().toString() + "\n\n\n Meeting Schedule \n\nDate - " + pickDay.getText().toString() +
                                        "\n\nTime - " + pickTime.getText().toString() + "\n\nRegards,\n" + "UserName");
                                intent.setType("sms/rfc822");
                                startActivityForResult(Intent.createChooser(intent, "Choose an Email client :"), 800);
                                if (intent.resolveActivity(getPackageManager()) != null) {
                                    v.getContext().startActivity(Intent.createChooser(intent, "Choose an email app"));
                                } else {
                                    Toast.makeText(scheduleMeet.this, "No App is Installed", Toast.LENGTH_LONG).show();
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
//                // Create the email activity result launcher
//                ActivityResultLauncher<Intent> emailLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
//                        result -> {
//                            // Check if the email was sent successfully
//                            if (result.getResultCode() == Activity.RESULT_OK) {
//                                // Start the confirmation activity
//                                Intent it = new Intent(scheduleMeet.this, mConfirmed.class);
//                                startActivity(it);
//                            } else {
//                                // Handle the case where the email was not sent successfully
//                            }
//                        });
//
//                // Start the email intent
//                emailLauncher.launch(Intent.createChooser(intent, "Send email..."));
//                }
//                else{
//                    Toast.makeText(getApplicationContext(), "Please select time and day", Toast.LENGTH_SHORT).show();
//                }
            }


        });
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
    public void startActivityForResult(@NonNull Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        if(requestCode== 800){
            Intent it=new Intent(scheduleMeet.this,mConfirmed.class);
            it.putExtra("selectedDate",selectedDay);
            startActivity(it);
            finish();
        }
    }
    //    @Override
//    protected void startActivityForResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == 800) {
//            //Called when returning from your email intent
//            Intent it=new Intent(scheduleMeet.this,mConfirmed.class);
//            startActivity(it);
//        }
//    }

}