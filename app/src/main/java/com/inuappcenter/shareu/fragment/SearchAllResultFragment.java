package com.inuappcenter.shareu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.my_class.Notice;
import com.inuappcenter.shareu.my_class.SuperiorLecture;
import com.inuappcenter.shareu.recycler.SuperiorLectureAdapter2;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import me.relex.circleindicator.CircleIndicator;

public class SearchAllResultFragment extends Fragment {
    private ArrayList<Notice> dataList;
    private ViewPager viewPager ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_main_search_all, container, false);
        /*ArrayList<SuperiorLecture> items=new ArrayList<>();
        items.add(new SuperiorLecture(R.drawable.ai,"문학과테마기행 족보",5));
        items.add(new SuperiorLecture(R.drawable.korean,"문테기 족보당",(float)4.8));
        items.add(new SuperiorLecture(R.drawable.ppt,"문테기 족보입니당",(float)4.5));
        items.add(new SuperiorLecture(R.drawable.ps,"2016 문테깅",(float)3.2));
        items.add(new SuperiorLecture(R.drawable.pdf,"문테문테",(float)2.5));
        viewPager = view.findViewById(R.id.viewpager_superior) ;

        SuperiorLectureAdapter2 adapter = new SuperiorLectureAdapter2(items,getActivity());
        viewPager.setAdapter(adapter) ;
        CircleIndicator indicator = view.findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
        adapter.registerDataSetObserver(indicator.getDataSetObserver());
        indicator.createIndicators(5,0);*/
        return view;
    }
}
