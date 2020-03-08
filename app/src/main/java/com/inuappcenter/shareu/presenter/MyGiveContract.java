package com.inuappcenter.shareu.presenter;

import com.inuappcenter.shareu.my_class.MyUpload;

import java.util.List;

public interface MyGiveContract {
    interface  View{
        void setDatas(List<MyUpload> datas);
        void setInternet(Boolean internet);
    }
    interface Presenter{
        public void onCreate();
        public void onResume();
        public void onDestroy();
        public void setToken(String token);
    }

}
