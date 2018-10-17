package com.cit.bs;

public class BS
{
    private static boolean isLoaded;

    static
    {
        if (!isLoaded)
        {
            System.loadLibrary("BS");
            isLoaded = true;
        }
    }

    public final static native boolean patch(String oldStr, String newStr, String bsStr);

    public final static native boolean diff(String oldStr, String newStr, String bsStr);
}
