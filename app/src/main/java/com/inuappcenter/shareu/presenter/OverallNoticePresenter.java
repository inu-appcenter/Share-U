package com.inuappcenter.shareu.presenter;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.model.OverallModel;
import com.inuappcenter.shareu.my_class.Notice;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OverallNoticePresenter implements OverallNoticeContract.Presenter{
    private LifecycleOwner lifecycleOwner;
    OverallNoticeContract.View overallNoticeView;
    OverallModel overallModel;

    public OverallNoticePresenter(OverallNoticeContract.View view, LifecycleOwner lifecycleOwner) {
        this.overallNoticeView = view;
        this.overallModel = new OverallModel();
        this.lifecycleOwner = lifecycleOwner;
    }

    @Override
    public void onCreate() {
        // Add observer(activity as a Lifecycle Observer.)
        // 모델에서 데이터가 바뀌면 observe하다가 onChanged 콜백이 불리게 된다.
        overallModel.getDataList().observe(lifecycleOwner, new Observer<List<Notice>>() {
            @Override
            public void onChanged(List<Notice> notices) {
                if (notices != null) {
                    overallNoticeView.setDatas(notices);
                }
            }
        });
        overallModel.getFlag().observe(lifecycleOwner, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean)
                {
                    overallNoticeView.setInternet();
                }
            }
        });
    }

    @Override
    public void onResume() {
        // Trigger update
        overallModel.setDatas();
    }

    @Override
    public void onDestroy() {
        // Remove observer.
        overallModel.getDataList().removeObservers(lifecycleOwner);
    }
}
