package com.inuappcenter.shareu.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.activity.MainActivity;
import com.inuappcenter.shareu.activity.MajorActivity;
import com.inuappcenter.shareu.activity.ServerFailActivity;
import com.inuappcenter.shareu.my_class.Code;
import com.inuappcenter.shareu.my_class.Major;
import com.inuappcenter.shareu.my_interface.OnItemClick;
import com.inuappcenter.shareu.recycler.MajorAdapter;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;
import com.viethoa.RecyclerViewFastScroller;
import com.viethoa.models.AlphabetItem;

import java.time.Duration;
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

public class MajorFragment extends Fragment  {

    private RecyclerViewFastScroller fastScroller;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager manager;
    private ArrayList<Major> dataList;
    private View my_view;
    private TextView tv_no_search;
    private EditText etv_search;
    private ImageView etv_search_click;
    TextView tv_my_major;

    private List<AlphabetItem> mAlphabetItems;
    private List<AlphabetItem> mAlphabetItem2 ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        my_view = inflater.inflate(R.layout.layout_recyclerview_select_major, container, false);
        fastScroller=my_view.findViewById(R.id.fast_scroller);
        mRecyclerView=my_view.findViewById(R.id.recyclerview_select_major);
        return my_view;
    }

    @Override
    public void onResume() {
        super.onResume();
        etv_search=getActivity().findViewById(R.id.etv_search);
        etv_search_click=getActivity().findViewById(R.id.etv_search_click);
        tv_no_search = getActivity().findViewById(R.id.tv_no_search);
        tv_my_major=getActivity().findViewById(R.id.tv_my_major);
        tv_my_major.setText("학과명 검색");
        tv_no_search.setVisibility(View.GONE);
        etv_search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //Enter key Action
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    listen();
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE); imm.hideSoftInputFromWindow( etv_search.getWindowToken(), 0);

                    return true;
                }
                return false;
            }
        });
        RetrofitService networkService = RetrofitHelper.create();
        networkService.getMajorList().enqueue(new Callback<List<Major>>() {
            @Override
            public void onResponse(Call<List<Major>> call, Response<List<Major>> response) {

                if (response.isSuccessful()) {
                    tv_no_search.setVisibility(View.GONE);
                    dataList = new ArrayList<>();
                    String flag = "?";

                    for (int i = 0; i < response.body().size(); i++) {
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
                mRecyclerView.setAdapter(new MajorAdapter(getActivity(), dataList));  // Adapter 등록
                fastScroller.setRecyclerView(mRecyclerView);
                fastScroller.setUpAlphabet(mAlphabetItems);
                if(dataList.size()==0)
                    tv_no_search.setVisibility(View.VISIBLE);
                else
                    tv_no_search.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Major>> call, Throwable t) {

            }
        });

        etv_search_click.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                listen();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE); imm.hideSoftInputFromWindow( etv_search.getWindowToken(), 0);
            }
        });
    }


    /*@Override
    public void onClick(String value) {
        //통신
        //search click
        //Toast.makeText(getActivity(),value+"", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onClick2(String value) {

    }*/

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(my_view!=null){
            ViewGroup parent = (ViewGroup)my_view.getParent();
            if(parent!=null){
                parent.removeView(my_view);
            }
        }

    }
    void listen()
    {
        mAlphabetItem2 = new ArrayList<>();
        RetrofitService networkService = RetrofitHelper.create();
        networkService.categoryMajor(etv_search.getText()+"").enqueue(new Callback<List<Major>>() {
            @Override
            public void onResponse(Call<List<Major>> call, Response<List<Major>> response) {
                if (response.isSuccessful()) {
                    dataList = new ArrayList<>();
                    String flag = "?";

                    for (int i = 0; i < response.body().size(); i++) {
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

                    mAlphabetItem2 = new ArrayList<>();
                    List<String>strAlphabets = new ArrayList<>();
                    for(int i=0;i<dataList.size();i++)
                    {
                        String name = dataList.get(i).getThird();
                        if(!strAlphabets.contains(name))
                        {
                            strAlphabets.add(name);
                            mAlphabetItem2.add(new AlphabetItem(i,name,false));
                        }
                    }
                }
                manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                mRecyclerView.setLayoutManager(manager); // LayoutManager 등록
                mRecyclerView.setAdapter(new MajorAdapter(getActivity(), dataList));  // Adapter 등록
                fastScroller.setRecyclerView(mRecyclerView);
                fastScroller.setUpAlphabet(mAlphabetItem2);
                if(dataList.size()==0)
                    tv_no_search.setVisibility(View.VISIBLE);
                else
                    tv_no_search.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<List<Major>> call, Throwable t) {

            }
        });
    }

}
