package com.cit.bs.app;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

public class Utils
{
    /**
     * 安装
     *
     * @param context
     * @param apkFilename
     */
    public static void installApk(Context context, String apkFilename)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.parse("file://" + apkFilename), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * 获取当前应用的安装包
     *
     * @param context
     * @return
     */
    public static String getApkFilename(Context context)
    {
        String filename = null;
        try
        {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
            filename = ai.sourceDir;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return filename;
    }

    /**
     * 获取文件的MD5值
     *
     * @param filename
     * @return
     */
    public static String md5(String filename)
    {
        String value = null;
        FileInputStream in = null;
        try
        {
            File file = new File(filename);
            in = new FileInputStream(file);
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            String str = bi.toString(16);
            int n;
            if ((n = 32 - str.length()) > 0)
            {
                for (int i = 0; i < n; i++)
                {
                    str = "0" + str;
                }
            }
            value = str;
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if (null != in)
            {
                try
                {
                    in.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }
}
