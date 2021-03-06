package com.inuappcenter.shareu.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.fragment.GyoyangFragment;
import com.inuappcenter.shareu.fragment.MajorFragment;
import com.inuappcenter.shareu.fragment.MajorFragment2;
import com.inuappcenter.shareu.my_class.Major;
import com.inuappcenter.shareu.my_interface.OnItemClick;
import com.inuappcenter.shareu.recycler.MajorAdapter;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;


public class MajorActivity extends AppCompatActivity {

    LinearLayoutManager manager;
    private ArrayList<Major> dataList;
    private FragmentManager fragmentManager;
    private MajorFragment fragmentMajor;
    private GyoyangFragment fragmentGyoyang;
    private MajorFragment2 fragmentMajor2;
    private FragmentTransaction transaction;
    private EditText etv_search;
    private ImageButton etv_search_click;
    ImageButton btn_left_bar_major;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_major);
        //this.initializeData();

        TextView tv_major =  findViewById(R.id.tv_major) ;
        TextView tv_gyoyang = findViewById(R.id.tv_gyoyang) ;

        btn_left_bar_major = findViewById(R.id.btn_backpress);

        etv_search = findViewById(R.id.etv_search);
        etv_search_click=findViewById(R.id.etv_search_click);

        tv_gyoyang.setTextColor(Color.parseColor("#000000"));
        tv_major.setTextColor(Color.parseColor("#574FBA"));
        tv_major.setTypeface(Typeface.DEFAULT_BOLD);

        fragmentMajor = new MajorFragment();
        fragmentGyoyang = new GyoyangFragment();
        fragmentMajor2 = new MajorFragment2();

        fragmentManager = getSupportFragmentManager();
        fragmentMajor = new MajorFragment();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.layout_frame_category,fragmentMajor);
        transaction.commitAllowingStateLoss();

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.tv_major :
                        if(tv_major.getCurrentTextColor()!=ContextCompat.getColor(getApplicationContext(), R.color.Iris)){
                            tv_major.setTextColor(Color.parseColor("#574FBA"));
                            tv_gyoyang.setTextColor(Color.parseColor("#000000"));
                            tv_gyoyang.setTypeface(Typeface.DEFAULT_BOLD);
                            fragmentManager = getSupportFragmentManager();
                            transaction = fragmentManager.beginTransaction();
                            transaction.replace(R.id.layout_frame_category,fragmentMajor);
                            transaction.commitAllowingStateLoss();
                        }
                        break;
                    case R.id.tv_gyoyang:
                        if(tv_gyoyang.getCurrentTextColor()!=ContextCompat.getColor(getApplicationContext(), R.color.Iris))
                        {

                            tv_major.setTextColor(Color.parseColor("#000000"));
                            tv_gyoyang.setTextColor(Color.parseColor("#574FBA"));
                            tv_gyoyang.setTypeface(Typeface.DEFAULT_BOLD);
                            fragmentManager = getSupportFragmentManager();
                            transaction = fragmentManager.beginTransaction();
                            transaction.replace(R.id.layout_frame_category,fragmentGyoyang).commitAllowingStateLoss();
                        }
                        break;
                }
            }
        } ;
        tv_major.setOnClickListener(onClickListener) ;
        tv_gyoyang.setOnClickListener(onClickListener);
        etv_search_click.setOnClickListener(onClickListener);

        btn_left_bar_major.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStack();
                } else {
                    finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }


}
