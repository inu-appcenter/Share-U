package com.inuappcenter.shareu.service;

import android.app.Application;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Navigator.setContext(getApplicationContext());
    }
}
