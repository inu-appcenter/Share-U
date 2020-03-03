package com.inuappcenter.shareu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.inuappcenter.shareu.R;

import androidx.annotation.Nullable;

public class PoliceActivity extends Activity{
    ImageView btn_backpress;
    TextView tv_type;
    String type;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_police);
        btn_backpress = findViewById(R.id.btn_backpress);
        tv_type=findViewById(R.id.tv_type);
        btn_backpress.setOnClickListener(v->finish());
        Intent intent =getIntent();
        type =intent.getExtras().getString("type");
        tv_type.setText(type+" ");
    }
}
