package com.inuappcenter.shareu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.model.Notice;
import com.inuappcenter.shareu.model.SuperiorLecture;
import com.inuappcenter.shareu.recycler.NoticeAdapter;
import com.inuappcenter.shareu.recycler.SuperiorLectureAdapter;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;
import com.kingfisher.easyviewindicator.RecyclerViewIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Drawer 처리
    DrawerLayout drawer_my_page;
    private ArrayList<Notice> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recycler 처리
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerview_main);
        RecyclerViewIndicator horizontalIndicator = (RecyclerViewIndicator)findViewById(R.id.recyclerViewIndicator);

        final EditText editText =(EditText)findViewById(R.id.etv_main);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<SuperiorLecture> items=new ArrayList<>();
        SuperiorLecture[] item=new SuperiorLecture[5];
        item[0]=new SuperiorLecture(R.drawable.pdf,"문학과테마기행 족보",5);
        item[1]=new SuperiorLecture(R.drawable.excel,"시스템프로그래밍 족보",4);
        item[2]=new SuperiorLecture(R.drawable.ppt,"생명과학 족보",(float)4.5);
        item[3]=new SuperiorLecture(R.drawable.word,"디지털기술과미래 족보",3);
        item[4]=new SuperiorLecture(R.drawable.pdf,"경영경제수학 족보",2);

        for(int i=0;i<5;i++) items.add(item[i]);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new SuperiorLectureAdapter(getApplicationContext(),items));
        horizontalIndicator.setRecyclerView(recyclerView);


        RetrofitService networkService = RetrofitHelper.create();
        networkService.getNotice().enqueue(new Callback<List<Notice> >(){
            @Override
            public void onResponse(Call<List<Notice> > call, Response<List<Notice>> response)
            {

                Log.e("ㅎㅎ",response.body().get(0).getTitle());
                if(response.isSuccessful())
                {
                    dataList = new ArrayList<>();
                    for(int i=0;i<response.body().size();i++)
                    {
                        dataList.add(new Notice((i+1)+". "+response.body().get(i).getTitle(),
                                response.body().get(i).getContent(),
                                response.body().get(i).getNoticeDate(),
                                response.body().get(i).getNoticeKey(),
                                R.drawable.rightarrow));
                    }
                }
                RecyclerView recyclerView2=(RecyclerView)findViewById(R.id.recyclerview2_main);
                LinearLayoutManager layoutManager2=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
                recyclerView2.setHasFixedSize(true);
                recyclerView2.setLayoutManager(layoutManager2);
                recyclerView2.setAdapter(new NoticeAdapter(MainActivity.this,dataList));
            }
            @Override
            public void onFailure(Call<List<Notice>> call, Throwable t) {
                Log.e("TAG", t.getMessage());
            }
        });


        //drawer 처리
        View view = (View)findViewById(R.id.drawer_logout);
        view.setVisibility(View.GONE);
        drawer_my_page = (DrawerLayout)findViewById(R.id.include_drawer_my_page);
        View.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_left_bar_main :
                        Intent intent = new Intent(getApplicationContext(),MajorActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_right,R.anim.hold);
                        break ;
                    case R.id.btn_my_page_main :
                        drawer_my_page.openDrawer(GravityCompat.END);
                        break ;
                    case R.id.tv_notice_set_title_more :
                        Intent intent2 = new Intent(getApplicationContext(), OverallNoticeActivity.class);
                        startActivity(intent2);
                        break ;
                }

            }
        } ;
        //과목 선택 처리
        Button btn_left_bar_main = (Button) findViewById(R.id.btn_left_bar_main) ;
        btn_left_bar_main.setOnClickListener(onClickListener) ;
        //drawer 처리
        Button btn_my_page_main = (Button)findViewById(R.id.btn_my_page_main);
        btn_my_page_main.setOnClickListener(onClickListener) ;

        TextView tv_notice_set_title_more = (TextView)findViewById(R.id.tv_notice_set_title_more);
        tv_notice_set_title_more.setOnClickListener(onClickListener);



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



