package com.inuappcenter.shareu.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.inuappcenter.shareu.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ModifySuccessActivity extends AppCompatActivity {

    ImageView btn_backpress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_success);
        init();
        btn_backpress.setOnClickListener(v->finish());
    }

    void init()
    {
        btn_backpress=findViewById(R.id.btn_backpress);
    }
}
