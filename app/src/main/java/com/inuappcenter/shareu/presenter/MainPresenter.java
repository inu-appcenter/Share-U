package com.inuappcenter.shareu.presenter;

import com.inuappcenter.shareu.model.MainModel;
import com.inuappcenter.shareu.my_class.Notice;
import com.inuappcenter.shareu.my_class.SuperiorLecture;

import java.util.List;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

public class MainPresenter implements MainContract.Presenter{

    LifecycleOwner lifecycleOwner;
    MainModel mainModel;
    MainContract.View mainView;

    public MainPresenter(LifecycleOwner lifecycleOwner, MainContract.View mainView) {
        this.lifecycleOwner = lifecycleOwner;
        this.mainView = mainView;
        this.mainModel = new MainModel();
    }

    @Override
    public void onCreate() {

        mainModel.getNoticeData().observe(lifecycleOwner, new Observer<List<Notice>>() {
            @Override
            public void onChanged(List<Notice> notices) {
                if(notices!=null)
                    mainView.setNotice(notices);
            }
        });
        mainModel.getSuperiorData().observe(lifecycleOwner, new Observer<List<SuperiorLecture>>() {
            @Override
            public void onChanged(List<SuperiorLecture> superiorLectures) {
                if(superiorLectures!=null)
                    mainView.setSuperior(superiorLectures);
            }
        });
        mainModel.getInternet().observe(lifecycleOwner, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean)
                {
                    mainView.setInternet();
                }
            }
        });
    }

    @Override
    public void onResume() {
        mainModel.setSuperior();
        mainModel.setNotice();
    }

    @Override
    public void onDestroy() {
        mainModel.getNoticeData().removeObservers(lifecycleOwner);
        mainModel.getSuperiorData().removeObservers(lifecycleOwner);
    }
}
