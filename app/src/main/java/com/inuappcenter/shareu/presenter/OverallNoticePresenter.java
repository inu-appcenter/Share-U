package com.inuappcenter.shareu.presenter;

import android.util.Log;

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
    OverallNoticeContract.View overallNoticeView;
    OverallModel overallModel;

    public OverallNoticePresenter(OverallNoticeContract.View view) {
        this.overallNoticeView = view;
        this.overallModel = new OverallModel();
    }


    @Override
    public void onResume() {
        //이 context를 가진 애한테 콜백을 넘겨주자!
        overallModel.setDatas();
        overallNoticeView.setDatas(overallModel.getDataList());
    }
}
