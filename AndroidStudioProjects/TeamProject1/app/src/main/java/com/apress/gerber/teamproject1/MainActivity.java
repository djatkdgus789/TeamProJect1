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
import android.widget.Toast;


public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
                finish();
                return true;
            default:
                return true;
        }
    }

    public void CameraClick(View v) {
        Intent iCamera =  new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
        Toast.makeText(getApplicationContext(), "사진을 찍어주세요.", Toast.LENGTH_LONG).show();
        startActivityForResult(iCamera, 0);
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


}
