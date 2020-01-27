package com.inuappcenter.shareu.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.model.Code;
import com.inuappcenter.shareu.model.Major;
import com.inuappcenter.shareu.recycler.MajorAdapter;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MajorActivity extends AppCompatActivity {

    private ArrayList<Major> dataList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_major);
        //this.initializeData();

        TextView tv_major = (TextView) findViewById(R.id.tv_major) ;
        TextView tv_gyoyang = (TextView) findViewById(R.id.tv_gyoyang) ;
        Button btn_left_bar_major = (Button)findViewById(R.id.btn_left_bar_major);
        TextView.OnClickListener onClickListener = new TextView.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.tv_major :
                        initializeData();
                        tv_gyoyang.setTextColor(Color.parseColor("#000000"));
                        tv_major.setTextColor(Color.parseColor("#574FBA"));
                        /*
                        RetrofitService networkService = RetrofitHelper.create();
                        networkService.getList().enqueue(new Callback<List<Major>>(){
                            @Override
                            public void onResponse(Call<List<Major>> call, Response<List<Major>> response) {
                                if(response.isSuccessful())
                                {
                                    dataList = new ArrayList<>();
                                    String flag ="?";
                                    for(int i=0;i<response.body().size();i++)
                                    {
                                        if(flag.equals(response.body().get(i).third))
                                        {
                                            dataList.add(new Major(response.body().get(i).first,null,null,Code.ViewType.MAJOR));
                                        }
                                        else
                                        {
                                            flag=response.body().get(i).third;
                                            dataList.add(new Major(response.body().get(i).third,null,null,Code.ViewType.INDEX));
                                            dataList.add(new Major(response.body().get(i).first,null,null,Code.ViewType.MAJOR));
                                        }
                                    }


                                }
                                else
                                {

                                }
                            }

                            @Override
                            public void onFailure(Call<List<Major>> call, Throwable t) {

                            }
                        });*/

                        RecyclerView recyclerView = findViewById(R.id.recyclerview_select_major);
                        LinearLayoutManager manager
                                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
                        recyclerView.setLayoutManager(manager); // LayoutManager 등록
                        recyclerView.setAdapter(new MajorAdapter(getApplicationContext(),dataList));  // Adapter 등록


                        break;
                    case R.id.tv_gyoyang:
                        tv_major.setTextColor(Color.parseColor("#000000"));
                        tv_gyoyang.setTextColor(Color.parseColor("#574FBA"));
                        initializeData2();
                        RecyclerView recyclerView2 = findViewById(R.id.recyclerview_select_major);
                        LinearLayoutManager manager2
                                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
                        recyclerView2.setLayoutManager(manager2); // LayoutManager 등록
                        recyclerView2.setAdapter(new MajorAdapter(getApplicationContext(),dataList));  // Adapter 등록

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
                        finish();
                }
            }
        };
        btn_left_bar_major.setOnClickListener(onClickListener2);
        /*RecyclerView recyclerView = findViewById(R.id.recyclerview_select_major);

        LinearLayoutManager manager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager); // LayoutManager 등록
        recyclerView.setAdapter(new MajorAdapter(getApplicationContext(),dataList));  // Adapter 등록*/

    }
    public void initializeData()
    {
        dataList = new ArrayList<>();

        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
    }
    public void initializeData2()
    {
        dataList = new ArrayList<>();

        dataList.add(new Major("사용자1님이 입장하셨습니다2.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요2", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
    }
}
