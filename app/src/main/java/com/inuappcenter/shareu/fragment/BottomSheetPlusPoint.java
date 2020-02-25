package com.inuappcenter.shareu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialogFragment;
import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.my_interface.OnItemClick;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static com.inuappcenter.shareu.R.id.btn_backpress;

public class BottomSheetPlusPoint extends RoundedBottomSheetDialogFragment {

    ImageView btn_backpress;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_bottomsheet_plus_point,container);
        btn_backpress = view.findViewById(R.id.btn_backpress);
        OnItemClick sibal = (OnItemClick) getActivity();
        btn_backpress.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               sibal.onClick("바텀시트 사라져");
            }
        });

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return view;
    }
}
