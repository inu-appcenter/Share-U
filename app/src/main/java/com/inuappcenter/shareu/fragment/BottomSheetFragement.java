package com.inuappcenter.shareu.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialogFragment;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.model.subjectName;
import com.inuappcenter.shareu.recycler.BottomSheetAdapter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BottomSheetFragement extends RoundedBottomSheetDialogFragment {

    private ArrayList<com.inuappcenter.shareu.model.subjectName> dataList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_bottomsheet,container);
        dataList=new ArrayList<>();
        dataList.add(new subjectName("시벌"));
        dataList.add(new subjectName("시이벌"));
        dataList.add(new subjectName("시부럴"));
        dataList.add(new subjectName("하기"));
        dataList.add(new subjectName("쥰나"));
        dataList.add(new subjectName("실타"));
        dataList.add(new subjectName("레알"));
        dataList.add(new subjectName("씩씩이는"));
        dataList.add(new subjectName("세상에서 제일"));
        dataList.add(new subjectName("귀요미이다 키키키키킥"));
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_bottomsheet);
        LinearLayoutManager manager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager); // LayoutManager 등록
        recyclerView.setAdapter(new BottomSheetAdapter(dataList,getActivity()));  // Adapter 등록
        return view;
    }
}
