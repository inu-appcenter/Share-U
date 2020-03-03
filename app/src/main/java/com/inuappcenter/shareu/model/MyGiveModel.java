package com.inuappcenter.shareu.model;

import android.util.Log;

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
    MutableLiveData<List<MyUpload> > listMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<List<MyUpload>> getListMutableLiveData() {
        return listMutableLiveData;
    }

    public void setListMutableLiveData(List<MyUpload> dataList) {
        this.listMutableLiveData.postValue(dataList);
    }
    public void setDatas()
    {

       /* RetrofitService networkService = RetrofitHelper.create();
        networkService.score(key).enqueue(new Callback<List<score>>() {
            @Override
            public void onResponse(Call<List<score>> call, Response<List<score>> response) {
                if (response.isSuccessful()) {
                    review_ratingbar.setRating(response.body().get(0).getScore());

                }

            }

            @Override
            public void onFailure(Call<List<score>> call, Throwable t) {

            }
        });
        List<MyUpload> tmp_list = new ArrayList<>();
        tmp_list.add(new MyUpload("ppt","2019.11.21","2019년도 일본학개론"));
        tmp_list.add(new MyUpload("word","2019.10.30","2017년도 임상여성학 족보"));
        tmp_list.add(new MyUpload("jpeg","2019.09.18","2019년도 1학기 일본학개론"));
        tmp_list.add(new MyUpload("ps","2019.08.30","시각디자인 포스터 자료"));
        tmp_list.add(new MyUpload("ppt","2019.11.21","2019년도 일본학개론"));
        tmp_list.add(new MyUpload("word","2019.10.30","2017년도 임상여성학 족보"));
        tmp_list.add(new MyUpload("hwp","2019.09.18","2019년도 1학기 일본학개론"));
        tmp_list.add(new MyUpload("mp3","2019.08.30","시각디자인 포스터 자료"));
        tmp_list.add(new MyUpload("ppt","2019.11.21","2019년도 일본학개론"));
        tmp_list.add(new MyUpload("word","2019.10.30","2017년도 임상여성학 족보"));
        tmp_list.add(new MyUpload("jpeg","2019.09.18","2019년도 1학기 일본학개론"));
        tmp_list.add(new MyUpload("ps","2019.08.30","시각디자인 포스터 자료"));
        setListMutableLiveData(tmp_list);*/
    }

}
