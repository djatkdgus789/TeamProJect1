package com.Reference.teamproject1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private Button button;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("facetype");;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = (TextView) findViewById(R.id.textView);

        setContentView(R.layout.result);


    }

    public void openRecommandListActivity(){
        Intent intent = new Intent(this, RecommandListActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDatabase.child("facetype").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                predict = dataSnapshot.getValue(String.class);
                value = dataSnapshot.getValue(String.class);
                if(predict.equals("heart")){
                    setContentView(R.layout.heart_result);
                }else if(predict.equals("oblong")){
                    setContentView(R.layout.oblong_result);
                }else if(predict.equals("oval")){
                    setContentView(R.layout.oval_result);
                }else if(predict.equals("round")){
                    setContentView(R.layout.round_result);
                }else if(predict.equals("square")){
                    setContentView(R.layout.square_result);
                }

                button = (Button)findViewById(R.id.button3);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openRecommandListActivity();
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

