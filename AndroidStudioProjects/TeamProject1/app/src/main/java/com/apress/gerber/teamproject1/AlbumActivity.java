package com.apress.gerber.teamproject1;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.app.Activity;
import android.content.Intent;

import android.widget.ImageView;


public class AlbumActivity extends Activity implements View.OnClickListener{

    static int REQUEST_PHOTO_ALBUM=2;
    static String SAMPLEIMG="ic_launcher.png";
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        iv=(ImageView) findViewById(R.id.imgView);
        findViewById(R.id.getCustom).setOnClickListener(this);

    }

    @Override

    public void onClick(View v){
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,REQUEST_PHOTO_ALBUM);
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            iv.setImageURI(data.getData());
        }
    }

}