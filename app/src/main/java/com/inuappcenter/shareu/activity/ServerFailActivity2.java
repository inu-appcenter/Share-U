package com.inuappcenter.shareu.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.inuappcenter.shareu.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ServerFailActivity2 extends AppCompatActivity {
    private TextView textView31;
    private TextView textView32;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_fail);
        ImageView btn_backpress =findViewById(R.id.btn_backpress);
        textView31=findViewById(R.id.textView31);
        textView32=findViewById(R.id.textView32);
        btn_backpress.setOnClickListener(v->finish());
        textView31.setText("잠시 후 ");
        textView32.setText("다시 시도해주세요.");
    }
}
