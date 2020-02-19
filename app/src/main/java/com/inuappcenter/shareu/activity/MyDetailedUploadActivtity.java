package com.inuappcenter.shareu.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.inuappcenter.shareu.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyDetailedUploadActivtity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_detailed_upload_activity);
        initializeView();

    }
    private void initializeView() {
        View.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_detailed_notice_backpress :
                        finish();
                        break ;

                }

            }
        };

        ImageButton btn_detailed_notice_backpress =(ImageButton)findViewById(R.id.btn_detailed_notice_backpress);
        btn_detailed_notice_backpress.setOnClickListener(onClickListener);
    }
}
