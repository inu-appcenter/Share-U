package com.inuappcenter.shareu.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.activity.MajorActivity2;
import com.inuappcenter.shareu.model.Code;
import com.inuappcenter.shareu.model.Major;
import com.inuappcenter.shareu.recycler.MajorAdapter;
import com.inuappcenter.shareu.recycler.MajorAdapter2;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import in.myinnos.alphabetsindexfastscrollrecycler.IndexFastScrollRecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.VISIBLE;

public class MajorFragment2 extends Fragment {
    private IndexFastScrollRecyclerView recyclerView;
    private LinearLayoutManager manager;
    private ArrayList<Major> dataList;
    TextView tv_my_major;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_recyclerview_select_major, container, false);

        Bundle args = getArguments();
        String name = getArguments().getString("name");

        tv_my_major = view.findViewById(R.id.tv_my_major);
        tv_my_major.setVisibility(VISIBLE);
        tv_my_major.setText(name);

        RetrofitService networkService = RetrofitHelper.create();
        networkService.getDetailedMajorList(name).enqueue(new Callback<List<Major>>() {
            @Override
            public void onResponse(Call<List<Major>> call, Response<List<Major>> response) {
                if (response.isSuccessful()) {
                    dataList = new ArrayList<>();
                    String flag = "?";
                    for (int i = 0; i < response.body().size(); i++) {
                        if (flag.equals(response.body().get(i).third)) {
                            if (i == response.body().size() - 1) {
                                dataList.add(new Major(response.body().get(i).first, response.body().get(i).second, response.body().get(i).third, Code.ViewType.MAJOR, R.color.white));
                            } else if (!response.body().get(i + 1).third.equals(response.body().get(i).third)) {
                                dataList.add(new Major(response.body().get(i).first, response.body().get(i).second, response.body().get(i).third, Code.ViewType.MAJOR, R.color.white));
                            } else {
                                dataList.add(new Major(response.body().get(i).first, response.body().get(i).second, response.body().get(i).third, Code.ViewType.MAJOR, R.color.gray));
                            }
                        }
                        else {
                            flag = response.body().get(i).third;
                            dataList.add(new Major(response.body().get(i).third, response.body().get(i).second, response.body().get(i).third, Code.ViewType.INDEX, R.color.gray));
                            if(response.body().size()==1)
                            {
                                dataList.add(new Major(response.body().get(i).first,response.body().get(i).second,response.body().get(i).third,Code.ViewType.MAJOR,R.color.gray));
                            }
                            else if(!response.body().get(i+1).third.equals(response.body().get(i).third))
                            {
                                dataList.add(new Major(response.body().get(i).first,response.body().get(i).second,response.body().get(i).third,Code.ViewType.MAJOR,R.color.white));
                            }
                            else
                                dataList.add(new Major(response.body().get(i).first,response.body().get(i).second,response.body().get(i).third,Code.ViewType.MAJOR,R.color.gray));
                        }
                    }


                }
                IndexFastScrollRecyclerView recyclerView = view.findViewById(R.id.recyclerview_select_major);
                LinearLayoutManager manager
                        = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setIndexTextSize(12);
                recyclerView.setIndexBarColor("#FFFFFF");
                recyclerView.setIndexBarTextColor("#000000");
                recyclerView.setIndexBarStrokeVisibility(false);
                recyclerView.setLayoutManager(manager); // LayoutManager 등록
                recyclerView.setAdapter(new MajorAdapter2(getActivity(), dataList));  // Adapter 등록
            }

            @Override
            public void onFailure(Call<List<Major>> call, Throwable t) {

            }
        });

        return view;
    }

}
