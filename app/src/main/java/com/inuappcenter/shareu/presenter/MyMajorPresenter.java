package com.inuappcenter.shareu.presenter;

import com.inuappcenter.shareu.model.MyMajorModel;

import androidx.lifecycle.LifecycleOwner;

public class MyMajorPresenter implements MyMajorContract.Presenter{

    LifecycleOwner lifecycleOwner;
    MyMajorContract.View myMajorView;
    MyMajorModel myMajorModel;

    public MyMajorPresenter(LifecycleOwner lifecycleOwner, MyMajorContract.View myMajorView, MyMajorModel myMajorModel) {
        this.lifecycleOwner = lifecycleOwner;
        this.myMajorView = myMajorView;
        this.myMajorModel = new MyMajorModel();
    }

    @Override
    public void onCreate() {


    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }
}
