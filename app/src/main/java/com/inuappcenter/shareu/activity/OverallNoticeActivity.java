package com.inuappcenter.shareu.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;

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

    private OverallNoticeContract.Presenter overallNoticePresent = new OverallNoticePresenter(this, this);
    private OverallNoticeAdapter adapter = new OverallNoticeAdapter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overall_notice);
        initializeView();
        overallNoticePresent.onCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Data update starts here.
        overallNoticePresent.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        overallNoticePresent.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void setDatas(List<Notice> datas) {
        //콜백을 받았습니다!
        adapter.setData(datas);
    }

    private void initializeView() {
        View.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_detailed_notice_backpress :
                        finish();
                        break ;
                }

            }
        };

        ImageButton btn_detailed_notice_backpress =(ImageButton)findViewById(R.id.btn_detailed_notice_backpress);
        btn_detailed_notice_backpress.setOnClickListener(onClickListener);

        RecyclerView rcv = findViewById(R.id.recyclerview_overall_notice);
        RecyclerView.LayoutManager mgr = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        rcv.setLayoutManager(mgr);
        rcv.setAdapter(adapter);
    }
}