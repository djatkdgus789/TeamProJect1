package com.Reference.teamproject1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity{

    static final int CAMERA_ACTIVITY_CODE=1;
    static final int ALBUM_ACTIVITY_CODE=2;
    static final int NAVER_ACTIVITY_CODE=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
       Intent iCamera = new Intent(this, CameraActivity.class);
       startActivityForResult(iCamera, CAMERA_ACTIVITY_CODE);
    }

    public void FolderClick(View v){
        Intent iFolder = new Intent(this, AlbumActivity.class);
        startActivityForResult(iFolder, ALBUM_ACTIVITY_CODE);
    }

    public void Button1Click(View v){
        Intent iButton1  = new Intent(this, Naver.class);
        startActivityForResult(iButton1, NAVER_ACTIVITY_CODE);
    }

    public void Button2Click(View v){
        Intent iButton2  = new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com"));
        startActivity(iButton2);
    }
}


