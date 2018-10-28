package com.apress.gerber.teamproject1;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.view.textclassifier.TextLinks;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity{

    public static final int REQUEST_CODE_MENU = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
//        if (permissionCheck == PackageManager.PERMISSION_GRANTED){
//            Toast.makeText(this, "카메라 권한 있음.", Toast.LENGTH_LONG).show();
//        }else {
//            Toast.makeText(this, "카메라 권한 없음", Toast.LENGTH_LONG).show();
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)){
//                Toast.makeText(this, "카메라 권한 설명 필요함.", Toast.LENGTH_LONG).show();
//            }else {
//                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 1);
//            }
//        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings:
                return true;
            case R.id.exit:
//                View view = (View) findViewById(R.id.exit);
//                ExitClick(view);
                finish();
                return true;
            default:
                return true;
        }
    }

    public void CameraClick(View v) {
//        Intent iCamera =  new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
//        Toast.makeText(getApplicationContext(), "사진을 찍어주세요.", Toast.LENGTH_LONG).show();
//        startActivityForResult(iCamera, 0);
        Intent intent = new Intent(getApplicationContext(), CameraActivity.class) ;
        startActivityForResult(intent, REQUEST_CODE_MENU);
    }

    public void FolderClick(View v){
        Intent iFolder = new Intent(Intent.ACTION_PICK);
        iFolder.setType(MediaStore.Images.Media.CONTENT_TYPE);
        Toast.makeText(getApplicationContext(), "사진을 선택해 주세요", Toast.LENGTH_LONG).show();
        startActivityForResult(iFolder, 0);
    }

    public void Button1Click(View v){
        Intent iButton1  = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
        startActivity(iButton1);
    }

    public void Button2Click(View v){
        Intent iButton2  = new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com"));
        startActivity(iButton2);
    }
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> parent of 3677278... Android

//    public void ExitClick(View v){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("안내");
//        builder.setMessage("어딜 도망가");
//        builder.setIcon(android.R.drawable.ic_dialog_alert);
//        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String message = "예 버튼이 눌렸습니다.";
//                textView.setText(message);
//            }
//        });
//
//        builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String message = "취소 버튼이 눌렸습니다.";
//                textView.setText(message);
//            }
//        });
//
//        builder.setNeutralButton("아니오", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String message = "아니오 버튼이 눌렸습니다.";
//                textView.setText(message);
//            }
//        });
//
//        AlertDialog dialog = builder.create();
//        dialog.show();
//
//    }


//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode){
//            case 1:{
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    Toast.makeText(this, "카메라 권한을 사용자가 승인함.", Toast.LENGTH_LONG).show();
//                }else {
//                    Toast.makeText(this, "카메라 권한 거부됨.", Toast.LENGTH_LONG).show();
//                }
//                return;
//            }
//        }
//    }
<<<<<<< HEAD
=======
>>>>>>> parent of 0ba5739... ??
=======
>>>>>>> parent of 3677278... Android
}
