package com.inuappcenter.shareu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialogFragment;
import com.inuappcenter.shareu.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BottomSheetMinusPoint extends RoundedBottomSheetDialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_bottomsheet_minus_point,container);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return view;
    }
}
