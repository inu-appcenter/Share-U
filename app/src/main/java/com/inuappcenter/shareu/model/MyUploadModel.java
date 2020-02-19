package com.inuappcenter.shareu.model;

import android.util.Log;

import com.inuappcenter.shareu.my_class.MyUpload;
import com.inuappcenter.shareu.my_class.Notice;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyUploadModel {

    private MutableLiveData<List<MyUpload>> _dataList = new MutableLiveData<>();

    public MutableLiveData<List<MyUpload>> getDataList() {
        return _dataList;
    }
    public void setDataList(List<MyUpload> dataList)
    {
        this._dataList.postValue(dataList);
    }
    public void setDatas()
    {
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
        setDataList(tmp_list);
    }

}
