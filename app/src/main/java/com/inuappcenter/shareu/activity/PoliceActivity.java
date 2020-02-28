package com.inuappcenter.shareu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

import com.inuappcenter.shareu.R;

import androidx.annotation.Nullable;

public class PoliceActivity extends Activity{
    ImageView btn_backpress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_police);
        btn_backpress = findViewById(R.id.btn_backpress);
        btn_backpress.setOnClickListener(v->finish());
    }
}
