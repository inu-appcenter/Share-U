package com.inuappcenter.shareu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.inuappcenter.shareu.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

public class CategorySearchActivity extends AppCompatActivity {
    private TextView tv_my_major;
    private ImageView btn_backpress;
    FloatingActionButton fab_main;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_search);
        init();

    }

    void init()
    {
        tv_my_major=findViewById(R.id.tv_my_major);
        btn_backpress=findViewById(R.id.btn_backpress);
        fab_main=findViewById(R.id.fab_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_backpress :
                        finish();
                       break;
                    case R.id.fab_main:
                        Intent intent = new Intent(getApplicationContext(), FileUploadActivity.class);
                        startActivity(intent);
                        break ;

                }

            }
        } ;
        btn_backpress.setOnClickListener(onClickListener);
        fab_main.setOnClickListener(onClickListener);
    }
}
