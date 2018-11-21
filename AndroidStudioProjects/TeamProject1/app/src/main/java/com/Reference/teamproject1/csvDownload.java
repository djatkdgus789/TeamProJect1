package com.Reference.teamproject1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class csvDownload {        //이미지 다운로드
    public void load() {
        try {
            InputStream inputStream = new URL("http://taeyeon.gonetis.com:8080/output/out.csv").openStream();
            File file = new File("out.csv");
            OutputStream out;
            out = new FileOutputStream(file);
            writeFile(inputStream, out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeFile(InputStream is, OutputStream os) throws IOException
    {
        int c = 0;
        while((c = is.read()) != -1)
            os.write(c);
        os.flush();
    }
}
