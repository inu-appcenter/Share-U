package com.inuappcenter.shareu.presenter;

import com.inuappcenter.shareu.model.OverallModel;

public class OverallNoticePresenter implements OverallNoticeContract.Presenter{
    OverallNoticeContract.View overallNoticeView;
    OverallModel overallModel;

    public OverallNoticePresenter(OverallNoticeContract.View view) {
        this.overallNoticeView = view;
        this.overallModel = new OverallModel();
    }

    @Override
    public void onResume() {
        overallNoticeView.setDatas(overallModel.getDatas());
    }
}
