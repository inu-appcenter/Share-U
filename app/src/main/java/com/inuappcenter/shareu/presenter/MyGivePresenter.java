package com.inuappcenter.shareu.presenter;

import com.inuappcenter.shareu.model.MyGiveModel;
import com.inuappcenter.shareu.my_class.MyUpload;

import java.util.List;
import java.util.Objects;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

public class MyGivePresenter implements MyGiveContract.Presenter{

    LifecycleOwner lifecycleOwner;
    MyGiveContract.View myGiveContractView;
    MyGiveModel myGiveModel;

    public MyGivePresenter(LifecycleOwner lifecycleOwner, MyGiveContract.View myGiveContractView) {
        this.lifecycleOwner = lifecycleOwner;
        this.myGiveContractView = myGiveContractView;
        this.myGiveModel = new MyGiveModel();
    }

    @Override
    public void onCreate() {
        //여기서 데이터를 구독하자
        myGiveModel.getListMutableLiveData().observe(lifecycleOwner,new Observer<List<MyUpload> >(){
                    @Override
                    public void onChanged(List<MyUpload> myUploads) {
                        if (myUploads != null) {
                            myGiveContractView.setDatas(myUploads);
                        }
                    }
                }

        );
    }

    @Override
    public void onResume() {
        //변화가 발생하면 데이터를 설정해주자
        //모델의 데이터를 바꿔서 또 observe의 onChanged 콜백이 실행되게끔!
        myGiveModel.setDatas();
    }

    @Override
    public void onDestroy() {
        //구독자의 참조를 취소해주자
        myGiveModel.getListMutableLiveData().removeObservers(lifecycleOwner);
    }
}
