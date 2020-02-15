package com.inuappcenter.shareu.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialogFragment;
import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.activity.FileUploadActivity;
import com.inuappcenter.shareu.activity.MajorActivity;
import com.inuappcenter.shareu.activity.OverallNoticeActivity;
import com.inuappcenter.shareu.my_class.profName;
import com.inuappcenter.shareu.my_class.subjectName;
import com.inuappcenter.shareu.my_interface.OnItemClick;
import com.inuappcenter.shareu.presenter.BottomSheetContract;
import com.inuappcenter.shareu.presenter.BottomSheetPresenter;
import com.inuappcenter.shareu.presenter.OverallNoticeContract;
import com.inuappcenter.shareu.recycler.BottomSheetAdapter;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BottomSheetFragement extends RoundedBottomSheetDialogFragment implements BottomSheetContract.View {


    private ArrayList<com.inuappcenter.shareu.my_class.subjectName> dataList;
    private ArrayList<com.inuappcenter.shareu.my_class.profName> dataList2;
    private RecyclerView recyclerView;
    private  LinearLayoutManager manager;
    private ImageButton img_btn_file_upload2;
    private ImageButton img_btn_file_upload3;
    private RetrofitService networkService;
    private BottomSheetContract.Presenter bottomsheetpresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_bottomsheet,container);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        bottomsheetpresenter = new BottomSheetPresenter(this);
        /*dataList=new ArrayList<>();
        dataList.add(new subjectName("시벌"));
        dataList.add(new subjectName("시이벌"));
        dataList.add(new subjectName("시부럴"));
        dataList.add(new subjectName("하기"));
        dataList.add(new subjectName("쥰나"));
        dataList.add(new subjectName("실타"));
        dataList.add(new subjectName("레알"));
        dataList.add(new subjectName("씩씩이는"));
        dataList.add(new subjectName("세상에서 제일"));
        dataList.add(new subjectName("귀요미이다 키키키키킥"));*/

        //init(view);
        recyclerView = view.findViewById(R.id.recyclerview_bottomsheet);
        manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        //setListener(view);
        //setRetrofit();
        bottomsheetpresenter.giveSubjData();
        //bottomsheetpresenter.giveProfData();
        return view;
    }
    public void init(View view)
    {
        img_btn_file_upload2 = view.findViewById(R.id.img_btn_file_upload2);
        img_btn_file_upload3 = view.findViewById(R.id.img_btn_file_upload3);
        recyclerView = view.findViewById(R.id.recyclerview_bottomsheet);
        manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    }
    /*public void setListener(View view)
    {
        View.OnClickListener onClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.img_btn_file_upload2 :
                        bottomsheetpresenter.giveSubjData();

                        break ;
                    case R.id.img_btn_file_upload3 :

                        break ;

                }
            }
        };
        img_btn_file_upload2.setOnClickListener(onClickListener);
        img_btn_file_upload3.setOnClickListener(onClickListener);
    }*/
   /* public void setRetrofit(){
        networkService = RetrofitHelper.create();
        networkService.getSubjectName("").enqueue(new Callback<List<subjectName>>(){
            @Override
            public void onResponse(Call<List<subjectName> > call, Response<List<subjectName>> response)
            {
                if(response.isSuccessful())
                {
                    dataList=new ArrayList<>();
                    for(int i=0;i<response.body().size();i++)
                    {
                        dataList.add(new subjectName(response.body().get(i).getSubjectName()));
                    }

                }

                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(manager); // LayoutManager 등록
                recyclerView.setAdapter(new BottomSheetAdapter(dataList,getActivity(),(OnItemClick)(getActivity())));  // Adapter 등록
            }
            @Override
            public void onFailure(Call<List<subjectName>> call, Throwable t) {

                //Toast.makeText(getApplicationContext(), "실패지렁", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }*/

    @Override
    public void setSubjData(ArrayList<subjectName> datas) {
        dataList=datas;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager); // LayoutManager 등록
        recyclerView.setAdapter(new BottomSheetAdapter(dataList,getActivity(),(OnItemClick)(getActivity())));  // Adapter 등록
    }

    @Override
    public void setProfData(ArrayList<profName> datas) {

    }
}
