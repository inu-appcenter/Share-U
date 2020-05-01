package com.inuappcenter.shareu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.my_class.Notice;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailedNoticeActivity extends AppCompatActivity {

    TextView tv_detailed_notice_title;
    TextView tv_detailed_notice_date;
    TextView tv_detailed_notice_content;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_notice);
        tv_detailed_notice_title=(TextView)findViewById(R.id.tv_detailed_notice_title);
        tv_detailed_notice_date=(TextView)findViewById(R.id.tv_detailed_notice_date);
        tv_detailed_notice_content=(TextView)findViewById(R.id.tv_detailed_notice_content);
        RetrofitService networkService = RetrofitHelper.create();
        Intent intent = getIntent();
        int key = intent.getExtras().getInt("key");
        networkService.getDetailedNotice(key).enqueue(new Callback<List<Notice>>(){
            @Override
            public void onResponse(Call<List<Notice> > call, Response<List<Notice>> response)
            {
                if(response.isSuccessful())
                {
                    // TODO size가 0일 때 처리가 안 되어 있어요

                    String sibal = response.body().get(0).getContent().replace("\\n","\n");
                    tv_detailed_notice_title.setText(response.body().get(0).getTitle());
                    tv_detailed_notice_date.setText(response.body().get(0).getNoticeDate());
                    tv_detailed_notice_content.setText(sibal);
                }

            }
            @Override
            public void onFailure(Call<List<Notice>> call, Throwable t) {

            }
        });

        View.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_backpress:
                        finish();
                        break ;
                }

            }
        } ;

        ImageButton btn_detailed_notice_backpress =(ImageButton)findViewById(R.id.btn_backpress);
        btn_detailed_notice_backpress.setOnClickListener(onClickListener);
    }
    public void onBackPressed() {
        super.onBackPressed();
    }
}
