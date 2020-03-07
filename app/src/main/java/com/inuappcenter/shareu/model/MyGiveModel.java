package com.inuappcenter.shareu.model;

import android.content.Intent;
import android.util.Log;

import com.inuappcenter.shareu.activity.ServerFailActivity;
import com.inuappcenter.shareu.my_class.MyUpload;
import com.inuappcenter.shareu.my_class.Notice;
import com.inuappcenter.shareu.my_class.TokenManager;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyGiveModel {
    private MutableLiveData<List<MyUpload>> _dataList = new MutableLiveData<>();
    private String token;
    private MutableLiveData<Boolean> internet = new MutableLiveData<>();

    public MutableLiveData<Boolean> getInternet() {
        return internet;
    }

    public MutableLiveData<List<MyUpload>> getDataList() {
        return _dataList;
    }
    public void setDataList(List<MyUpload> dataList)
    {
        this._dataList.postValue(dataList);
    }

    public void setDatas()
    {

        RetrofitService networkService = RetrofitHelper.create();
        networkService.userDownloadList(token).enqueue(new Callback<List<MyUpload>>() {
            @Override
            public void onResponse(Call<List<MyUpload>> call, Response<List<MyUpload>> response) {
                if (response.isSuccessful()) {
                    List<MyUpload> tmp_list = new ArrayList<>();
                    for(int i=response.body().size()-1;i>=0;i--)
                    {
                        tmp_list.add(new MyUpload(response.body().get(i).getUploadDate(),
                                response.body().get(i).getTitle(),response.body().get(i).getExtension(),response.body().get(i).getDocumentKey()));
                    }
                    setDataList(tmp_list);
                }

            }

            @Override
            public void onFailure(Call<List<MyUpload>> call, Throwable t) {

                internet.postValue(true);
            }
        });
    }

    public void setToken(String token) {
        this.token=token;
    }


}
