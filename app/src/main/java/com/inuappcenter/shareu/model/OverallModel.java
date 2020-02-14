package com.inuappcenter.shareu.model;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.activity.OverallNoticeActivity;
import com.inuappcenter.shareu.my_class.Notice;
import com.inuappcenter.shareu.recycler.OverallNoticeAdapter;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OverallModel {

    private ArrayList<Notice> dataList;

    public ArrayList<Notice> getDatas()
    {
        dataList = new ArrayList<>();

        for(int i=0;i<5;i++)
        {
            dataList.add(new Notice((i+1)+"",
                    "냥냥"+i+1,
                    "야옹"+i+1,
                    i+1,
                    R.drawable.rightarrow));
        }
        return dataList;
    }
}
