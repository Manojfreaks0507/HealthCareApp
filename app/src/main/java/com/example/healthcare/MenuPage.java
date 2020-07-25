package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class MenuPage extends AppCompatActivity {
    ImageView uploaddata;
    ImageView admindata;
    ImageView doctoraccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        uploaddata= (ImageView) findViewById(R.id.up);
        admindata= (ImageView) findViewById(R.id.ad);
        doctoraccess=findViewById(R.id.imageView3);

        uploaddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPage.this, PatientUploadMenu.class);
                startActivity(intent);
            }
    });
        admindata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPage.this, MsgList.class);
                startActivity(intent);
            }
        });

        doctoraccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPage.this, doctor_access.class);
                startActivity(intent);

            }
        });

    }
}
