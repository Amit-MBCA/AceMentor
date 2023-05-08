package com.example.ib;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class eleventhStudents extends AppCompatActivity implements View.OnClickListener{
    private ImageView backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleventh_students);
        getSupportActionBar().hide();
        backbtn=findViewById(R.id.imageView);
        TextView Science = findViewById(R.id.tvScience);
        TextView Mathematics = findViewById(R.id.tvMath);
        TextView Studies_In_language_and_literature = findViewById(R.id.tvLang);
        TextView Individuals_and_societies = findViewById(R.id.tvIndividuals);
        TextView Language_Acquisition = findViewById(R.id.tvAcqusition);
        TextView Arts = findViewById(R.id.tvArts);
//        TextView PHE = findViewById(R.id.tvPhe);
//        TextView Design = findViewById(R.id.tvDesign);
//        TextView Project = findViewById(R.id.tvProjects);

        Science.setOnClickListener(this);
        Mathematics.setOnClickListener(this);
        Studies_In_language_and_literature.setOnClickListener(this);
        Individuals_and_societies.setOnClickListener(this);
        Language_Acquisition.setOnClickListener(this);
        Arts.setOnClickListener(this);
//        PHE.setOnClickListener(this);
//        Design.setOnClickListener(this);
//        Project.setOnClickListener(this);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(eleventhStudents.this,HomePage.class);
                startActivity(it);
                finish();
            }
        });


    }


    /// handling all click lister for the subjects
    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.tvScience:
                    Intent gtsc=new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.ibo.org/programmes/diploma-programme/curriculum/sciences/"));
                    startActivity(gtsc);
                      break;
                case R.id.tvMath:
                    Intent gtsm=new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.ibo.org/programmes/diploma-programme/curriculum/mathematics/"));
                    startActivity(gtsm);
                    break;
                case R.id.tvLang:
//                    intent=new Intent(this,SlotActivity.class);
//                    intent.putExtra("Subject", "Language and Literature");
//                    startActivity(intent);
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ibo.org/programmes/diploma-programme/curriculum/language-and-literature/"));
                    startActivity(browserIntent);
                    break;
                case R.id.tvIndividuals:
//                    intent=new Intent(this,SlotActivity.class);
//                    intent.putExtra("Subject", "Individuals and Societies");
//                    startActivity(intent);
                    Intent indi=new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.ibo.org/programmes/diploma-programme/curriculum/language-acquisition/"));
                    startActivity(indi);
                    break;
                case R.id.tvAcqusition:
                    Intent bt = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ibo.org/programmes/diploma-programme/curriculum/language-acquisition/"));
                    startActivity(bt);
                    break;
                case R.id.tvArts:
//                    intent=new Intent(this,SlotActivity.class);
//                    intent.putExtra("Subject", "The Arts");
//                    startActivity(intent);
                    Intent tvarts = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ibo.org/programmes/diploma-programme/curriculum/the-arts/"));
                    startActivity(tvarts);
                    break;
//                case R.id.tvPhe:
//                    Intent gtphe = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ibo.org/programmes/middle-years-programme/curriculum/physical-and-health-education/"));
//                    startActivity(gtphe);
//                    break;
//                case R.id.tvDesign:
//                    Intent gtdesign = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ibo.org/programmes/middle-years-programme/curriculum/design/"));
//                    startActivity(gtdesign);
//                    break;

            }
    }
}