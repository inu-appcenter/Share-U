package com.inuappcenter.shareu.service;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.inuappcenter.shareu.activity.MainActivity;

public class Navigator{
    private static Context context;

    public static void setContext(Context context)
    {
        if(context == null)
        {
            throw new RuntimeException("WTF");
        }
        Navigator.context=context;
    }
    public static void launchActicity(Class<?> clazz)
    {
        if(Navigator.context == null)
        {
            throw new RuntimeException("WTF ? setContext first!");
        }
        Intent intent = new Intent(Navigator.context,clazz);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Navigator.context.startActivity(intent);
    }

}
