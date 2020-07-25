package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class PatientRecord extends AppCompatActivity {

    ListView lv;
    ArrayList<String> arr=new ArrayList<>();
    ArrayAdapter<String> adapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientrecord);

        lv=findViewById(R.id.details);

        String getnum= getIntent().getStringExtra("number");

        myRef.child("patientdata").child(getnum).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                  arr.clear();

                for(DataSnapshot data:dataSnapshot.getChildren())
                {
                    String temp=data.getValue().toString();
                    temp=temp.replaceAll("_n","\n");
                    Log.e("control",data.getKey()+"\n"+temp);
                    arr.add(data.getKey()+"\n"+temp);
                }
                Collections.sort(arr,Collections.<String>reverseOrder());
                Log.e("order:",arr.get(0));
                adapter = new ArrayAdapter<String>(PatientRecord.this, android.R.layout.simple_list_item_1, arr);
                lv.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });


    }
}