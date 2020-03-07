package com.inuappcenter.shareu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.my_class.MyUpload;
import com.inuappcenter.shareu.my_class.SumPoint;
import com.inuappcenter.shareu.my_class.TokenManager;
import com.inuappcenter.shareu.my_class.userPointList;
import com.inuappcenter.shareu.presenter.MyGiveContract;
import com.inuappcenter.shareu.presenter.MyGivePresenter;
import com.inuappcenter.shareu.presenter.MyUploadPresenter;
import com.inuappcenter.shareu.recycler.MyGiveAdapter;
import com.inuappcenter.shareu.recycler.MyPointAdapter;
import com.inuappcenter.shareu.recycler.MyUploadAdapter;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyGiveActivity extends AppCompatActivity implements MyGiveContract.View {

    private MyGivePresenter myGivePresenter = new MyGivePresenter(this,this);
    private MyGiveAdapter myGiveAdapter = new MyGiveAdapter(this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_give);
        initializeView();
        myGivePresenter.onCreate();
        TokenManager tm = TokenManager.getInstance();
        String token = tm.getToken(this);
        myGivePresenter.setToken(token);
    }

    @Override
    protected void onResume() {
        super.onResume();
        myGivePresenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myGivePresenter.onDestroy();
    }

    //콜백 받아서 데이터를 수정한다!
    @Override
    public void setDatas(List<MyUpload> datas) {
        myGiveAdapter.setData(datas);

    }

    @Override
    public void setInternet(Boolean internet) {
        Intent intent = new Intent(getApplicationContext(), ServerFailActivity.class);
        startActivity(intent);
    }

    private void initializeView() {
        View.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_backpress:
                        finish();
                        break ;

                }

            }
        };

        ImageButton btn_detailed_notice_backpress =(ImageButton)findViewById(R.id.btn_backpress);
        btn_detailed_notice_backpress.setOnClickListener(onClickListener);
        RecyclerView rcv = findViewById(R.id.recyclerview_myupload);
        RecyclerView.LayoutManager mgr = new GridLayoutManager(getApplicationContext(),2);
        rcv.setLayoutManager(mgr);
        rcv.setAdapter(myGiveAdapter);

    }

}
