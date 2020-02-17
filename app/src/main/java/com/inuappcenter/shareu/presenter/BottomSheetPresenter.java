package com.inuappcenter.shareu.presenter;

import com.inuappcenter.shareu.model.BottomSheetModel;
import com.inuappcenter.shareu.model.OverallModel;
import com.inuappcenter.shareu.my_class.subjectName;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BottomSheetPresenter implements BottomSheetContract.Presenter
{
    BottomSheetContract.View bottomSheetView;
    BottomSheetModel bottomSheetModel;

    public BottomSheetPresenter(BottomSheetContract.View bottomSheetView) {
        this.bottomSheetView = bottomSheetView;
        this.bottomSheetModel = new BottomSheetModel() ;
    }

    @Override
    public void giveSubjData() {

        RetrofitService networkService = RetrofitHelper.create();
        networkService.getSubjectName("").enqueue(new Callback<List<subjectName>>(){
            @Override
            public void onResponse(Call<List<subjectName>> call, Response<List<subjectName>> response)
            {

                if(response.isSuccessful())
                {
                    bottomSheetView.setSubjData(bottomSheetModel.getSubjDatas(response.body()));
                }


            }
            @Override
            public void onFailure(Call<List<subjectName>> call, Throwable t) {

                //Toast.makeText(getApplicationContext(), "실패지렁", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
        //bottomSheetView.setSubjData(bottomSheetModel.getSubjDatas()

    }

    @Override
    public void giveProfData() {
        //bottomSheetView.setProfData(bottomSheetModel.getProfDatas());
    }
}
