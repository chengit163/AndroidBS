package com.cit.bs.app;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cit.bs.BS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity
{
    private final static String TAG = "BS";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }


    public void reinstall(View v)
    {
        String apkFilename = Utils.getApkFilename(this);
        Utils.installApk(this, apkFilename);
    }

    public void random(View v)
    {
        String apkFilename = Utils.getApkFilename(this);
        Log.d(TAG, "当前应用安装包 md5: " + Utils.md5(apkFilename));
        File oldApk = new File(getExternalCacheDir(), "old.apk");
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try
        {
            fis = new FileInputStream(new File(apkFilename));
            fos = new FileOutputStream(oldApk);
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = fis.read(buffer, 0, buffer.length)) > 0)
            {
                // 随机改变
                int index = (int) (Math.random() * 1024);
                byte value = (byte) (Math.random() * 128);
                buffer[index] = value;
                fos.write(buffer, 0, len);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if (fos != null)
            {
                try
                {
                    fos.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if (fis != null)
            {
                try
                {
                    fis.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        Log.d(TAG, "旧安装包 md5: " + Utils.md5(oldApk.getAbsolutePath()));
    }

    public void bsdiff(View v)
    {
        File oldApk = new File(getExternalCacheDir(), "old.apk");//旧安装包
        String apkFilename = Utils.getApkFilename(this);//当前应用安装包，当作新安装包
        File patch = new File(getExternalCacheDir(), "patch.data");//差分包
        //
        long start = System.currentTimeMillis();
        boolean flag = BS.diff(oldApk.getAbsolutePath(), apkFilename, patch.getAbsolutePath());
        long end = System.currentTimeMillis();
        Log.d(TAG, "bsdiff: " + flag + "; 耗时(ms): " + (end - start));
        Log.d(TAG, "当前应用安装包大小: " + new File(apkFilename).length());
        Log.d(TAG, "差分包大小: " + patch.length());
    }

    public void bspatch(View v)
    {
        String apkFilename = Utils.getApkFilename(this);//当前应用安装包，当作新安装包
        File oldApk = new File(getExternalCacheDir(), "old.apk");//旧安装包
        File newApk = new File(getExternalCacheDir(), "new.apk");//新安装包
        File patch = new File(getExternalCacheDir(), "patch.data");//差分包
        //
        long start = System.currentTimeMillis();
        boolean flag = BS.patch(oldApk.getAbsolutePath(), newApk.getAbsolutePath(), patch.getAbsolutePath());
        long end = System.currentTimeMillis();
        Log.d(TAG, "bspatch: " + flag + "; 耗时(ms): " + (end - start));
        Log.d(TAG, "当前应用安装包 md5: " + Utils.md5(apkFilename));
        Log.d(TAG, "新安装包 md5: " + Utils.md5(newApk.getAbsolutePath()));
    }

    public void install(View v)
    {
        File newApk = new File(getExternalCacheDir(), "new.apk");//新安装包
        Utils.installApk(this, newApk.getAbsolutePath());
    }
}
