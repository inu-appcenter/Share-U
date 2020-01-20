package com.inuappcenter.shareu.activity;

import android.app.Activity;
import android.content.Intent;
import android.drm.DrmStore;
import android.os.Bundle;
import android.os.Handler;

import com.inuappcenter.shareu.R;

import androidx.annotation.Nullable;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startLoading();
    }
    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent_splash = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(intent_splash);
                SplashActivity.this.finish();
            }
        }, 1500);
    }
}
