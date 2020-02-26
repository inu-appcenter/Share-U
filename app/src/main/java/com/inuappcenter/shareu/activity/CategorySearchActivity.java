package com.inuappcenter.shareu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.inuappcenter.shareu.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CategorySearchActivity extends AppCompatActivity {
    private TextView tv_my_major;
    private ImageView btn_backpress;
    private String name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_search);

    }

    void init()
    {
        tv_my_major=findViewById(R.id.tv_my_major);
        btn_backpress=findViewById(R.id.btn_backpress);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
