package com.inuappcenter.shareu.presenter;

import com.inuappcenter.shareu.my_class.MyUpload;
import com.inuappcenter.shareu.my_class.Notice;

import java.util.List;

public interface MyUploadContract {
    interface  View{
        void setDatas(List<MyUpload> datas);
    }
    interface Presenter{
        public void onCreate();
        public void onResume();
        public void onDestroy();
    }
    //View와 Presenter은 소통을 할 수 있어야해!
}
