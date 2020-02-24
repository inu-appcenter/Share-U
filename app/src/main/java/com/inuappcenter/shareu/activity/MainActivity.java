package com.inuappcenter.shareu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.fragment.MainFragment;
import com.inuappcenter.shareu.fragment.MajorFragment;
import com.inuappcenter.shareu.my_class.Notice;
import com.inuappcenter.shareu.my_class.SuperiorLecture;
import com.inuappcenter.shareu.recycler.NoticeAdapter;
import com.inuappcenter.shareu.recycler.SuperiorLectureAdapter2;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Drawer 처리
    DrawerLayout drawer_my_page;
   /* private ArrayList<Notice> dataList;
    private ViewPager viewPager ;*/
    private EditText etv_search;
    private ImageButton etv_search_click;
    private FragmentManager fragmentManager;
    private MainFragment fragmentMain;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //drawer 처리
        View view = (View)findViewById(R.id.drawer_logout);
        view.setVisibility(View.GONE);
        drawer_my_page = (DrawerLayout)findViewById(R.id.include_drawer_my_page);
        drawer_my_page.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_left_bar_main :
                        Intent intent = new Intent(getApplicationContext(),MajorActivity.class);
                        startActivity(intent);
                        break ;
                    case R.id.btn_my_page_main :
                        drawer_my_page.openDrawer(GravityCompat.END);
                        break ;
                    case R.id.fab_main:
                        Intent intent3 = new Intent(getApplicationContext(), FileUploadActivity.class);
                        startActivity(intent3);
                        break ;
                    case R.id.etv_search_click:
                        //메인화면 검색
                }

            }
        } ;
        //과목 선택 처리
        Button btn_left_bar_main = (Button) findViewById(R.id.btn_left_bar_main) ;
        btn_left_bar_main.setOnClickListener(onClickListener) ;
        //drawer 처리
        Button btn_my_page_main = (Button)findViewById(R.id.btn_my_page_main);
        btn_my_page_main.setOnClickListener(onClickListener) ;

        FloatingActionButton fab_main = (FloatingActionButton)findViewById(R.id.fab_main);
        fab_main.setOnClickListener(onClickListener);

        etv_search=findViewById(R.id.etv_search);
        etv_search_click = findViewById(R.id.etv_search_click);
        etv_search_click.setOnClickListener(onClickListener);
        fragmentManager = getSupportFragmentManager();
        fragmentMain = new MainFragment();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.layout_frame_main,fragmentMain);
        transaction.commit();


    }
    @Override
    public void onBackPressed() {
        if (drawer_my_page.isDrawerOpen(GravityCompat.END)) {
            drawer_my_page.closeDrawer(GravityCompat.END);
        }
        else {
            super.onBackPressed();
        }
    }
    public void closeDrawer()
    {
        drawer_my_page.closeDrawer(GravityCompat.END);
    }
}



