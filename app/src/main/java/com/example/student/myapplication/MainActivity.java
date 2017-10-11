package com.example.student.myapplication;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {
    final int REQUEST_EXTERNAL_STORAGE = 321;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickWrite(View v) {
        String fname = getFilesDir().getAbsolutePath();
        Log.d("FN", fname);
        String fname1 = getCacheDir().getAbsolutePath();
        Log.d("FN", fname1);


        try {
            FileWriter fw = new FileWriter(fname + File.separator + "data1.txt");
            fw.write("Hello World");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void clickRead(View v) {
        String fname = getFilesDir().getAbsolutePath();
        char ch[] = new char[100];
        try {
            FileReader fr = new FileReader(fname + File.separator + "data1.txt");
            fr.read(ch);
            String str = new String(ch);
            Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickWriter2(View v) {
        String path = getFilesDir().getAbsolutePath();
        File fname = new File(path + File.separator + "data2.txt");
        try {
            FileWriter fw = new FileWriter(fname);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Hello,world");
            bw.newLine();
            bw.write("This is android");
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickReader2(View v) {
        String path = getFilesDir().getAbsolutePath();
        File fname = new File(path + File.separator + "data2.txt");
        try {
            FileReader fr = new FileReader(fname);
            BufferedReader br = new BufferedReader(fr);
            String str;
            while ((str = br.readLine()) != null) {
                Log.d("FNAME", "Read:" + str);
            }
            br.close();
            fr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickWriter3(View v) {
        int[] data = {4, 1, 7, 3, 4, 8, 2, 1};
        StringBuilder sb = new StringBuilder();
        for (int i : data)
        {
            sb.append(i + ",");
        }
        String path = getFilesDir().getAbsolutePath();
        File fname = new File(path + File.separator + "data3.txt");
        try {
            FileWriter fw = new FileWriter(fname);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(sb.toString());
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickReader3(View v) {
        String path = getFilesDir().getAbsolutePath();
        File fname = new File(path + File.separator + "data3.txt");
        ArrayList<Integer> mylist = new ArrayList();
        try {
            FileReader fr = new FileReader(fname);
            BufferedReader br = new BufferedReader(fr);
            String str;
            str = br.readLine();
            String strdata[] = str.split(",");
            for (String s : strdata) {
                if (s.length() > 0) {
                    mylist.add(Integer.valueOf(s));
                }
            }
            br.close();
            fr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i : mylist) {
            Log.d("FNAME", "data:" + i);
        }

    }

    public void clickWriter4(View v ){
        String path = getFilesDir().getAbsolutePath();
        File fname = new File(path + File.separator + "data3.txt");
        try {
            FileWriter fw = new FileWriter(fname);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Hello,world");
            bw.newLine();
            bw.write("This is android");
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void clickReader4(View v){

    }

    public void clickWriter5(View v)
    {
        int permission = ActivityCompat.checkSelfPermission(this,
                WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            //未取得權限，向使用者要求允許權
            ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE},
                    REQUEST_EXTERNAL_STORAGE);
        }else{
            //已有權限，可進行檔案存取
            writeFile();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_EXTERNAL_STORAGE)
        {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //取得權限，進行檔案存取
                writeFile();
            } else {
                //使用者拒絕權限，停用檔案存取功能
            }
        }
    }

    private void writeFile()
    {
        File f = Environment.getExternalStorageDirectory();
        Log.d("FNAME", f.getAbsolutePath());
        File f2 = new File(f.getAbsolutePath() + File.separator + "mydata");
        f2.mkdir();
        File txtFile = new File(f2.getAbsolutePath() + File.separator + "data5.txt");
        try {
            FileWriter fw = new FileWriter(txtFile);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Hello World");
            bw.newLine();
            bw.write("This is android");
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void clickReader5(View v)
    {
        File f = Environment.getExternalStorageDirectory();
        Log.d("FNAME", f.getAbsolutePath());
        File f2 = new File(f.getAbsolutePath() + File.separator + "mydata");
        File txtFile = new File(f2.getAbsolutePath() + File.separator + "data5.txt");
        try {
            FileReader fr = new FileReader(txtFile);
            BufferedReader br = new BufferedReader(fr);
            String str;
            while ((str=br.readLine()) != null)
            {
                Log.d("FNAME", "Read:" + str);
            }
            br.close();
            fr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
