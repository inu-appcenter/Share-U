package com.inuappcenter.shareu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.my_class.Fuck;
import com.inuappcenter.shareu.my_class.score;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import java.util.List;

import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectPoliceActivity extends Activity {

    private TextView tv_fish,tv_ad,tv_trick,tv_fuck;
    private int key;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_select_police);
        init();
        listen();
    }
    void init()
    {
        tv_fish = findViewById(R.id.tv_fish);
        tv_ad = findViewById(R.id.tv_ad);
        tv_trick = findViewById(R.id.tv_trick);
        tv_fuck = findViewById(R.id.tv_fuck);
        Intent intent =getIntent();
        key =intent.getExtras().getInt("key");
    }

    void listen()
    {
        tv_fish.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                goSingo(tv_fish.getText()+"");
            }
        });

        tv_ad.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                goSingo(tv_ad.getText()+"");
            }
        });

        tv_trick.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                goSingo(tv_trick.getText()+"");
            }
        });

        tv_fuck.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                goSingo(tv_fuck.getText()+"");
            }
        });
    }

    void goSingo(String type)
    {
        RetrofitService networkService = RetrofitHelper.create();
        networkService.report(key,type).enqueue(new Callback<Fuck>() {
            @Override
            public void onResponse(Call<Fuck> call, Response<Fuck> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(getApplicationContext(),PoliceActivity.class);
                    intent.putExtra("type",type);
                    startActivity(intent);
                    finish();
                }

            }

            @Override
            public void onFailure(Call<Fuck> call, Throwable t) {

            }
        });
    }

}
