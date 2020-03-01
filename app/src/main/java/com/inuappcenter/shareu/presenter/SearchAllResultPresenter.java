package com.inuappcenter.shareu.presenter;

import com.inuappcenter.shareu.fragment.SearchAllResultFragment;
import com.inuappcenter.shareu.model.MainModel;
import com.inuappcenter.shareu.model.SearchAllResultModel;
import com.inuappcenter.shareu.my_class.Document;
import com.inuappcenter.shareu.my_class.SuperiorLecture;
import com.inuappcenter.shareu.recycler.SuperiorLectureAdapter2;

import java.util.List;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

public class SearchAllResultPresenter implements SearchAllResultContract.Presenter {

    LifecycleOwner lifecycleOwner;
    SearchAllResultModel searchAllResultModel;
    SearchAllResultContract.View searchAllView;

    private String name;

    public SearchAllResultPresenter(LifecycleOwner lifecycleOwner, SearchAllResultContract.View searchAllView) {
        this.lifecycleOwner = lifecycleOwner;
        this.searchAllResultModel = new SearchAllResultModel();
        this.searchAllView = searchAllView;
    }

    @Override
    public void onCreate() {
        searchAllResultModel.getSuperiorData().observe(lifecycleOwner, new Observer<List<SuperiorLecture>>() {
            @Override
            public void onChanged(List<SuperiorLecture> superiorLectures) {
                if(superiorLectures!=null)
                    searchAllView.giveMeSuperior(superiorLectures);
            }
        });
        searchAllResultModel.getNewData().observe(lifecycleOwner, new Observer<List<Document>>() {
            @Override
            public void onChanged(List<Document> documents) {

                if(documents!=null)
                    searchAllView.giveMeNew(documents);
            }
        });

        searchAllResultModel.getInternet().observe(lifecycleOwner, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean)
                    searchAllView.setInternet();
            }
        });

        searchAllResultModel.getFlag().observe(lifecycleOwner, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                searchAllView.setVisible(aBoolean);
            }
        });
    }

    @Override
    public void onResume() {
        searchAllResultModel.setSuperiorDatas(name);
        searchAllResultModel.setNewDatas(name);
    }

    @Override
    public void onDestroy() {
        searchAllResultModel.getSuperiorData().removeObservers(lifecycleOwner);
        searchAllResultModel.getInternet().removeObservers(lifecycleOwner);
        searchAllResultModel.getNewData().removeObservers(lifecycleOwner);
        searchAllResultModel.getFlag().removeObservers(lifecycleOwner);
    }

    @Override
    public void setText(String name) {
        this.name = name;
    }
}
