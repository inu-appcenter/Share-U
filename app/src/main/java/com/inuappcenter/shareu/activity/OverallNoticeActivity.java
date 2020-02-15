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
import androidx.lifecycle.Observer;
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



        overallNoticePresent.giveMeDataPleaseWhenEverItIsOkayPleaseJust().observe(this, new Observer<List<Notice>>() {
            @Override
            public void onChanged(List<Notice> notices) {
                if (notices != null) {
                    // 여기에 도착한 리스트는 믿을 수 있다!
                    recyclerView.setAdapter(new OverallNoticeAdapter(notices,OverallNoticeActivity.this));  // Adapter 등록
                }
            }
        });



        overallNoticePresent.onResume();
        //내 컨텍스트.onResume()한거니깐 이것도 콜백이당 ㅇㅅㅇ Caller은 당연히 implements 해당하는 인터페이스를 갖고있어야 한다능

        Log.d("OverallNoticeActivity", "Has onResume done its job?");


        recyclerView = findViewById(R.id.recyclerview_overall_notice);
        manager= new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager); // LayoutManager 등록

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
