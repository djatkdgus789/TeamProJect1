package com.Reference.teamproject1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ResultActivity extends Activity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        textView = (TextView)findViewById(R.id.textView);
        loadFile("http://taeyeon.gonetis.com:8080/output", "out.csv");

    }



    public void loadFile(String Url, String FileName){
        URL imageDownUrl;
        int Read;
        try {
            imageDownUrl = new URL(Url);
            HttpURLConnection conn= (HttpURLConnection)imageDownUrl.openConnection();
            conn.connect();
            int len = conn.getContentLength();
            byte[] raster = new byte[len];
            InputStream is = conn.getInputStream();
            FileOutputStream fos = openFileOutput(FileName, MODE_WORLD_READABLE);
            for (;;)
            {
                Read = is.read(raster);
                if (Read <= 0)
                {
                    break;
                }
                fos.write(raster,0, Read);
            }
            is.close();
            fos.close();
        } catch (Exception e) {
            System.out.println("error");
            return;
        }

    }

}
