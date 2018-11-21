package com.Reference.teamproject1;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;


public class ResultActivity extends Activity {
    String csvUrl = "http://taeyeon.gonetis.com:8080/output/out.csv";
    BufferedReader br = null;
    TextView textView;
    static String predict="잠시만 기다려 주세요";
    static String value="잠시만 기다려 주세요";
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("facetype");;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(predict + "\n" + value);



    }

    @Override
    protected void onStart() {
        super.onStart();
        mDatabase.child("facetype").addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                predict = dataSnapshot.getValue(String.class);
                mDatabase.child("percent").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        value = dataSnapshot.getValue(String.class);
                        textView.setText(predict +"\n" + value);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        predict="잠시만 기다려 주세요";
        value="잠시만 기다려 주세요";
        super.onBackPressed();

    }
}

