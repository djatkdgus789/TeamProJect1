package com.apress.gerber.teamproject1;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.View;
import android.app.Activity;
import android.content.Intent;

import android.widget.Button;
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
import com.wonderkiln.camerakit.CameraKitError;
import com.wonderkiln.camerakit.CameraKitEvent;
import com.wonderkiln.camerakit.CameraKitEventListener;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraKitVideo;
import com.wonderkiln.camerakit.CameraView;

import java.util.List;

import dmax.dialog.SpotsDialog;


public class AlbumActivity extends Activity implements View.OnClickListener{

    static int REQUEST_PHOTO_ALBUM=2;
    static String SAMPLEIMG="ic_launcher.png";

    CameraView cameraView;
    GraphicOverlay graphicOverlay;
    Button btnDetect;
    AlertDialog waitingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);

        cameraView = (CameraView)findViewById(R.id.camera_view);
        graphicOverlay = (GraphicOverlay)findViewById(R.id.graphic_overlay);
        btnDetect = (Button)findViewById(R.id.btn_detect);
        waitingDialog = new SpotsDialog.Builder().setContext(this)
                .setMessage("Please wait")
                .setCancelable(false)
                .build();

        btnDetect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,REQUEST_PHOTO_ALBUM);
            }
        });

        cameraView.addCameraKitListener(new CameraKitEventListener() {
            @Override
            public void onEvent(CameraKitEvent cameraKitEvent) {

            }

            @Override
            public void onError(CameraKitError cameraKitError) {

            }

            @Override
            public void onImage(CameraKitImage cameraKitImage) {
                waitingDialog.show();

                Bitmap bitmap = cameraKitImage.getBitmap();
                bitmap = bitmap.createScaledBitmap(bitmap,cameraView.getWidth(),cameraView.getHeight(), false);
                cameraView.stop();

                runFaceDetector(bitmap);
            }


            @Override
            public void onVideo(CameraKitVideo cameraKitVideo){

            }
        });
    }

    @Override

    public void onClick(View v){
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,REQUEST_PHOTO_ALBUM);
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

/*    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            //iv.setImageURI(data.getData());

        }

    }
*/
}