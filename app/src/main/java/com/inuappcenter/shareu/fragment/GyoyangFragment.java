package com.inuappcenter.shareu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.activity.ServerFailActivity;
import com.inuappcenter.shareu.my_class.Code;
import com.inuappcenter.shareu.my_class.Major;
import com.inuappcenter.shareu.my_class.categoryCulture;
import com.inuappcenter.shareu.my_interface.OnItemClick;
import com.inuappcenter.shareu.recycler.GyoyangAdapter;
import com.inuappcenter.shareu.recycler.SubjectAdapter;
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

public class GyoyangFragment extends Fragment  {
    private IndexFastScrollRecyclerView recyclerView;
    private LinearLayoutManager manager;
    private ArrayList<Major> dataList;
    private View view;
    private TextView tv_no_search;

    private EditText etv_search;
    private ImageButton etv_search_click;
    private ArrayList<categoryCulture>dataList2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_recyclerview_select_major, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        tv_no_search = getActivity().findViewById(R.id.tv_no_search);
        etv_search = getActivity().findViewById(R.id.etv_search);
        etv_search_click = getActivity().findViewById(R.id.etv_search_click);
        etv_search_click.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                giveMyGyoyang();
            }
        });

        RetrofitService networkService2 = RetrofitHelper.create();
        networkService2.getDetailedGyoyangList().enqueue(new Callback<List<Major>>(){
            @Override
            public void onResponse(Call<List<Major> > call, Response<List<Major>> response) {
                if(response.isSuccessful())
                {
                    tv_no_search.setVisibility(View.GONE);
                    dataList = new ArrayList<>();
                    String flag ="?";
                    for(int i=0;i<response.body().size();i++)
                    {
                        if(flag.equals(response.body().get(i).third))
                        {
                            if(i==response.body().size()-1)
                            {
                                dataList.add(new Major(response.body().get(i).first,response.body().get(i).second,response.body().get(i).third,Code.ViewType.MAJOR,R.color.white));
                            }
                            else if(!response.body().get(i+1).third.equals(response.body().get(i).third))
                            {

                                dataList.add(new Major(response.body().get(i).first,response.body().get(i).second,response.body().get(i).third,Code.ViewType.MAJOR,R.color.white));
                            }
                            else
                            {
                                dataList.add(new Major(response.body().get(i).first,response.body().get(i).second,response.body().get(i).third,Code.ViewType.MAJOR,R.color.gray));
                            }
                        }
                        else
                        {
                            flag=response.body().get(i).third;
                            dataList.add(new Major(response.body().get(i).third,null,response.body().get(i).third,Code.ViewType.INDEX,R.color.gray));
                            if(response.body().size()==1)
                            {
                                dataList.add(new Major(response.body().get(i).first,response.body().get(i).second,response.body().get(i).third,Code.ViewType.MAJOR,R.color.gray));
                            }
                            else if(i==response.body().size()-1)
                            {
                                dataList.add(new Major(response.body().get(i).first,response.body().get(i).second,response.body().get(i).third,Code.ViewType.MAJOR,R.color.white));
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
                recyclerView = view.findViewById(R.id.recyclerview_select_major);
                LinearLayoutManager manager2
                        = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);

                recyclerView.setIndexTextSize(12);
                recyclerView.setIndexBarColor("#FFFFFF");
                recyclerView.setIndexBarTextColor("#000000");
                recyclerView.setIndexBarStrokeVisibility(false);
                recyclerView.setLayoutManager(manager2); // LayoutManager 등록
                recyclerView.setAdapter(new GyoyangAdapter(getActivity(),dataList));  // Adapter 등록


            }
            @Override
            public void onFailure(Call<List<Major>> call, Throwable t) {
                Intent intent = new Intent(getActivity(), ServerFailActivity.class);
                startActivity(intent);
            }
        });
    }
    void giveMyGyoyang()
    {
        RetrofitService networkService = RetrofitHelper.create();
        networkService.categoryCulture(etv_search.getText()+"").enqueue(new Callback<List<categoryCulture>>() {
            @Override
            public void onResponse(Call<List<categoryCulture>> call, Response<List<categoryCulture>> response) {

                if (response.isSuccessful()) {
                    dataList2 = new ArrayList<>();
                    String flag = "?";
                    Log.e("흠",response.body().size()+"");
                    for (int i = 0; i < response.body().size(); i++) {
                        if (flag.equals(response.body().get(i).getSubjectInitiality())) {

                            if (i == response.body().size() - 1) {
                                dataList2.add(new categoryCulture(response.body().get(i).getSubjectname(), response.body().get(i).getProfName(), response.body().get(i).getSubjectInitiality(), Code.ViewType.MAJOR, R.color.white));
                            }
                            else if(!response.body().get(i+1).getSubjectInitiality().equals(response.body().get(i).getSubjectInitiality()))
                            {
                                dataList2.add(new categoryCulture(response.body().get(i).getSubjectname(),response.body().get(i).getProfName(),response.body().get(i).getSubjectInitiality(),Code.ViewType.MAJOR,R.color.white));
                            }
                            else {
                                dataList2.add(new categoryCulture(response.body().get(i).getSubjectname(), response.body().get(i).getProfName(), response.body().get(i).getSubjectInitiality(), Code.ViewType.MAJOR, R.color.gray));
                            }
                        }
                        else {
                            flag = response.body().get(i).getSubjectInitiality();
                            dataList2.add(new categoryCulture(response.body().get(i).getSubjectInitiality(), response.body().get(i).getProfName(), response.body().get(i).getSubjectInitiality(), Code.ViewType.INDEX, R.color.gray));
                            if(response.body().size()==1)
                            {
                                dataList2.add(new categoryCulture(response.body().get(i).getSubjectname(),response.body().get(i).getProfName(),response.body().get(i).getSubjectInitiality(),Code.ViewType.MAJOR,R.color.gray));
                            }
                            else if(i==response.body().size()-1 )
                            {
                                dataList2.add(new categoryCulture(response.body().get(i).getSubjectname(),response.body().get(i).getProfName(),response.body().get(i).getSubjectInitiality(),Code.ViewType.MAJOR,R.color.white));
                            }
                            else if(!response.body().get(i+1).getSubjectInitiality().equals(response.body().get(i).getSubjectInitiality()))
                            {
                                dataList2.add(new categoryCulture(response.body().get(i).getSubjectname(),response.body().get(i).getProfName(),response.body().get(i).getSubjectInitiality(),Code.ViewType.MAJOR,R.color.white));
                            }
                            else
                                dataList2.add(new categoryCulture(response.body().get(i).getSubjectname(),response.body().get(i).getProfName(),response.body().get(i).getSubjectInitiality(),Code.ViewType.MAJOR,R.color.gray));
                        }
                    }
                }
                recyclerView = view.findViewById(R.id.recyclerview_select_major);
                manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setIndexTextSize(12);
                recyclerView.setIndexBarColor("#FFFFFF");
                recyclerView.setIndexBarTextColor("#000000");
                recyclerView.setIndexBarStrokeVisibility(false);
                recyclerView.setLayoutManager(manager); // LayoutManager 등록
                recyclerView.setAdapter(new SubjectAdapter(getActivity(), dataList2));  // Adapter 등록
                if(dataList2.size()==0)
                    tv_no_search.setVisibility(View.VISIBLE);
                else
                    tv_no_search.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<List<categoryCulture>> call, Throwable t) {
                Intent intent = new Intent(getContext(), ServerFailActivity.class);

            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(view!=null){
            ViewGroup parent = (ViewGroup)view.getParent();
            if(parent!=null){
                parent.removeView(view);
            }
        }

    }


}
