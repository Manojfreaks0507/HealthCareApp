package com.example.healthcare;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class PatientUpload extends AppCompatActivity {

    EditText fname,lname,phno,email,dname,symp,medicines,haddr,dphno;
    Button Submit;
    TextView admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        fname=(EditText)findViewById(R.id.txt_fname);
        lname=(EditText)findViewById(R.id.txt_lname);
        phno=(EditText)findViewById(R.id.txt_phno);
        email=(EditText)findViewById(R.id.txt_email);
        dname=(EditText)findViewById(R.id.txt_dname);
        symp=(EditText)findViewById(R.id.txt_symp);
        medicines=(EditText)findViewById(R.id.txt_med);
        haddr=(EditText)findViewById(R.id.txt_haddr);
        dphno=(EditText)findViewById(R.id.txt_dphno);
        admin=(TextView) findViewById(R.id.admin);

        Submit= (Button) findViewById(R.id.submitForm);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_send(v);
            }
        });
    }
    public void btn_send(View view){

        int permissioncheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if(permissioncheck== PackageManager.PERMISSION_GRANTED){

            MyMessage();
        }else{

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},0);
        }
    }

    private void MyMessage() {

        String firstname= fname.getText().toString().trim();
        String lastname= lname.getText().toString().trim();
        String phone= phno.getText().toString().trim();
        String useremail= email.getText().toString().trim();
        String doctorname= dname.getText().toString().trim();
        String symptoms= symp.getText().toString().trim();
        String med= medicines.getText().toString().trim();
        String hospaddr= haddr.getText().toString().trim();
        String doctorphno= dphno.getText().toString().trim();
        String adminno=admin.getText().toString().trim();
        try
        {
        String msg = new StringBuilder().append("FIRST NAME: ").append(firstname).append("\n").append("LAST NAME: ").append(lastname).append("\n").append("PHONE No.: ").append(phone).append("\n").append("EMAIL: ").append(useremail).append("\n").append("DOCTOR NAME: ").append(doctorname).append("\n").append("SYMPTOMS: ").append(symptoms).append("\n").append("MEDICINES: ").append(med).append("\n").append("HOSPITAL ADDRESS: ").append(hospaddr).append("\n").append("DOCTOR'S Ph.No.: ").append(doctorphno).toString();
        SmsManager smsManager= SmsManager.getDefault();

        ArrayList<String> parts = smsManager.divideMessage(msg);
        smsManager.sendMultipartTextMessage(adminno,null,parts,null,null);
        Toast.makeText(this,"patient record submitted",Toast.LENGTH_LONG).show();
        } catch (Exception e) {
        Toast.makeText(getApplicationContext(), "SMS Failed !", Toast.LENGTH_LONG).show();
        e.printStackTrace();
    }
    }
}
