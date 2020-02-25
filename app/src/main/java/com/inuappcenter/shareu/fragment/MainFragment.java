package com.inuappcenter.shareu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.activity.FileUploadActivity;
import com.inuappcenter.shareu.activity.MainActivity;
import com.inuappcenter.shareu.activity.MajorActivity;
import com.inuappcenter.shareu.activity.OverallNoticeActivity;
import com.inuappcenter.shareu.activity.ServerFailActivity;
import com.inuappcenter.shareu.my_class.Notice;
import com.inuappcenter.shareu.my_class.SuperiorLecture;
import com.inuappcenter.shareu.presenter.MainContract;
import com.inuappcenter.shareu.presenter.MainPresenter;
import com.inuappcenter.shareu.presenter.MyUploadPresenter;
import com.inuappcenter.shareu.recycler.NoticeAdapter;
import com.inuappcenter.shareu.recycler.SuperiorLectureAdapter2;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment implements MainContract.View {

    private ViewPager viewPager ;
    private MainPresenter mainPresenter = new MainPresenter(this,this);
    private SuperiorLectureAdapter2 superiorLectureAdapter2;
    private NoticeAdapter noticeAdapter;
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_main, container, false);
        superiorLectureAdapter2 = new SuperiorLectureAdapter2(getActivity());
        noticeAdapter = new NoticeAdapter(getActivity());
        init();
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.tv_notice_set_title_more :
                        Intent intent2 = new Intent(getActivity(), OverallNoticeActivity.class);
                        startActivity(intent2);
                        break ;
                }

            }
        } ;

        TextView tv_notice_set_title_more = view.findViewById(R.id.tv_notice_set_title_more);
        tv_notice_set_title_more.setOnClickListener(onClickListener);



        return view;
    }

    void init()
    {
        viewPager = view.findViewById(R.id.viewpager_superior) ;
        viewPager.setAdapter(superiorLectureAdapter2) ;
        CircleIndicator indicator = view.findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
        superiorLectureAdapter2.registerDataSetObserver(indicator.getDataSetObserver());
        indicator.createIndicators(5,0);

        RecyclerView recyclerView2=view.findViewById(R.id.recyclerview2_main);
        LinearLayoutManager layoutManager2=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setAdapter(noticeAdapter);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainPresenter.onCreate();
    }

    @Override
    public void onResume() {
        super.onResume();
        mainPresenter.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mainPresenter.onDestroy();
    }

    @Override
    public void setSuperior(List<SuperiorLecture> value) {
        superiorLectureAdapter2.setItem(value);
    }

    @Override
    public void setNotice(List<Notice> value) {
        noticeAdapter.setItem(value);
    }

    @Override
    public void setInternet() {
        Intent intent = new Intent(getActivity(), ServerFailActivity.class);
        startActivity(intent);
    }
}
