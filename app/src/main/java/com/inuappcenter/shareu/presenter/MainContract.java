package com.inuappcenter.shareu.presenter;

import com.inuappcenter.shareu.my_class.Notice;
import com.inuappcenter.shareu.my_class.SuperiorLecture;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public interface MainContract {
    interface View
    {
        void setSuperior(List<SuperiorLecture> value);
        void setNotice(List<Notice> value);
        void setInternet();
    }
    interface Presenter
    {
        void onCreate();
        void onResume();
        void onDestroy();
    }
}
