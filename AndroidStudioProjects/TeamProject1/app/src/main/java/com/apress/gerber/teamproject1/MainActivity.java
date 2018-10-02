package com.apress.gerber.teamproject1;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button mButton_Camera;
    Button mButton_Folder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mButton_Camera = (Button)findViewById(R.id.Camera);
        mButton_Camera.setOnClickListener(this);

        mButton_Folder = (Button)findViewById(R.id.Folder);
        mButton_Folder.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Camera:
                Intent iCamera =  new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
                startActivityForResult(iCamera, 0);
                break;
            case R.id.Folder:
                Intent iFolder = new Intent(Intent.ACTION_PICK);
                iFolder.setType(MediaStore.Images.Media.CONTENT_TYPE);
                startActivityForResult(iFolder, 0);
                break;
        }

    }
}
