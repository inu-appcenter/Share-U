package com.inuappcenter.shareu.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.inuappcenter.shareu.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ServerFailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_fail);
        ImageView btn_backpress =findViewById(R.id.btn_backpress);
        btn_backpress.setOnClickListener(v->finish());
    }
}
