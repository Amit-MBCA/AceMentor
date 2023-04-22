package com.example.ib;

import static com.example.ib.Signup.hideSoftKeyboard;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {

    // Database reference declaration
    FirebaseDatabase db;
    DatabaseReference reference;

    Bitmap bitmap;
    Uri uri;


    private String user, mail, std, img, mentSubj = "a", mentorSubject;
    private TextView tvname, tvemail, tvstd, tvSelectSubj;
    private CircleImageView pimg;
    private boolean check;
    private Button customBtn, applyChanges;
    private Switch isMentor;
    private ArrayAdapter subjAdapter;
    private SharedPreferences shrd1, shrd2;
    private Spinner subjSpinner;
    private Boolean isOn, mentor, isCheck = false;
    public static String PREFS_NAME = "MyPrefsFile";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        applyChanges = findViewById(R.id.applyChangesBtn);

        tvSelectSubj = findViewById(R.id.selectSub);
        tvname = findViewById(R.id.pName);
        tvemail = findViewById(R.id.pEmail);
        tvstd = findViewById(R.id.pStd);
        pimg = findViewById(R.id.gotopfpic);
        customBtn = findViewById(R.id.cstBtn);
        isMentor = (Switch) findViewById(R.id.isMentor);

        shrd1 = getSharedPreferences(Signup.PREFS_NAME, MODE_PRIVATE);
        user = shrd1.getString("uName", "User Name");
        mail = shrd1.getString("uEmail", "User Mail");
        std = shrd1.getString("selectedStd", "8th");
        img = shrd1.getString("image_data", "image");
        mentor = shrd1.getBoolean("isMentor", false);

        mentorSubject = shrd1.getString("mentorSubject", "Science");
        if (std.equals("11th") || std.equals("12th")) {
            subjAdapter = ArrayAdapter.createFromResource(this, R.array.eSubjSpinner, R.layout.subj_spinner);
        } else {
            subjAdapter = ArrayAdapter.createFromResource(this, R.array.nSubjSpinner, R.layout.subj_spinner);
        }

        subjSpinner = findViewById(R.id.subjSpinner);
        subjSpinner.setAdapter(subjAdapter);
//        subjAdapter= ArrayAdapter.createFromResource(this,R.array.nSubjSpinner,R.layout.subj_spinner);
        subjAdapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
        if (mentor) {
            if (!std.equals("8th")) {
                subjSpinner.setVisibility(View.VISIBLE);
                tvSelectSubj.setVisibility(View.VISIBLE);
                isMentor.setChecked(true);
                isCheck = true;
                int spinnerpos = subjAdapter.getPosition(mentorSubject);
                subjSpinner.setSelection(spinnerpos);
            } else {
                subjSpinner.setVisibility(View.INVISIBLE);
                tvSelectSubj.setVisibility(View.INVISIBLE);
                isMentor.setChecked(false);
                applyChanges.setVisibility(View.INVISIBLE);
            }

        }
        uri = Uri.parse(img);      ///this the image path
        pimg.setImageURI(uri);
        tvname.setText(user);
        tvemail.setText(mail);
        tvstd.setText(std);

        if (std.equals("11th") || std.equals("12th")) {
            subjAdapter = ArrayAdapter.createFromResource(this, R.array.eSubjSpinner, R.layout.subj_spinner);
        } else {
            subjAdapter = ArrayAdapter.createFromResource(this, R.array.nSubjSpinner, R.layout.subj_spinner);
        }


        subjSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mentSubj = subjSpinner.getSelectedItem().toString();
                hideSoftKeyboard(Profile.this, view);
                shrd2 = getSharedPreferences(Signup.PREFS_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = shrd2.edit();
                editor.putString("mentorSubject", mentSubj);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Please select your subject", Toast.LENGTH_LONG).show();
            }
        });
        isMentor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isCheck = isChecked;
                if (isChecked) {
                    tvSelectSubj.setVisibility(View.VISIBLE);
                    subjSpinner.setVisibility(View.VISIBLE);
//                    applyChanges.setVisibility(View.VISIBLE);

                } else {
                    tvSelectSubj.setVisibility(View.INVISIBLE);
                    subjSpinner.setVisibility(View.INVISIBLE);
//                    applyChanges.setVisibility(View.INVISIBLE);
                }
            }
        });
        applyChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shrd2 = getSharedPreferences(Signup.PREFS_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = shrd2.edit();
                if (isCheck) {
                    editor.putBoolean("isMentor", true);

                    //Data upload to firebase
                    uploadToFirebase();

                } else {
                    editor.putBoolean("isMentor", false);
                }
                editor.putString("mentorSubject", mentSubj);
                editor.apply();
//                editor.commit();
                Intent intent = new Intent(Profile.this, HomePage.class);
                startActivity(intent);
                finish();
            }
        });
        if (std.equals("8th")) {
            isMentor.setVisibility(View.INVISIBLE);
        } else {
            isMentor.setVisibility(View.VISIBLE);
        }
        customBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Profile.this, Signup.class);
                startActivity(it);
                finish();
            }
        });
    }

    private void uploadToFirebase() {
        //Dialog to show percentage
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("File Uploader");
        dialog.show();

        //Firebase storage reference
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference uploader = storage.getReference("Image" + new Random().nextInt(50));

        uploader.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                dialog.dismiss();
                                ///Reference of Mentor class
                                Mentors mentors = new Mentors(user, mail, std, uri.toString(), mentSubj);

                                //Realtime database setup
                                db = FirebaseDatabase.getInstance();
                                reference = db.getReference("Mentors");
                                reference.child(mentSubj).setValue(mentors).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(Profile.this, "You are now Mentor", Toast.LENGTH_LONG).show();
                                    }
                                });

                                // < end > Realtime Database code

                            }
                        });
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        float percent = (100 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                        dialog.setMessage("Uploaded : " + (int) percent + "%");
                    }
                });
    }
}