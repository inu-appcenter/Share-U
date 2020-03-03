package com.inuappcenter.shareu.activity;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.inuappcenter.shareu.R;

import androidx.annotation.Nullable;

public class GetPasswdActivity extends Activity {

    ImageView btn_backpress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        setContentView(R.layout.activity_get_passwd);
        btn_backpress = findViewById(R.id.btn_backpress);
        btn_backpress.setOnClickListener(v->finish());

    }
}
