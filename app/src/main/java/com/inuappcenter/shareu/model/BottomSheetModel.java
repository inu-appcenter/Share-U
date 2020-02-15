package com.inuappcenter.shareu.model;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.my_class.Notice;
import com.inuappcenter.shareu.my_class.subjectName;
import com.inuappcenter.shareu.my_class.profName;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BottomSheetModel {
    private ArrayList<subjectName> dataList;
    private ArrayList<profName> dataList2;
    public ArrayList<subjectName> getSubjDatas(List<subjectName> sibal)
    {
        for(int i=0;i<sibal.size();i++)
        {
            dataList.add(sibal.get(i));
        }
        return dataList;
    }

  /*  public ArrayList<profName> getProfDatas()
    {
        dataList2 = new ArrayList<>();

        RetrofitService networkService = RetrofitHelper.create();
        networkService.getProfName("").enqueue(new Callback<List<profName>>(){
            @Override
            public void onResponse(Call<List<profName>> call, Response<List<profName>> response)
            {
                if(response.isSuccessful())
                {
                    dataList2=new ArrayList<>();
                    for(int i=0;i<response.body().size();i++)
                    {
                        dataList2.add(new profName(response.body().get(i).getProfName()));
                    }

                }


            }
            @Override
            public void onFailure(Call<List<profName>> call, Throwable t) {

                //Toast.makeText(getApplicationContext(), "실패지렁", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
        return dataList2;
    }*/
}
