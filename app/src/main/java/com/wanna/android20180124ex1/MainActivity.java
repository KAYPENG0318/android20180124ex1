package com.wanna.android20180124ex1;

import android.Manifest;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void click1(View v)
    {
        File f = Environment.getExternalStorageDirectory();

        File nf = new File(f, "DCIM" + File.separator + "myfile.txt");
        Log.d("FILE", nf.getAbsolutePath());
        if (nf.exists())
        {
            Log.d("FILE", "nf 存在");
        }
        else
        {
            Log.d("FILE", "nf 不存在");
        }

        int permission = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(
                    this,
                    new String[] {WRITE_EXTERNAL_STORAGE,
                            READ_EXTERNAL_STORAGE},
                    123
            );
        }
        else
        {
            readFile();
        }
    }
    private void readFile()
    {
        File f = Environment.getExternalStorageDirectory();

        File nf = new File(f, "DCIM" + File.separator + "myfile.txt");
        try {
            FileReader fr = new FileReader(nf);
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();
            Log.d("FILE", "Read:" + str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 123)
        {
            if (grantResults.length > 0
                    && grantResults[0] == PERMISSION_GRANTED) {
                //取得權限，進行檔案存取
                readFile();
            } else {
                //使用者拒絕權限，停用檔案存取功能
            }
            return;
        }
    }
}
