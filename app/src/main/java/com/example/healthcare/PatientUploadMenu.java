package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class PatientUploadMenu extends AppCompatActivity {
    ImageView compose;
    TextView composetext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientuploadmenu);
        compose= (ImageView) findViewById(R.id.compose);
        compose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientUploadMenu.this, PatientUpload.class);
                startActivity(intent);
            }
        });
        composetext= (TextView) findViewById(R.id.composetext);
        composetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientUploadMenu.this, PatientUpload.class);
                startActivity(intent);
            }
        });
    }
}
