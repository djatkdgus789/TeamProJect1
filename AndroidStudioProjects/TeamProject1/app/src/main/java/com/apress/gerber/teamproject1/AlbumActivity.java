package com.apress.gerber.teamproject1;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.View;
import android.app.Activity;
import android.content.Intent;

import android.widget.ImageView;
import android.widget.Toast;

import com.apress.gerber.teamproject1.Helper.GraphicOverlay;
import com.apress.gerber.teamproject1.Helper.RectOverlay;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.face.FirebaseVisionFace;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetector;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;

import java.io.IOException;
import java.util.List;

import dmax.dialog.SpotsDialog;


public class AlbumActivity extends Activity implements View.OnClickListener{

    static int REQUEST_PHOTO_ALBUM=2;
    static String SAMPLEIMG="ic_launcher.png";
    ImageView iv;
    AlertDialog waitingDialog;
    GraphicOverlay graphicOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);


        iv=(ImageView) findViewById(R.id.imgView);
        graphicOverlay = (GraphicOverlay)findViewById(R.id.graphic_overlay);
        waitingDialog = new SpotsDialog.Builder().setContext(this)
                .setMessage("Please wait")
                .setCancelable(false)
                .build();
        findViewById(R.id.btn_detect).setOnClickListener(this);
    }

    @Override

    public void onClick(View v){
        graphicOverlay.clear();
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,REQUEST_PHOTO_ALBUM);
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode == REQUEST_PHOTO_ALBUM && data != null && data.getData() != null){
            waitingDialog.show();

            Uri filePath = data.getData();

            try {
                waitingDialog.show();

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                iv.setImageBitmap(bitmap);
                bitmap = bitmap.createScaledBitmap(bitmap, iv.getWidth(),iv.getHeight(), false);
                runFaceDetector(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void runFaceDetector(Bitmap bitmap) {
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);

        FirebaseVisionFaceDetectorOptions options = new FirebaseVisionFaceDetectorOptions.Builder()
                .build();

        FirebaseVisionFaceDetector detector = FirebaseVision.getInstance()
                .getVisionFaceDetector(options);

        detector.detectInImage(image)
                .addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionFace>>() {
                    @Override
                    public void onSuccess(List<FirebaseVisionFace> firebaseVisionFaces){
                        processFaceResult(firebaseVisionFaces);
                    }

                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AlbumActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void processFaceResult(List<FirebaseVisionFace> firebaseVisionFaces) {
        int count = 0;
        for(FirebaseVisionFace face : firebaseVisionFaces){
            Rect bounds = face.getBoundingBox();
            RectOverlay rect = new RectOverlay(graphicOverlay,bounds);
            graphicOverlay.add(rect);

            count++;
        }
        waitingDialog.dismiss();
        Toast.makeText(this, String.format("Detected %d faces in image,count",count), Toast.LENGTH_SHORT).show();


    }

}