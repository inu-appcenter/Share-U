package com.inuappcenter.shareu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.activity.SearchSuccessedActivity;
import com.inuappcenter.shareu.activity.ServerFailActivity;
import com.inuappcenter.shareu.my_class.Document;
import com.inuappcenter.shareu.my_class.SuperiorLecture;
import com.inuappcenter.shareu.presenter.SearchAllResultContract;
import com.inuappcenter.shareu.presenter.SearchAllResultPresenter;
import com.inuappcenter.shareu.recycler.DocumentAdapter;
import com.inuappcenter.shareu.recycler.SuperiorLectureAdapter2;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import me.relex.circleindicator.CircleIndicator;

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
    private TextView tv_search_result_more;

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
        tv_search_result_name.setText("\""+etv_search.getText()+"\"");
        presenter.onResume();
        tv_search_result_more.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //더보기 넘어가기
                Intent intent = new Intent(getActivity(), SearchSuccessedActivity.class);
                intent.putExtra("name",tv_search_result_name.getText()+"");
                startActivity(intent);
            }
        });


    }
    void init()
    {
        tv_no_superior=view.findViewById(R.id.tv_no_superior);
        etv_search=getActivity().findViewById(R.id.etv_search);
        viewPager = view.findViewById(R.id.viewpager_superior);
        superiorLectureAdapter2 = new SuperiorLectureAdapter2(getActivity());
        viewPager.setAdapter(superiorLectureAdapter2) ;
        tv_search_result_name=view.findViewById(R.id.tv_search_result_name);
        tv_search_result_more=view.findViewById(R.id.tv_search_result_more);

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
        getActivity().startActivity(intent);
    }

    @Override
    public void giveMeNew(List<Document> documents) {
        documentAdapter.setData(documents);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    /*이 에러도 프래그먼트를 사용하다 보면 접하기 쉬운데, 주로 여러 프래그먼트를 전환하는 구성을 사용할 때 발생하기 쉽습니다.
    (FragmentTransaction.replace(), add(), remove() 등을 사용하여 한 액티비티 내에서 선택한 메뉴에 따라 다른 프래그먼트를 보여주는 경우가 대표적인 예)
위에서 본 두 사례의 원인은 모두 '프래그먼트를 구성하는 뷰(View)를 중복해서 레이아웃 내에 추가하려 했기 때문' 입니다.
프래그먼트가 화면에 표시되는 과정에서, 프래그먼트가 표시되는 뷰(컨테이너 뷰)에 프래그먼트의 뷰가 추가되는데,
이 상태에서 다시 프래그먼트를 추가하면 뷰가 중복되어 오류가 발생하는 것입니다.
이를 방지하려면, 프래그먼트가 화면에서 사라질 때 프래그먼트의 뷰를 컨테이너 뷰에서 제거해주면 됩니다.
일반적으로 다음과 프래그먼트의 코드 내에 onDestroyView()를 다음과 같이 오버라이드 하면 됩니다.
프래그먼트가 화면에서 사라질 때, 컨테이너 뷰(parent)에서 프래그먼트 뷰(v)를 제거하여 이후에 중복 추가되는 것을 방지하는 원리입니다.

    * */
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
