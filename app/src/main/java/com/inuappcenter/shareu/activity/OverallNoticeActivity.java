package com.inuappcenter.shareu.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.my_class.Notice;
import com.inuappcenter.shareu.presenter.OverallNoticeContract;
import com.inuappcenter.shareu.presenter.OverallNoticePresenter;
import com.inuappcenter.shareu.recycler.OverallNoticeAdapter;
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

public class OverallNoticeActivity extends AppCompatActivity implements OverallNoticeContract.View {

    OverallNoticeContract.Presenter overallNoticePresent;
    RecyclerView recyclerView;
    LinearLayoutManager manager;
    private List<Notice> dataList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overall_notice);
        overallNoticePresent=new OverallNoticePresenter(this);
        overallNoticePresent.onResume();
        //내 컨텍스트.onResume()한거니깐 이것도 콜백이당 ㅇㅅㅇ Caller은 당연히 implements 해당하는 인터페이스를 갖고있어야 한다능

        /*RetrofitService networkService = RetrofitHelper.create();
        networkService.getNotice().enqueue(new Callback<List<Notice>>(){
            @Override
            public void onResponse(Call<List<Notice> > call, Response<List<Notice>> response)
            {
                if(response.isSuccessful())
                {
                    dataList = new ArrayList<>();

                    for(int i=0;i<response.body().size();i++)
                    {
                        dataList.add(new Notice((i+1)+". "+response.body().get(i).getTitle(),
                                response.body().get(i).getContent(),
                                response.body().get(i).getNoticeDate(),
                                i+1,
                                R.drawable.rightarrow));
                    }
                }
                recyclerView = findViewById(R.id.recyclerview_overall_notice);
                manager= new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(manager); // LayoutManager 등록
                recyclerView.setAdapter(new OverallNoticeAdapter(dataList,OverallNoticeActivity.this));  // Adapter 등록
            }
            @Override
            public void onFailure(Call<List<Notice>> call, Throwable t) {
                Log.e("TAG", t.getMessage());
                Toast.makeText(OverallNoticeActivity.this, "실패", Toast.LENGTH_SHORT).show();
            }
        });*/


        recyclerView = findViewById(R.id.recyclerview_overall_notice);
        manager= new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager); // LayoutManager 등록
        recyclerView.setAdapter(new OverallNoticeAdapter(dataList,OverallNoticeActivity.this));  // Adapter 등록

        View.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_detailed_notice_backpress :
                        finish();
                        break ;
                }

            }
        } ;

        ImageButton btn_detailed_notice_backpress =(ImageButton)findViewById(R.id.btn_detailed_notice_backpress);
        btn_detailed_notice_backpress.setOnClickListener(onClickListener);
    }

    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public void setDatas(List<Notice> datas) {
        //콜백을 받았습니다!

        dataList=datas;
    }
}
