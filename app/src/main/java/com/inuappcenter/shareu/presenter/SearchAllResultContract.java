package com.inuappcenter.shareu.presenter;

import com.inuappcenter.shareu.my_class.Document;
import com.inuappcenter.shareu.my_class.SuperiorLecture;

import java.util.List;

public interface SearchAllResultContract {

    interface View
    {
        void giveMeSuperior(List<SuperiorLecture> superiorLectures);
        void setVisible(Boolean flag);
        void setInternet();
        void giveMeNew(List<Document>documents);
    }
    interface Presenter
    {
        void onCreate();
        void onResume();
        void onDestroy();
        void setText(String name);
    }
}
