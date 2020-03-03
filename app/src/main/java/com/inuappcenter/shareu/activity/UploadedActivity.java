package com.inuappcenter.shareu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.inuappcenter.shareu.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UploadedActivity extends AppCompatActivity {

    private ImageButton btn_back_home;
    private ImageButton btn_ok;
    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploaded);
        textView=findViewById(R.id.textView);
        textView.setText("+5");
        btn_back_home = findViewById(R.id.btn_back_home);
        btn_ok = findViewById(R.id.btn_ok);
        btn_back_home.setOnClickListener(v->finish());
        btn_ok.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MyUploadActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
