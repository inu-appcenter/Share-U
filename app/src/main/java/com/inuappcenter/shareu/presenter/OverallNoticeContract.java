package com.inuappcenter.shareu.presenter;

import com.inuappcenter.shareu.my_class.Notice;

import java.util.ArrayList;
import java.util.List;

public interface OverallNoticeContract {
    interface  View{
        void setDatas(List<Notice> datas);
    }
    interface Presenter{
        public void onResume();
    }
    //View와 Presenter은 소통을 할 수 있어야해!
}
