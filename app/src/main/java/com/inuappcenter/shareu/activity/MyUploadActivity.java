package com.inuappcenter.shareu.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.my_class.MyUpload;
import com.inuappcenter.shareu.my_class.TokenManager;
import com.inuappcenter.shareu.presenter.MyUploadContract;
import com.inuappcenter.shareu.presenter.MyUploadPresenter;
import com.inuappcenter.shareu.recycler.MyUploadAdapter;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyUploadActivity extends AppCompatActivity implements MyUploadContract.View {

    private MyUploadPresenter myUploadPresenter = new MyUploadPresenter(this,this);
    private MyUploadAdapter myUploadAdapter = new MyUploadAdapter(this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_upload);
        initializeView();
        myUploadPresenter.onCreate();
        TokenManager tm = TokenManager.getInstance();
        String token = tm.getToken(this);
        myUploadPresenter.setToken(token);
    }

    @Override
    protected void onResume() {
        super.onResume();
        myUploadPresenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myUploadPresenter.onDestroy();
    }

    //콜백 받아서 데이터를 수정한다!
    @Override
    public void setDatas(List<MyUpload> datas) {
        myUploadAdapter.setData(datas);

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
        rcv.setAdapter(myUploadAdapter);

    }
}
