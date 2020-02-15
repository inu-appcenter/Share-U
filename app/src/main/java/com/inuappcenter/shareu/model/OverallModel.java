package com.inuappcenter.shareu.model;

import android.util.Log;
import android.widget.Toast;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.activity.OverallNoticeActivity;
import com.inuappcenter.shareu.my_class.Notice;
import com.inuappcenter.shareu.recycler.OverallNoticeAdapter;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OverallModel {

    private MutableLiveData<List<Notice>> _dataList = new MutableLiveData<>();

    public LiveData<List<Notice>> getDataList() {
        return _dataList;
    }

    public void setDataList(List<Notice> dataList) {
        this._dataList.postValue(dataList);
    }

    public void setDatas()
    {
        RetrofitService networkService = RetrofitHelper.create();
        networkService.getNotice().enqueue(new Callback<List<Notice>>(){
            @Override
            public void onResponse(Call<List<Notice> > call, Response<List<Notice>> response)
            {
                if(response.isSuccessful())
                {
                    Log.e("냥",response.body().size()+"");
                    setDataList(response.body());
                    //overallNoticeView.setDatas(overallModel.getDatas(response.body()));
                }

            }
            @Override
            public void onFailure(Call<List<Notice>> call, Throwable t) {

                Log.e("TAG", t.getMessage());

            }
        });
    }




}
