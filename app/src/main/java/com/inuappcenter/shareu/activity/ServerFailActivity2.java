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
    private TextView tv_server_fail;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_fail);
        ImageView btn_backpress =findViewById(R.id.btn_backpress);
        textView31=findViewById(R.id.textView31);
        textView32=findViewById(R.id.textView32);
        tv_server_fail=findViewById(R.id.tv_server_fail);
        btn_backpress.setOnClickListener(v->finish());
        textView31.setText("점검 시간 : 2020/03/14 ~ 2020/03/16");
        textView32.setText("");
        tv_server_fail.setText("서버 점검 중 입니다.");
    }
}
