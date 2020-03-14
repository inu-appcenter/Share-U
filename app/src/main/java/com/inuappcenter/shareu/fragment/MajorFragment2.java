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
import com.inuappcenter.shareu.recycler.MajorAdapter;
import com.inuappcenter.shareu.recycler.MajorAdapter2;
import com.inuappcenter.shareu.recycler.MajorAdapter3;
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

import static android.view.View.VISIBLE;

public class MajorFragment2 extends Fragment{
    private RecyclerViewFastScroller fastScroller;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager manager;
    private List<AlphabetItem> mAlphabetItems;
    private List<AlphabetItem> mAlphabetItem2;


    private ArrayList<Major> dataList;
    private ArrayList<categoryCulture>dataList2;
    TextView tv_my_major;
    private String name;
    private View view;
    private TextView tv_no_search;

    private EditText etv_search;
    private ImageButton etv_search_click;

    TextView tv_my_major2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_recyclerview_select_major, container, false);

        Bundle args = getArguments();
        name = getArguments().getString("name");

        tv_my_major = view.findViewById(R.id.tv_my_major);
        tv_my_major.setVisibility(VISIBLE);
        tv_my_major.setText(name);
        tv_my_major2=getActivity().findViewById(R.id.tv_my_major);
        tv_my_major2.setText("과목명 검색");

        etv_search=view.findViewById(R.id.etv_search);
        fastScroller=view.findViewById(R.id.fast_scroller);
        mRecyclerView=view.findViewById(R.id.recyclerview_select_major);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        tv_no_search = getActivity().findViewById(R.id.tv_no_search);
        etv_search = getActivity().findViewById(R.id.etv_search);
        etv_search_click = getActivity().findViewById(R.id.etv_search_click);
        etv_search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //Enter key Action
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    giveSearchResult();
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE); imm.hideSoftInputFromWindow( etv_search.getWindowToken(), 0);

                    return true;
                }
                return false;
            }
        });
        etv_search_click.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                giveSearchResult();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE); imm.hideSoftInputFromWindow( etv_search.getWindowToken(), 0);
            }
        });
        RetrofitService networkService = RetrofitHelper.create();
        networkService.getDetailedMajorList(name).enqueue(new Callback<List<Major>>() {
            @Override
            public void onResponse(Call<List<Major>> call, Response<List<Major>> response) {
                dataList = new ArrayList<>();
                if (response.isSuccessful()) {
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

                    mAlphabetItems=new ArrayList<>();
                    List<String>strAlphabets = new ArrayList<>();
                    for(int i=0;i<dataList.size();i++)
                    {
                        String name = dataList.get(i).getThird();
                        if(!strAlphabets.contains(name))
                        {
                            strAlphabets.add(name);
                            mAlphabetItems.add(new AlphabetItem(i,name,false));
                        }
                    }

                }

                manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                mRecyclerView.setLayoutManager(manager); // LayoutManager 등록
                mRecyclerView.setAdapter(new MajorAdapter3(getActivity(), dataList));  // Adapter 등록
                fastScroller.setRecyclerView(mRecyclerView);
                fastScroller.setUpAlphabet(mAlphabetItems);
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

        mAlphabetItem2 = new ArrayList<>();
        Log.e("흠",tv_my_major.getText()+" "+etv_search.getText()+" ");
        RetrofitService networkService = RetrofitHelper.create();
        networkService.categorySubject(tv_my_major.getText()+"",etv_search.getText()+"").enqueue(new Callback<List<categoryCulture>>() {
            @Override
            public void onResponse(Call<List<categoryCulture>> call, Response<List<categoryCulture>> response) {
                dataList2 = new ArrayList<>();
                if (response.isSuccessful()) {
                    tv_no_search.setVisibility(View.GONE);
                    //Log.e("시발",response.body().size()+"");

                    String flag = "?";
                    for (int i = 0; i < response.body().size(); i++) {
                        //Log.e("체크 ! ",name+" "+response.body().get(i).getSubjectname()+" "+response.body().get(i).getSubjectInitiality()+" "+response.body().get(i).getProfName());
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
                    mAlphabetItem2=new ArrayList<>();
                    List<String>strAlphabets = new ArrayList<>();
                    for(int i=0;i<dataList2.size();i++)
                    {
                        String name = dataList2.get(i).getSubjectInitiality();
                        if(!strAlphabets.contains(name))
                        {
                            strAlphabets.add(name);
                            mAlphabetItem2.add(new AlphabetItem(i,name,false));
                        }
                    }
                }
                manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                mRecyclerView.setLayoutManager(manager); // LayoutManager 등록
                mRecyclerView.setAdapter(new MajorAdapter2(getActivity(), dataList2));  // Adapter 등록
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
