package com.inuappcenter.shareu.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.my_class.MyUpload;
import com.inuappcenter.shareu.presenter.MyGiveContract;
import com.inuappcenter.shareu.presenter.MyGivePresenter;
import com.inuappcenter.shareu.recycler.MyGiveAdapter;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyGiveActivity extends AppCompatActivity implements MyGiveContract.View {

    MyGivePresenter myGivePresenter=new MyGivePresenter(this,this);
    MyGiveAdapter myGiveAdapter=new MyGiveAdapter(this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_give);
        initializeView();
        myGivePresenter.onCreate();

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

    @Override
    public void setDatas(List<MyUpload> myUploadList) {
        myGiveAdapter.setData(myUploadList);
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
