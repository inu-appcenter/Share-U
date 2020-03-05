package com.inuappcenter.shareu.fragment;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;
import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialogFragment;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.activity.ServerFailActivity;
import com.inuappcenter.shareu.my_class.profName;
import com.inuappcenter.shareu.my_class.subjectName;
import com.inuappcenter.shareu.my_interface.OnItemClick;
import com.inuappcenter.shareu.recycler.BottomSheetAdapter;
import com.inuappcenter.shareu.recycler.BottomSheetAdapter2;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BottomSheetFragement extends RoundedBottomSheetDialogFragment{


    private ArrayList<subjectName> dataList;
    private RecyclerView recyclerView;
    private  LinearLayoutManager manager;
    private RetrofitService networkService;
    private EditText etv_search;
    private ImageButton  etv_search_click;
    private TSnackbar snackbar;
    private TextView tv_search_please;
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_bottomsheet,container);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        init(view);
        return view;
    }
    public void init(View view)
    {
        tv_search_please = view.findViewById(R.id.tv_search_please);
        recyclerView = view.findViewById(R.id.recyclerview_bottomsheet);
        manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        etv_search = view.findViewById(R.id.etv_search);
        etv_search_click = view.findViewById(R.id.etv_search_click);
    }

    @Override
    public void onResume() {
        super.onResume();
        etv_search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //Enter key Action
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    giveMeList();
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE); imm.hideSoftInputFromWindow( etv_search.getWindowToken(), 0);

                    return true;
                }
                return false;
            }
        });
        etv_search_click.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                giveMeList();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE); imm.hideSoftInputFromWindow( etv_search.getWindowToken(), 0);
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

    void giveMeList()
    {
        if(etv_search.getText().length()==0)
        {
            tv_search_please.setVisibility(View.VISIBLE);
            tv_search_please.setText("과목을 한 글자 이상 검색해주세요 :(");;

        }
        else
        {
            networkService = RetrofitHelper.create();
            networkService.getSubjectName(etv_search.getText()+"").enqueue(new Callback<List<subjectName>>(){
                @Override
                public void onResponse(Call<List<subjectName> > call, Response<List<subjectName>> response)
                {
                    if(response.isSuccessful())
                    {
                        if(response.isSuccessful())
                        {
                            dataList=new ArrayList<>();
                            for(int i=0;i<response.body().size();i++)
                            {
                                dataList.add(new subjectName(response.body().get(i).getMajorName()));
                            }

                        }
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(manager); // LayoutManager 등록
                        recyclerView.setAdapter(new BottomSheetAdapter(dataList,getActivity(),(OnItemClick)(getActivity())));  // Adapter 등록
                        if(response.body().size()==0)
                        {
                            tv_search_please.setVisibility(View.VISIBLE);
                            tv_search_please.setText("검색 결과가 없습니다 :(");
                        }
                        else
                        {
                            tv_search_please.setVisibility(View.GONE);
                        }

                    }


                }
                @Override
                public void onFailure(Call<List<subjectName>> call, Throwable t) {

                }
            });


        }

    }


}
