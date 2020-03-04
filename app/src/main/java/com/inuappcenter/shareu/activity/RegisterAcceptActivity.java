package com.inuappcenter.shareu.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.inuappcenter.shareu.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterAcceptActivity extends AppCompatActivity {

    private ImageView btn_back_home;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_accept);
        btn_back_home=findViewById(R.id.btn_back_home);
        btn_back_home.setOnClickListener(v->finish());
    }
}
