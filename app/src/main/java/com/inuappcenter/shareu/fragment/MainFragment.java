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
import com.inuappcenter.shareu.my_class.Notice;
import com.inuappcenter.shareu.my_class.SuperiorLecture;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {
    private ArrayList<Notice> dataList;
    private ViewPager viewPager ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_main, container, false);
        ArrayList<SuperiorLecture> items=new ArrayList<>();
        items.add(new SuperiorLecture(R.drawable.pdf,"문학과테마기행 족보",5));
        items.add(new SuperiorLecture(R.drawable.excel,"시스템프로그래밍 족보",(float)4.8));
        items.add(new SuperiorLecture(R.drawable.ppt,"생명과학 족보",(float)4.5));
        items.add(new SuperiorLecture(R.drawable.word,"디지털기술과미래 족보",(float)3.2));
        items.add(new SuperiorLecture(R.drawable.pdf,"경영경제수학 족보",(float)2.5));
        viewPager = view.findViewById(R.id.viewpager_superior) ;

        SuperiorLectureAdapter2 adapter = new SuperiorLectureAdapter2(items,getActivity());
        viewPager.setAdapter(adapter) ;
        CircleIndicator indicator = view.findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
        adapter.registerDataSetObserver(indicator.getDataSetObserver());
        indicator.createIndicators(5,0);


        RetrofitService networkService = RetrofitHelper.create();
        networkService.getNotice().enqueue(new Callback<List<Notice>>(){
            @Override
            public void onResponse(Call<List<Notice> > call, Response<List<Notice>> response)
            {

                Log.e("ㅎㅎ",response.body().get(0).getTitle());
                if(response.isSuccessful())
                {
                    dataList = new ArrayList<>();
                    for(int i=0;i<response.body().size();i++)
                    {
                        dataList.add(new Notice((i+1)+". "+response.body().get(i).getTitle(),
                                response.body().get(i).getContent(),
                                response.body().get(i).getNoticeDate(),
                                i+1,
                                R.drawable.rightarrow));
                    }
                }
                RecyclerView recyclerView2=view.findViewById(R.id.recyclerview2_main);
                LinearLayoutManager layoutManager2=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                recyclerView2.setHasFixedSize(true);
                recyclerView2.setLayoutManager(layoutManager2);
                recyclerView2.setAdapter(new NoticeAdapter(getActivity(),dataList));



            }
            @Override
            public void onFailure(Call<List<Notice>> call, Throwable t) {
                Log.e("TAG", t.getMessage());
            }
        });
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
}
