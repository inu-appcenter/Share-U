package com.inuappcenter.shareu.presenter;

import com.inuappcenter.shareu.model.MyUploadModel;
import com.inuappcenter.shareu.my_class.MyUpload;

import java.util.List;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

public class MyUploadPresenter implements MyUploadContract.Presenter{

    LifecycleOwner lifecycleOwner;
    MyUploadContract.View myUploadView;
    MyUploadModel myUploadModel;
    //뷰랑 모델을 불러내서 작업을 하게 해야징


    public MyUploadPresenter(LifecycleOwner lifecycleOwner, MyUploadContract.View myUploadView) {
        this.lifecycleOwner = lifecycleOwner;
        this.myUploadView = myUploadView;
        this.myUploadModel = new MyUploadModel();
    }

    @Override
    public void onCreate() {
        myUploadModel.getDataList().observe(lifecycleOwner,new Observer<List<MyUpload> >(){
                    @Override
                    public void onChanged(List<MyUpload> myUploads) {
                        if (myUploads != null) {
                            myUploadView.setDatas(myUploads);
                        }
                    }
                }

        );
    }

    @Override
    public void onResume() {
        myUploadModel.setDatas();
    }

    @Override
    public void onDestroy()
    {
        myUploadModel.getDataList().removeObservers(lifecycleOwner);
    }

    @Override
    public void setToken(String token) {
        myUploadModel.setToken(token);
    }
}
