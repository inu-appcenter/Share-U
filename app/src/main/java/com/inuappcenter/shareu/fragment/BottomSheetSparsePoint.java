package com.inuappcenter.shareu.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialogFragment;
import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.my_class.SumPoint;
import com.inuappcenter.shareu.my_class.TokenManager;
import com.inuappcenter.shareu.my_interface.OnItemClick;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BottomSheetSparsePoint extends RoundedBottomSheetDialogFragment {

    ImageView btn_backpress;
    TextView tv_numeric_point;
    TextView tv_now_my_point;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_bottomsheet_sparse_point,container);
        btn_backpress = view.findViewById(R.id.btn_backpress);
        tv_numeric_point=view.findViewById(R.id.tv_numeric_point);
        tv_now_my_point=view.findViewById(R.id.tv_now_my_point);
        OnItemClick sibal = (OnItemClick) getActivity();
        btn_backpress.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                sibal.onClick("바텀시트 사라져");
            }
        });
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        giveMyPoint();
        return view;
    }
    void giveMyPoint()
    {
        TokenManager tm = TokenManager.getInstance();
        String token = tm.getToken(getActivity());
        Log.e("읭",token);
        RetrofitService networkService = RetrofitHelper.create();
        networkService.sumpoint(token).enqueue(new Callback<List<SumPoint>>(){
            @Override
            public void onResponse(Call<List<SumPoint>> call, Response<List<SumPoint>> response)
            {
                if(response.isSuccessful())
                {
                    tv_numeric_point.setText(3+"");
                    tv_now_my_point.setText(response.body().get(0).getPoint()+"");
                }

            }
            @Override
            public void onFailure(Call<List<SumPoint>> call, Throwable t) {

            }
        });
    }
}
