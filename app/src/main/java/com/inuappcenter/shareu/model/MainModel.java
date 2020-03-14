package com.inuappcenter.shareu.model;

import android.util.Log;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.activity.ServerFailActivity;
import com.inuappcenter.shareu.activity.ServerFailActivity2;
import com.inuappcenter.shareu.my_class.Notice;
import com.inuappcenter.shareu.my_class.SuperiorLecture;
import com.inuappcenter.shareu.service.Navigator;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainModel {

    MutableLiveData< List<SuperiorLecture> > SuperiorData = new MutableLiveData<>();
    MutableLiveData< List<Notice> > NoticeData = new MutableLiveData<>();
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


    //메인 우수자료
    public void setSuperior()
    {
        //Log.e("여기도 오능데","야ㅑㅇ야야옹");
        RetrofitService networkService = RetrofitHelper.create();
        networkService.mainTop5ScoreList().enqueue(new Callback<List<SuperiorLecture>>() {
            @Override
            public void onResponse(Call<List<SuperiorLecture>> call, Response<List<SuperiorLecture>> response) {
                List<SuperiorLecture> tmp_list2 =new ArrayList<>();
                if (response.isSuccessful()) {
                    for(int i=0;i<response.body().size();i++)
                    {
                        tmp_list2.add(new SuperiorLecture(response.body().get(i).getDocumentKey(),

                                response.body().get(i).getTitle(),response.body().get(i).getUploadDate(),
                                response.body().get(i).getExtension(),
                                response.body().get(i).getRating()));
                    }
                    setSuperiorData(tmp_list2);

                }
                else
                {
                    Navigator.launchActicity(ServerFailActivity2.class);
                }

            }

            @Override
            public void onFailure(Call<List<SuperiorLecture>> call, Throwable t) {
                Navigator.launchActicity(ServerFailActivity.class);
            }
        });
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
                                response.body().get(i).getNoticeKey(),
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
