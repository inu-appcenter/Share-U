package com.inuappcenter.shareu.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;
import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialog;
import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialogFragment;
import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.activity.ServerFailActivity;
import com.inuappcenter.shareu.my_class.profName;
import com.inuappcenter.shareu.my_class.subjectName;
import com.inuappcenter.shareu.my_interface.OnItemClick;
import com.inuappcenter.shareu.recycler.BottomSheetAdapter;
import com.inuappcenter.shareu.recycler.BottomSheetAdapter2;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BottomSheetFragment2 extends RoundedBottomSheetDialogFragment {
    private ArrayList<profName> dataList;
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private RetrofitService networkService;
    private EditText etv_search;
    private ImageButton  etv_search_click;
    private TextView tv_search_please;
    private TextView edtv_select_subject;
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
        tv_search_please.setText("교수명을 검색해주세요 :)");
        edtv_select_subject=getActivity().findViewById(R.id.edtv_select_subject);

    }

    @Override
    public void onResume() {
        super.onResume();
        etv_search_click.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(edtv_select_subject.getText().length()==0)
                {
                    tv_search_please.setVisibility(View.VISIBLE);
                    tv_search_please.setText("과목명부터 검색해주세요 :(");
                }
                else
                {
                    networkService = RetrofitHelper.create();
                    networkService.getProfName(edtv_select_subject.getText()+"",etv_search.getText()+"").enqueue(new Callback<List<profName>>(){
                        @Override
                        public void onResponse(Call<List<profName> > call, Response<List<profName>> response)
                        {
                            if(response.body().size()==0)
                            {
                                tv_search_please.setVisibility(View.VISIBLE);
                                tv_search_please.setText("검색 결과가 없습니다 :(");
                            }
                            else
                            {
                                tv_search_please.setVisibility(View.GONE);
                                if(response.isSuccessful())
                                {
                                    dataList=new ArrayList<>();
                                    for(int i=0;i<response.body().size();i++)
                                    {
                                        dataList.add(new profName(response.body().get(i).getProfName()));
                                    }

                                }
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setLayoutManager(manager); // LayoutManager 등록
                                recyclerView.setAdapter(new BottomSheetAdapter2(dataList,getActivity(),(OnItemClick)(getActivity())));  // Adapter 등록
                            }

                        }
                        @Override
                        public void onFailure(Call<List<profName>> call, Throwable t)
                        {
                            Intent intent = new Intent(getActivity(), ServerFailActivity.class);
                            startActivity(intent);
                        }
                    });

                }

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
