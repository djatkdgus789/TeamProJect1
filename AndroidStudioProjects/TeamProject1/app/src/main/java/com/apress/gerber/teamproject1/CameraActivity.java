package com.apress.gerber.teamproject1;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
=======
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
>>>>>>> parent of 3677278... Android
=======
import java.io.File;
import android.net.Uri;
>>>>>>> parent of 0ba5739... ??
=======
import java.io.File;
import android.net.Uri;
>>>>>>> parent of 0ba5739... ??
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
<<<<<<< HEAD
<<<<<<< HEAD
import android.widget.ImageView;
import android.widget.Toast;


import java.io.File;
<<<<<<< HEAD
=======
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.widget.ImageView;
>>>>>>> parent of 0ba5739... ??
=======
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.widget.ImageView;
>>>>>>> parent of 0ba5739... ??

=======
import java.io.IOException;
>>>>>>> parent of 3677278... Android

/**
 * 인텐트를 이용해 단말의 카메라 앱을 실행시켜 사진을 찍는 방법을 알 수 있습니다.
 *
 */
public class CameraActivity extends AppCompatActivity {
    public static final int REQUEST_IMAGE_CAPTURE = 1001;

    File file = null;
    ImageView imageView;

    @Override
<<<<<<< HEAD
    protected void onCreate(Bundle savedInstanceState){
<<<<<<< HEAD
<<<<<<< HEAD



=======
    protected void onCreate(Bundle savedInstanceState) {
>>>>>>> parent of 3677278... Android
=======
>>>>>>> parent of 0ba5739... ??
=======
>>>>>>> parent of 0ba5739... ??
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        imageView = (ImageView) findViewById(R.id.imageView);

        try {
            file = createFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void onButton1Clicked(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private File createFile() throws IOException {
        String imageFileName = "test.jpg";
        File storageDir = Environment.getExternalStorageDirectory();
        File curFile = new File(storageDir, imageFileName);

        return curFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            if (file != null) {
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);

                imageView.setImageBitmap(bitmap);
            } else {
                Toast.makeText(getApplicationContext(), "File is null.", Toast.LENGTH_LONG).show();
            }
        }
    }

}
