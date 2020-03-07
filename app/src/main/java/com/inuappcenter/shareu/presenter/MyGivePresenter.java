package com.inuappcenter.shareu.presenter;

import com.inuappcenter.shareu.model.MyGiveModel;
import com.inuappcenter.shareu.model.MyUploadModel;
import com.inuappcenter.shareu.my_class.MyUpload;

import java.util.List;
import java.util.Objects;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

public class MyGivePresenter implements MyGiveContract.Presenter{

    LifecycleOwner lifecycleOwner;
    MyGiveContract.View myUploadView;
    MyGiveModel myGiveModel;
    //뷰랑 모델을 불러내서 작업을 하게 해야징


    public MyGivePresenter(LifecycleOwner lifecycleOwner, MyGiveContract.View myUploadView) {
        this.lifecycleOwner = lifecycleOwner;
        this.myUploadView = myUploadView;
        this.myGiveModel = new MyGiveModel();
    }

    @Override
    public void onCreate() {
        myGiveModel.getDataList().observe(lifecycleOwner,new Observer<List<MyUpload> >(){
                    @Override
                    public void onChanged(List<MyUpload> myUploads) {
                        if (myUploads != null) {
                            myUploadView.setDatas(myUploads);
                        }
                    }
                }

        );
        myGiveModel.getInternet().observe(lifecycleOwner, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean)
                {
                    myUploadView.setInternet(aBoolean);
                }
            }
        });
    }

    @Override
    public void onResume() {
        myGiveModel.setDatas();
    }

    @Override
    public void onDestroy()
    {
        myGiveModel.getDataList().removeObservers(lifecycleOwner);
    }

    @Override
    public void setToken(String token) {
        myGiveModel.setToken(token);
    }
}
