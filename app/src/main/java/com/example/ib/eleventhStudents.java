package com.example.ib;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class eleventhStudents extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleventh_students);
        getSupportActionBar().hide();

        TextView Science = findViewById(R.id.tvScience);
        TextView Mathematics = findViewById(R.id.tvMath);
        TextView Studies_In_language_and_literature = findViewById(R.id.tvLang);
        TextView Individuals_and_societies = findViewById(R.id.tvIndividuals);
        TextView Language_Acquisition = findViewById(R.id.tvAcqusition);
        TextView Arts = findViewById(R.id.tvArts);
        TextView PHE = findViewById(R.id.tvPhe);
        TextView Design = findViewById(R.id.tvDesign);
        TextView Project = findViewById(R.id.tvProjects);

        Science.setOnClickListener(this);
        Mathematics.setOnClickListener(this);
        Studies_In_language_and_literature.setOnClickListener(this);
        Individuals_and_societies.setOnClickListener(this);
        Language_Acquisition.setOnClickListener(this);
        Arts.setOnClickListener(this);
        PHE.setOnClickListener(this);
        Design.setOnClickListener(this);
        Project.setOnClickListener(this);


    }


    /// handling all click lister for the subjects
    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.tvScience:
                    Intent intent=new Intent(this,SlotActivity.class);
                    intent.putExtra("Subject", "Science");
                    startActivity(intent);
                      break;
                case R.id.tvMath:
                    intent=new Intent(this,SlotActivity.class);
                    intent.putExtra("Subject", "Math");
                    startActivity(intent);
                    break;
                case R.id.tvLang:
                    intent=new Intent(this,SlotActivity.class);
                    intent.putExtra("Subject", "Language and Literature");
                    startActivity(intent);
                    break;
                case R.id.tvIndividuals:
                    intent=new Intent(this,SlotActivity.class);
                    intent.putExtra("Subject", "Individuals and Societies");
                    startActivity(intent);
                    break;
                case R.id.tvAcqusition:
                    intent=new Intent(this,SlotActivity.class);
                    intent.putExtra("Subject", "Language Acquisition");
                    startActivity(intent);
                    break;
                case R.id.tvArts:
                    intent=new Intent(this,SlotActivity.class);
                    intent.putExtra("Subject", "The Arts");
                    startActivity(intent);
                    break;
                case R.id.tvPhe:
                    intent=new Intent(this,SlotActivity.class);
                    intent.putExtra("Subject", "PHE");
                    startActivity(intent);
                    break;
                case R.id.tvDesign:
                    intent=new Intent(this,SlotActivity.class);
                    intent.putExtra("Subject", "Design");
                    startActivity(intent);
                    break;
                case R.id.tvProjects:
                    intent=new Intent(this,SlotActivity.class);
                    intent.putExtra("Subject", "Projects");
                    startActivity(intent);
                    break;
            }
    }
}