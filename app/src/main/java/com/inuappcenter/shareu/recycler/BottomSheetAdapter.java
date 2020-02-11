package com.inuappcenter.shareu.recycler;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.activity.FileUploadActivity;
import com.inuappcenter.shareu.activity.MajorActivity;
import com.inuappcenter.shareu.fragment.MajorFragment2;
import com.inuappcenter.shareu.model.Code;
import com.inuappcenter.shareu.model.Major;
import com.inuappcenter.shareu.model.SuperiorLecture;
import com.inuappcenter.shareu.model.subjectName;
import com.inuappcenter.shareu.my_interface.OnItemClick;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class BottomSheetAdapter extends RecyclerView.Adapter<BottomSheetAdapter.ViewHolder>{

    private ArrayList<subjectName> mitems;
    private Context mContext;
    private OnItemClick mCallback;
    public BottomSheetAdapter(ArrayList<subjectName> mitems, Context mContext,OnItemClick listener) {
        this.mitems = mitems;
        this.mContext = mContext;
        this.mCallback = listener;
    }

    @NonNull
    @Override
    public BottomSheetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_bottomsheet,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BottomSheetAdapter.ViewHolder holder, int position) {
        final subjectName item = mitems.get(position);
        holder.tv_bottomsheet.setText(item.getSubjectName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.onClick(item.getSubjectName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_bottomsheet;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_bottomsheet = itemView.findViewById(R.id.tv_bottomsheet);
        }
    }
}
