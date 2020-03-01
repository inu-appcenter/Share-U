package com.inuappcenter.shareu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.activity.ServerFailActivity;
import com.inuappcenter.shareu.my_class.Document;
import com.inuappcenter.shareu.my_class.Notice;
import com.inuappcenter.shareu.my_class.SuperiorLecture;
import com.inuappcenter.shareu.presenter.MainContract;
import com.inuappcenter.shareu.presenter.SearchAllResultContract;
import com.inuappcenter.shareu.presenter.SearchAllResultPresenter;
import com.inuappcenter.shareu.recycler.DocumentAdapter;
import com.inuappcenter.shareu.recycler.SuperiorLectureAdapter2;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchAllResultFragment extends Fragment implements SearchAllResultContract.View {
    private ArrayList<SuperiorLecture> dataList;
    private ArrayList<Document> dataList2;
    private ViewPager viewPager ;
    private TextView tv_no_superior;
    private TextView tv_search_result_name;
    private SuperiorLectureAdapter2 superiorLectureAdapter2;
    private DocumentAdapter documentAdapter;
    private View view;
    private EditText etv_search;
    private SearchAllResultContract.Presenter presenter = new SearchAllResultPresenter(this,this);
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_main_search_all, container, false);
        init();
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onCreate();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setText(etv_search.getText()+"");
        tv_search_result_name.setText(etv_search.getText()+"");
        presenter.onResume();


    }
    void init()
    {
        tv_no_superior=view.findViewById(R.id.tv_no_superior);
        etv_search=getActivity().findViewById(R.id.etv_search);
        viewPager = view.findViewById(R.id.viewpager_superior);
        superiorLectureAdapter2 = new SuperiorLectureAdapter2(getActivity());
        viewPager.setAdapter(superiorLectureAdapter2) ;
        tv_search_result_name=view.findViewById(R.id.tv_search_result_name);

        CircleIndicator indicator = view.findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);

        superiorLectureAdapter2.registerDataSetObserver(indicator.getDataSetObserver());
        indicator.createIndicators(0,0);


        documentAdapter = new DocumentAdapter(getActivity());

        RecyclerView recyclerView2=view.findViewById(R.id.recyclerview2_main);
        LinearLayoutManager layoutManager2=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setAdapter(documentAdapter);
    }


    @Override
    public void giveMeSuperior(List<SuperiorLecture> superiorLectures) {

        superiorLectureAdapter2.setData(superiorLectures);


    }


    @Override
    public void setVisible(Boolean flag) {
        if(flag)
        {
            tv_no_superior.setVisibility(View.VISIBLE);
            CircleIndicator indicator = view.findViewById(R.id.indicator);
            indicator.setViewPager(viewPager);
            indicator.createIndicators(0,0);
        }

        else
            tv_no_superior.setVisibility(View.GONE);
    }

    @Override
    public void setInternet() {
        Intent intent = new Intent(getActivity(), ServerFailActivity.class);
        startActivity(intent);
    }

    @Override
    public void giveMeNew(List<Document> documents) {
        documentAdapter.setData(documents);
    }
}
