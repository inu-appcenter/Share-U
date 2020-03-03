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
import com.inuappcenter.shareu.recycler.MajorAdapter2;
import com.inuappcenter.shareu.recycler.MajorAdapter3;
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

public class MajorFragment2 extends Fragment{
    private IndexFastScrollRecyclerView recyclerView;
    private LinearLayoutManager manager;
    private ArrayList<Major> dataList;
    private ArrayList<categoryCulture>dataList2;
    TextView tv_my_major;
    private String name;
    private View view;
    private TextView tv_no_search;

    private EditText etv_search;
    private ImageButton etv_search_click;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_recyclerview_select_major, container, false);

        Bundle args = getArguments();
        name = getArguments().getString("name");

        tv_my_major = view.findViewById(R.id.tv_my_major);
        tv_my_major.setVisibility(VISIBLE);
        tv_my_major.setText(name);

        etv_search=view.findViewById(R.id.etv_search);
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
                giveSearchResult();
            }
        });
        RetrofitService networkService = RetrofitHelper.create();
        networkService.getDetailedMajorList(name).enqueue(new Callback<List<Major>>() {
            @Override
            public void onResponse(Call<List<Major>> call, Response<List<Major>> response) {
                if (response.isSuccessful()) {
                    dataList = new ArrayList<>();
                    String flag = "?";
                    for (int i = 0; i < response.body().size(); i++) {
                        //Log.e("체크 ! ",name+" "+response.body().get(i).first+" "+response.body().get(i).second+" "+response.body().get(i).third);
                        if (flag.equals(response.body().get(i).third)) {
                            if (i == response.body().size() - 1) {
                                dataList.add(new Major(response.body().get(i).first, response.body().get(i).second, response.body().get(i).third, Code.ViewType.MAJOR, R.color.white));
                            }
                            else if(!response.body().get(i+1).third.equals(response.body().get(i).third))
                            {
                                dataList.add(new Major(response.body().get(i).first,response.body().get(i).second,response.body().get(i).third,Code.ViewType.MAJOR,R.color.white));
                            }
                            else {
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
                            else if(i==response.body().size()-1 )
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
                IndexFastScrollRecyclerView recyclerView = view.findViewById(R.id.recyclerview_select_major);
                LinearLayoutManager manager
                        = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setIndexTextSize(12);
                recyclerView.setIndexBarColor("#FFFFFF");
                recyclerView.setIndexBarTextColor("#000000");
                recyclerView.setIndexBarStrokeVisibility(false);
                recyclerView.setLayoutManager(manager); // LayoutManager 등록
                recyclerView.setAdapter(new MajorAdapter3(getActivity(), dataList));  // Adapter 등록
                if(dataList.size()==0)
                    tv_no_search.setVisibility(View.VISIBLE);
                else
                    tv_no_search.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Major>> call, Throwable t) {
                /*Intent intent = new Intent(getActivity(), ServerFailActivity.class);
                startActivity(intent);*/
                Log.e("흠",t.getCause()+"");
            }
        });

    }

    void giveSearchResult()
    {

        Log.e("흠",tv_my_major.getText()+" "+etv_search.getText()+" ");
        RetrofitService networkService = RetrofitHelper.create();
        networkService.categorySubject(tv_my_major.getText()+"",etv_search.getText()+"").enqueue(new Callback<List<categoryCulture>>() {
            @Override
            public void onResponse(Call<List<categoryCulture>> call, Response<List<categoryCulture>> response) {
                if (response.isSuccessful()) {
                    Log.e("시발",response.body().size()+"");
                    dataList2 = new ArrayList<>();
                    String flag = "?";
                    for (int i = 0; i < response.body().size(); i++) {
                        //Log.e("체크 ! ",name+" "+response.body().get(i).first+" "+response.body().get(i).second+" "+response.body().get(i).third);
                        if (flag.equals(response.body().get(i).getSubjectInitiality())) {
                            if (i == response.body().size() - 1) {
                                dataList2.add(new categoryCulture(response.body().get(i).getSubjectname(), response.body().get(i).getSubjectInitiality(), response.body().get(i).getProfName(), Code.ViewType.MAJOR, R.color.white));
                            }
                            else if(!response.body().get(i+1).getSubjectInitiality().equals(response.body().get(i).getSubjectInitiality()))
                            {
                                dataList2.add(new categoryCulture(response.body().get(i).getSubjectname(),response.body().get(i).getSubjectInitiality(),response.body().get(i).getProfName(),Code.ViewType.MAJOR,R.color.white));
                            }
                            else {
                                dataList2.add(new categoryCulture(response.body().get(i).getSubjectname(), response.body().get(i).getSubjectInitiality(), response.body().get(i).getProfName(), Code.ViewType.MAJOR, R.color.gray));
                            }
                        }
                        else {
                            flag = response.body().get(i).getSubjectInitiality();
                            dataList2.add(new categoryCulture(response.body().get(i).getSubjectname(), response.body().get(i).getSubjectInitiality(), response.body().get(i).getProfName(), Code.ViewType.INDEX, R.color.gray));
                            if(response.body().size()==1)
                            {
                                dataList2.add(new categoryCulture(response.body().get(i).getSubjectname(),response.body().get(i).getSubjectInitiality(),response.body().get(i).getProfName(),Code.ViewType.MAJOR,R.color.gray));
                            }
                            else if(i==response.body().size()-1 )
                            {
                                dataList2.add(new categoryCulture(response.body().get(i).getSubjectname(),response.body().get(i).getSubjectInitiality(),response.body().get(i).getProfName(),Code.ViewType.MAJOR,R.color.white));
                            }
                            else if(!response.body().get(i+1).getSubjectInitiality().equals(response.body().get(i).getSubjectInitiality()))
                            {
                                dataList2.add(new categoryCulture(response.body().get(i).getSubjectname(),response.body().get(i).getSubjectInitiality(),response.body().get(i).getProfName(),Code.ViewType.MAJOR,R.color.white));
                            }
                            else
                                dataList2.add(new categoryCulture(response.body().get(i).getSubjectname(),response.body().get(i).getSubjectInitiality(),response.body().get(i).getProfName(),Code.ViewType.MAJOR,R.color.gray));
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
                recyclerView.setAdapter(new MajorAdapter2(getActivity(), dataList2));  // Adapter 등록
                if(dataList.size()==0)
                    tv_no_search.setVisibility(View.VISIBLE);
                else
                    tv_no_search.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<categoryCulture>> call, Throwable t) {
                Intent intent = new Intent(getActivity(), ServerFailActivity.class);
                startActivity(intent);
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
