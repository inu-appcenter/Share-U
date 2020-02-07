package com.inuappcenter.shareu.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.fragment.GyoyangFragment;
import com.inuappcenter.shareu.fragment.MajorFragment;
import com.inuappcenter.shareu.fragment.MajorFragment2;
import com.inuappcenter.shareu.model.Code;
import com.inuappcenter.shareu.model.Major;
import com.inuappcenter.shareu.recycler.GyoyangAdapter;
import com.inuappcenter.shareu.recycler.MajorAdapter;
import com.inuappcenter.shareu.recycler.MajorAdapter2;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import in.myinnos.alphabetsindexfastscrollrecycler.IndexFastScrollRecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MajorActivity extends AppCompatActivity {
    IndexFastScrollRecyclerView recyclerView;
    IndexFastScrollRecyclerView recyclerView2;
    LinearLayoutManager manager;
    private ArrayList<Major> dataList;
    private FragmentManager fragmentManager;
    private MajorFragment fragmentMajor;
    private GyoyangFragment fragmentGyoyang;
    private FragmentTransaction transaction;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_major);
        //this.initializeData();

        TextView tv_major = (TextView) findViewById(R.id.tv_major) ;
        TextView tv_gyoyang = (TextView) findViewById(R.id.tv_gyoyang) ;
        Button btn_left_bar_major = (Button)findViewById(R.id.btn_left_bar_major);

        tv_gyoyang.setTextColor(Color.parseColor("#000000"));
        tv_major.setTextColor(Color.parseColor("#574FBA"));
        tv_major.setTypeface(Typeface.DEFAULT_BOLD);

        fragmentManager = getSupportFragmentManager();

        fragmentMajor = new MajorFragment();

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.layout_frame_category,fragmentMajor);
        transaction.addToBackStack(null);
        transaction.commit();
        TextView.OnClickListener onClickListener = new TextView.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.tv_major :
                        if(tv_major.getCurrentTextColor()!=ContextCompat.getColor(getApplicationContext(), R.color.Iris)){
                            tv_major.setTextColor(Color.parseColor("#574FBA"));
                            tv_gyoyang.setTextColor(Color.parseColor("#000000"));
                            tv_gyoyang.setTypeface(Typeface.DEFAULT_BOLD);
                            fragmentManager = getSupportFragmentManager();
                            fragmentMajor = new MajorFragment();
                            transaction = fragmentManager.beginTransaction();
                            transaction.replace(R.id.layout_frame_category,fragmentMajor);
                            transaction.commit();
                        }
                        break;
                    case R.id.tv_gyoyang:
                        if(tv_gyoyang.getCurrentTextColor()!=ContextCompat.getColor(getApplicationContext(), R.color.Iris))
                        {
                            tv_major.setTextColor(Color.parseColor("#000000"));
                            tv_gyoyang.setTextColor(Color.parseColor("#574FBA"));
                            tv_gyoyang.setTypeface(Typeface.DEFAULT_BOLD);
                            fragmentManager = getSupportFragmentManager();
                            fragmentGyoyang = new GyoyangFragment();
                            transaction = fragmentManager.beginTransaction();
                            transaction.replace(R.id.layout_frame_category,fragmentGyoyang).commitAllowingStateLoss();
                        }
                        break;
                }
            }
        } ;
        tv_major.setOnClickListener(onClickListener) ;
        tv_gyoyang.setOnClickListener(onClickListener);


        Button.OnClickListener onClickListener2 = new Button.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                switch(view.getId())
                {
                    case R.id.btn_left_bar_major:
                    {
                        finish();
                    }
                }
            }
        };
        btn_left_bar_major.setOnClickListener(onClickListener2);

    }


}
