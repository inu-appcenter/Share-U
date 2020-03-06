package com.inuappcenter.shareu.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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
import com.inuappcenter.shareu.recycler.MajorAdapter;
import com.inuappcenter.shareu.recycler.SubjectAdapter;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;
import com.viethoa.RecyclerViewFastScroller;
import com.viethoa.models.AlphabetItem;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GyoyangFragment extends Fragment  {
    private RecyclerViewFastScroller fastScroller;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager manager;
    private ArrayList<Major> dataList;
    private View view;
    private TextView tv_no_search;

    private EditText etv_search;
    private ImageButton etv_search_click;
    private ArrayList<categoryCulture>dataList2;

    private List<AlphabetItem> mAlphabetItems;
    private List<AlphabetItem> mAlphabetItem2;
    private TextView tv_my_major;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_recyclerview_select_major, container, false);
        fastScroller=view.findViewById(R.id.fast_scroller);
        mRecyclerView=view.findViewById(R.id.recyclerview_select_major);
        tv_my_major=getActivity().findViewById(R.id.tv_my_major);
        tv_my_major.setText("학과명 검색");
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
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE); imm.hideSoftInputFromWindow( etv_search.getWindowToken(), 0);
            }
        });

        etv_search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //Enter key Action
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    giveMyGyoyang();
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE); imm.hideSoftInputFromWindow( etv_search.getWindowToken(), 0);

                    return true;
                }
                return false;
            }
        });

        RetrofitService networkService2 = RetrofitHelper.create();
        networkService2.getDetailedGyoyangList().enqueue(new Callback<List<Major>>(){
            @Override
            public void onResponse(Call<List<Major> > call, Response<List<Major>> response) {
                if (response.isSuccessful()) {
                    tv_no_search.setVisibility(View.GONE);
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
                        } else {
                            flag = response.body().get(i).third;
                            dataList.add(new Major(response.body().get(i).third, null, response.body().get(i).third, Code.ViewType.INDEX, R.color.gray));
                            if (response.body().size() == 1) {
                                dataList.add(new Major(response.body().get(i).first, response.body().get(i).second, response.body().get(i).third, Code.ViewType.MAJOR, R.color.gray));
                            } else if (i == response.body().size() - 1) {
                                dataList.add(new Major(response.body().get(i).first, response.body().get(i).second, response.body().get(i).third, Code.ViewType.MAJOR, R.color.white));
                            } else if (!response.body().get(i + 1).third.equals(response.body().get(i).third)) {
                                dataList.add(new Major(response.body().get(i).first, response.body().get(i).second, response.body().get(i).third, Code.ViewType.MAJOR, R.color.white));
                            } else
                                dataList.add(new Major(response.body().get(i).first, response.body().get(i).second, response.body().get(i).third, Code.ViewType.MAJOR, R.color.gray));
                        }
                    }

                    mAlphabetItems=new ArrayList<>();
                    List<String> strAlphabets = new ArrayList<>();
                    for (int i = 0; i < dataList.size(); i++) {
                        String name = dataList.get(i).getThird();
                        if (!strAlphabets.contains(name)) {
                            strAlphabets.add(name);
                            mAlphabetItems.add(new AlphabetItem(i, name, false));
                        }
                    }


                }
                manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                mRecyclerView.setLayoutManager(manager); // LayoutManager 등록
                mRecyclerView.setAdapter(new GyoyangAdapter(getActivity(), dataList));  // Adapter 등록
                fastScroller.setRecyclerView(mRecyclerView);
                fastScroller.setUpAlphabet(mAlphabetItems);
                if (dataList.size() == 0)
                    tv_no_search.setVisibility(View.VISIBLE);
                else
                    tv_no_search.setVisibility(View.GONE);


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
        mAlphabetItem2 = new ArrayList<>();
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

                    mAlphabetItem2 = new ArrayList<>();
                    List<String>strAlphabets = new ArrayList<>();
                    for(int i=0;i<dataList2.size();i++)
                    {
                        String name = dataList2.get(i).getProfName();
                        if(!strAlphabets.contains(name))
                        {
                            strAlphabets.add(name);
                            mAlphabetItem2.add(new AlphabetItem(i,name,false));
                        }
                    }
                }
                manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                mRecyclerView.setLayoutManager(manager); // LayoutManager 등록
                mRecyclerView.setAdapter(new SubjectAdapter(getActivity(), dataList2));  // Adapter 등록
                fastScroller.setRecyclerView(mRecyclerView);
                fastScroller.setUpAlphabet(mAlphabetItem2);
                if(dataList2.size()==0)
                    tv_no_search.setVisibility(View.VISIBLE);
                else
                    tv_no_search.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<List<categoryCulture>> call, Throwable t) {

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
