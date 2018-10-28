package com.apress.gerber.teamproject1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.ImageView;

import java.io.File;


public class CameraActivity extends Activity implements View.OnClickListener{
    static String SAMPLEIMG=".test.jpg";
    static File file = new File(Environment.getExternalStorageDirectory(), SAMPLEIMG);
    static final int REQUEST_PICTURE=1;

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
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(this, "com.apress.gerber.teamproject1.fileprovider", file));
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, REQUEST_PICTURE);
        }
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        iv.setImageBitmap(bitmap);
    }
}







