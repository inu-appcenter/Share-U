package com.inuappcenter.shareu.presenter;

public interface MyMajorContract {
    interface View
    {
        void setData();
    }
    interface Presenter
    {
        void onCreate();
        void onResume();
        void onDestroy();
    }
}
