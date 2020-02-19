package com.inuappcenter.shareu.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.inuappcenter.shareu.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UploadedActivity extends AppCompatActivity {

    private ImageButton btn_back_home;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploaded);
        btn_back_home = findViewById(R.id.btn_back_home);
        btn_back_home.setOnClickListener(v->finish());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
