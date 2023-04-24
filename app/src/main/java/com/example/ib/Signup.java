package com.example.ib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ib.databinding.ActivitySignupBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.util.UUID;

public class Signup extends AppCompatActivity {

    //creating a string which carry random number for Id of student
    private static String id = UUID.randomUUID().toString();

//    ImageView profpic;
    ActivitySignupBinding binding;
    private ArrayAdapter stdAdapter;

    private Uri uri;
    private int spinnerpos;
    private String encodedImage,selectedstd,user,std,mail,img;
    SharedPreferences userdata;
    public static String PREFS_NAME="MyPrefsFile";
    private static int flag=0;
    private boolean prpic,hasSignedup=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        binding.tvtagline.setText("Seamlessly soar into the Next Chapter:\nYour Academic Journey Made Effortless");
        userdata=getSharedPreferences(Signup.PREFS_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor = userdata.edit();
        stdAdapter=ArrayAdapter.createFromResource(this,R.array.setStandard,R.layout.std_spinner);
        stdAdapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
        binding.stdSpinner.setAdapter(stdAdapter);
        binding.stdSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedstd=binding.stdSpinner.getSelectedItem().toString();
                hideSoftKeyboard(Signup.this,view);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(),"Please select your standard",Toast.LENGTH_LONG).show();
            }
        });
        SharedPreferences shrd1=getSharedPreferences(Signup.PREFS_NAME,MODE_PRIVATE);
        String tempID = shrd1.getString("ID","123");

        id = tempID;

        SharedPreferences.Editor editor1=shrd1.edit();
        hasSignedup=shrd1.getBoolean("hasSignedUp",false);
        if(hasSignedup){
            binding.rgstbtn.setText("Save Changes");
            editor1.putBoolean("isMentor",false);
            editor1.apply();
            user=shrd1.getString("uName","User Name");
            mail=shrd1.getString("uEmail","User Mail");
            std=shrd1.getString("selectedStd","8th");
            img=shrd1.getString("image_data","image");
            Uri uri=Uri.parse(img);
            spinnerpos=stdAdapter.getPosition(std);
            binding.profpic.setImageURI(uri);
            binding.userName.setText(user);
            binding.userEmail.setText(mail);
            binding.stdSpinner.setSelection(spinnerpos);
        }
        binding.profpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(Signup.this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080,1080)
                        .start();
            }
        });
        binding.rgstbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uName=binding.userName.getText().toString();
                String uEmail=binding.userEmail.getText().toString();
                prpic=userdata.getBoolean("prvalid",false);

                if(!prpic){
                    Toast.makeText(Signup.this,"Please set your Profile Image",Toast.LENGTH_SHORT).show();
                }
                else if((!uName.equals("")&&!uEmail.equals(""))&&(!selectedstd.equals("Select Your Standard"))){
                    if(valideEmailAddress()) {
                        Toast.makeText(Signup.this, "Your Data Processed Successfully", Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = userdata.edit();
//                        editor.putString("image_data",encodedImage);

                        editor.putString("ID", id);
                        editor.putBoolean("prvalid", true);
                        editor.putString("uName", uName);
                        editor.putString("uEmail", uEmail);
                        editor.putString("selectedStd", selectedstd);
                        editor.putBoolean("hasSignedUp",true);
                        editor.apply();
                        Intent it = new Intent(Signup.this, HomePage.class);
//                        setResult(Activity.RESULT_OK,it);
                        startActivity(it);
                        finish();
                    }
                    else{
                        Toast.makeText(Signup.this,"Invalid email address",Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(Signup.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                }

            }
        });

//

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uri=data.getData();
//        imgdata=uri.toString();

//        userdata=getSharedPreferences(Signup.PREFS_NAME,MODE_PRIVATE);
        //check - to check the user signup for the first time or existing user
        SharedPreferences.Editor editor=userdata.edit();
        if(resultCode==RESULT_OK){
            flag=1;
            encodedImage=uri.toString();
            editor.putString("image_data",encodedImage);
            editor.putBoolean("prvalid",true);
            editor.apply();
            binding.profpic.setImageURI(uri);
            Toast.makeText(this, "Image Saved", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Could not captured", Toast.LENGTH_SHORT).show();
        }
    }
        private boolean valideEmailAddress(){
            String email=binding.userEmail.getText().toString();
            if(!email.isEmpty()&& Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                return true;
            }
            else{
                return false;
            }
        }
        public static void hideSoftKeyboard(Activity activity, View view) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
        }
}