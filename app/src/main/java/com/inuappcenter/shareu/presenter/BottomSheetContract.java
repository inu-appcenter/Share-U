package com.inuappcenter.shareu.presenter;

import com.inuappcenter.shareu.my_class.Notice;
import com.inuappcenter.shareu.my_class.subjectName;
import com.inuappcenter.shareu.my_class.profName;
import java.util.ArrayList;

public interface BottomSheetContract {
    interface  View{
        void setSubjData(ArrayList<subjectName> datas);
        void setProfData(ArrayList<profName> datas);
    }
    interface Presenter{
        void giveSubjData();
        void giveProfData();
    }
    //View와 Presenter은 소통을 할 수 있어야해!
}
