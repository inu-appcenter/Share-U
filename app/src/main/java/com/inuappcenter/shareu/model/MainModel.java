package com.inuappcenter.shareu.model;

import android.content.Intent;
import android.util.Log;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.activity.ServerFailActivity;
import com.inuappcenter.shareu.my_class.Notice;
import com.inuappcenter.shareu.my_class.SuperiorLecture;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainModel {

    MutableLiveData< List<SuperiorLecture>> SuperiorData = new MutableLiveData<>();
    MutableLiveData< List<Notice>> NoticeData = new MutableLiveData<>();
    MutableLiveData<Boolean> internet = new MutableLiveData<>();
    public MutableLiveData<List<SuperiorLecture>> getSuperiorData() {
        return SuperiorData;
    }

    public void setSuperiorData(List<SuperiorLecture> superiorData) {
        SuperiorData.postValue(superiorData);
    }

    public MutableLiveData<List<Notice>> getNoticeData() {
        return NoticeData;
    }

    public void setNotiecData(List<Notice> noticeData) {
        NoticeData.postValue(noticeData);
    }

    public MutableLiveData<Boolean> getInternet() {
        return internet;
    }


    public void setSuperior()
    {
        List<SuperiorLecture> tmp_list2 =new ArrayList<>();
        tmp_list2.add(new SuperiorLecture(R.drawable.pdf,"문학과테마기행 족보",5));
        tmp_list2.add(new SuperiorLecture(R.drawable.excel,"시스템프로그래밍 족보",(float)4.8));
        tmp_list2.add(new SuperiorLecture(R.drawable.ppt,"생명과학 족보",(float)4.5));
        tmp_list2.add(new SuperiorLecture(R.drawable.word,"디지털기술과미래 족보",(float)3.2));
        tmp_list2.add(new SuperiorLecture(R.drawable.pdf,"경영경제수학 족보",(float)2.5));
        setSuperiorData(tmp_list2);
    }
    public void setNotice()
    {
        internet.postValue(false);
        RetrofitService networkService = RetrofitHelper.create();
        networkService.getNotice().enqueue(new Callback<List<Notice>>(){
            @Override
            public void onResponse(Call<List<Notice> > call, Response<List<Notice>> response)
            {

                List<Notice> tmp_list = new ArrayList<>();
                //Log.e("ㅎㅎ",response.body().get(0).getTitle());
                if(response.isSuccessful())
                {
                    for(int i=0;i<response.body().size();i++)
                    {
                        tmp_list.add(new Notice((i+1)+". "+response.body().get(i).getTitle(),
                                response.body().get(i).getContent(),
                                response.body().get(i).getNoticeDate(),
                                i+1,
                                R.drawable.rightarrow));
                    }
                    setNotiecData(tmp_list);
                }

            }
            @Override
            public void onFailure(Call<List<Notice>> call, Throwable t) {
                internet.postValue(true);
            }
        });
    }
}
