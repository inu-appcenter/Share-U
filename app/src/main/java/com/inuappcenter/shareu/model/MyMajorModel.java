package com.inuappcenter.shareu.model;

import android.util.Log;
import android.widget.Toast;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.my_class.Code;
import com.inuappcenter.shareu.my_class.Major;
import com.inuappcenter.shareu.recycler.MajorAdapter;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyMajorModel {

    private MutableLiveData<List<Major>> _dataList;

    public MutableLiveData<List<Major>> get_dataList() {
        return _dataList;
    }

    public void set_dataList(List<Major> _dataList) {
        this._dataList.postValue(_dataList);
    }

    void setData()
    {
        RetrofitService networkService = RetrofitHelper.create();
        networkService.getMajorList().enqueue(new Callback<List<Major>>() {
            @Override
            public void onResponse(Call<List<Major>> call, Response<List<Major>> response) {
                if (response.isSuccessful()) {
                    List<Major> tmp_list = new ArrayList<>();
                    String flag = "?";

                    for (int i = 0; i < response.body().size(); i++) {
                        if (flag.equals(response.body().get(i).third)) {
                            if (i == response.body().size() - 1) {
                                tmp_list.add(new Major(response.body().get(i).first, response.body().get(i).second, response.body().get(i).third, Code.ViewType.MAJOR, R.color.white));
                            } else if (!response.body().get(i + 1).third.equals(response.body().get(i).third)) {
                                tmp_list.add(new Major(response.body().get(i).first, response.body().get(i).second, response.body().get(i).third, Code.ViewType.MAJOR, R.color.white));
                            } else {
                                tmp_list.add(new Major(response.body().get(i).first, response.body().get(i).second, response.body().get(i).third, Code.ViewType.MAJOR, R.color.gray));
                            }
                        } else {
                            flag = response.body().get(i).third;
                            tmp_list.add(new Major(response.body().get(i).third, response.body().get(i).second, response.body().get(i).third, Code.ViewType.INDEX, R.color.gray));
                            if (!response.body().get(i + 1).third.equals(response.body().get(i).third)) {
                                tmp_list.add(new Major(response.body().get(i).first, response.body().get(i).second, response.body().get(i).third, Code.ViewType.MAJOR, R.color.white));
                            } else
                                tmp_list.add(new Major(response.body().get(i).first, response.body().get(i).second, response.body().get(i).third, Code.ViewType.MAJOR, R.color.gray));
                        }
                    }
                }


            }

            @Override
            public void onFailure(Call<List<Major>> call, Throwable t) {

            }
        });

    }
    
}
