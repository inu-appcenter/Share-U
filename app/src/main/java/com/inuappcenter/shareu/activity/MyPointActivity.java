package com.inuappcenter.shareu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.my_class.SumPoint;
import com.inuappcenter.shareu.my_class.TokenManager;
import com.inuappcenter.shareu.my_class.reviewList;
import com.inuappcenter.shareu.my_class.userPointList;
import com.inuappcenter.shareu.presenter.MainContract;
import com.inuappcenter.shareu.recycler.MyPointAdapter;
import com.inuappcenter.shareu.recycler.ReviewAdapter;
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

public class MyPointActivity extends AppCompatActivity {

    private MyPointAdapter myPointAdapter;
    private TextView tv_numeric_point;
    private ImageView btn_backpress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_point);
        init();
        listen();
    }
    void init()
    {
        tv_numeric_point=findViewById(R.id.tv_numeric_point);
        btn_backpress=findViewById(R.id.btn_backpress);
    }

    void listen()
    {
        btn_backpress.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        giveMePoint();
        giveMeList();
    }

    void giveMePoint()
    {
        TokenManager tm = TokenManager.getInstance();
        String token = tm.getToken(getApplicationContext());
        RetrofitService networkService = RetrofitHelper.create();
        networkService.sumpoint(token).enqueue(new Callback<List<SumPoint> >(){
            @Override
            public void onResponse(Call<List<SumPoint>> call, Response<List<SumPoint>> response)
            {
                if(response.isSuccessful())
                {
                    tv_numeric_point.setText(response.body().get(0).getPoint()+"");
                }

            }
            @Override
            public void onFailure(Call<List<SumPoint>> call, Throwable t) {

            }
        });
    }
    void giveMeList()
    {
        TokenManager tm = TokenManager.getInstance();

        String token = tm.getToken(this);
        RetrofitService networkService = RetrofitHelper.create();
        networkService.userPointList(token).enqueue(new Callback<List<userPointList>>() {
            @Override
            public void onResponse(Call<List<userPointList>> call, Response<List<userPointList>> response) {
                if (response.isSuccessful()) {
                    List<userPointList>tmp_list = new ArrayList<>();
                    for(int i=response.body().size()-1;i>=0;i--)
                    {
                        tmp_list.add(new userPointList(response.body().get(i).getPoint(),response.body().get(i).getDocumentKey(),

                                response.body().get(i).getPointloadDate(),response.body().get(i).getTitle()));
                    }
                    myPointAdapter =new MyPointAdapter(getApplicationContext(),tmp_list);
                    RecyclerView recyclerView2=findViewById(R.id.recyclerview_point);
                    LinearLayoutManager layoutManager2=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
                    recyclerView2.setHasFixedSize(true);
                    recyclerView2.setLayoutManager(layoutManager2);
                    recyclerView2.setAdapter(myPointAdapter);
                }

            }

            @Override
            public void onFailure(Call<List<userPointList>> call, Throwable t) {


            }
        });
    }
}
