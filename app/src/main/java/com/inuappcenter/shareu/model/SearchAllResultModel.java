package com.inuappcenter.shareu.model;

import android.view.View;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.my_class.Document;
import com.inuappcenter.shareu.my_class.Notice;
import com.inuappcenter.shareu.my_class.SuperiorLecture;
import com.inuappcenter.shareu.recycler.SuperiorLectureAdapter2;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchAllResultModel {
    MutableLiveData<List<SuperiorLecture>> SuperiorData = new MutableLiveData<>();
    MutableLiveData< List<Document> > NewData = new MutableLiveData<>();
    MutableLiveData<Boolean> internet = new MutableLiveData<>();
    MutableLiveData<Boolean> flag = new MutableLiveData<>();

    public MutableLiveData<List<SuperiorLecture>> getSuperiorData() {
        return SuperiorData;
    }

    public MutableLiveData<List<Document>> getNewData() {
        return NewData;
    }

    public void setSuperiorData(List<SuperiorLecture> superiorData) {
        SuperiorData.postValue(superiorData);
    }

    public void setNewData(List<Document> newData) {
        NewData.postValue(newData);
    }

    public MutableLiveData<Boolean> getInternet() {
        return internet;
    }


    public MutableLiveData<Boolean> getFlag() {
        return flag;
    }

    public void setSuperiorDatas(String name)
    {
        RetrofitService networkService = RetrofitHelper.create();
        networkService.documentTop5ScoreList(name,"","").enqueue(new Callback<List<SuperiorLecture>>() {
            @Override
            public void onResponse(Call<List<SuperiorLecture>> call, Response<List<SuperiorLecture>> response) {
                if (response.isSuccessful()) {
                    List<SuperiorLecture> tmp_list = new ArrayList<>();
                    if(response.body().size()==0)
                    {
                        flag.postValue(true);
                        //tv_no_superior.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        flag.postValue(false);
                        //tv_no_superior.setVisibility(View.GONE);
                        for(int i=0;i<response.body().size();i++)
                        {
                            tmp_list.add(new SuperiorLecture(response.body().get(i).getDocumentKey(),response.body().get(i).getTitle(),
                                    response.body().get(i).getUploadDate(),response.body().get(i).getExtension(),response.body().get(i).getRating()));
                        }
                        setSuperiorData(tmp_list);
                    }
                }

            }

            @Override
            public void onFailure(Call<List<SuperiorLecture>> call, Throwable t) {

            }
        });
    }

    public void setSuperiorDatas(String subj,String prof)
    {
        RetrofitService networkService = RetrofitHelper.create();
        networkService.documentTop5ScoreList("",subj,prof).enqueue(new Callback<List<SuperiorLecture>>() {
            @Override
            public void onResponse(Call<List<SuperiorLecture>> call, Response<List<SuperiorLecture>> response) {
                if (response.isSuccessful()) {
                    List<SuperiorLecture> tmp_list = new ArrayList<>();
                    if(response.body().size()==0)
                    {
                        flag.postValue(true);
                        //tv_no_superior.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        flag.postValue(false);
                        //tv_no_superior.setVisibility(View.GONE);
                        for(int i=0;i<response.body().size();i++)
                        {
                            tmp_list.add(new SuperiorLecture(response.body().get(i).getDocumentKey(),response.body().get(i).getTitle(),
                                    response.body().get(i).getUploadDate(),response.body().get(i).getExtension(),response.body().get(i).getRating()));
                        }
                        setSuperiorData(tmp_list);
                    }
                }

            }

            @Override
            public void onFailure(Call<List<SuperiorLecture>> call, Throwable t) {

            }
        });
    }

    public void setNewDatas(String name)
    {
        RetrofitService networkService = RetrofitHelper.create();
        networkService.documentTop5DateList(name,"","").enqueue(new Callback<List<Document>>() {
            @Override
            public void onResponse(Call<List<Document>> call, Response<List<Document>> response) {
                if (response.isSuccessful()) {
                    List<Document> tmp_list = new ArrayList<>();
                    for(int i=0;i<response.body().size();i++)
                    {
                        tmp_list.add(new Document(response.body().get(i).getTitle(),
                                response.body().get(i).getUploadDate(),
                                response.body().get(i).getDocumentKey(),
                                null,null,null,null,0));
                    }
                    setNewData(tmp_list);
                }

            }

            @Override
            public void onFailure(Call<List<Document>> call, Throwable t) {

            }
        });
    }

    public void setNewDatas(String subj,String prof)
    {
        RetrofitService networkService = RetrofitHelper.create();
        networkService.documentTop5DateList("",subj,prof).enqueue(new Callback<List<Document>>() {
            @Override
            public void onResponse(Call<List<Document>> call, Response<List<Document>> response) {
                if (response.isSuccessful()) {
                    List<Document> tmp_list = new ArrayList<>();
                    for(int i=0;i<response.body().size();i++)
                    {
                        tmp_list.add(new Document(response.body().get(i).getTitle(),
                                response.body().get(i).getUploadDate(),
                                response.body().get(i).getDocumentKey(),
                                null,null,null,null,0));
                    }
                    setNewData(tmp_list);
                }

            }

            @Override
            public void onFailure(Call<List<Document>> call, Throwable t) {

            }
        });
    }
}
