
package com.inuappcenter.shareu.presenter;

import androidx.lifecycle.LiveData;

import com.inuappcenter.shareu.my_class.Notice;

import java.util.ArrayList;
import java.util.List;

public interface OverallNoticeContract {
    interface  View{
        void setDatas(List<Notice> datas);
    }
    interface Presenter{
        public void onCreate();
        public void onResume();
        public void onDestroy();
    }
    //View와 Presenter은 소통을 할 수 있어야해!
}