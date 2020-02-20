package com.inuappcenter.shareu.presenter;

import com.inuappcenter.shareu.my_class.MyUpload;

import java.util.List;

public interface MyGiveContract {
    interface View
    {
        void setDatas(List<MyUpload>myUploadList);
    }
    interface Presenter
    {
        void onCreate();
        void onResume();
        void onDestroy();
    }

}
