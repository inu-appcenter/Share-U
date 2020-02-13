package com.inuappcenter.shareu.presenter;

import com.inuappcenter.shareu.my_class.Notice;

import java.util.ArrayList;

public interface OverallNoticeContract {
    interface  View{

        void setDatas(ArrayList<Notice> datas);
    }
    interface Presenter{
        public void onResume();
    }
}
