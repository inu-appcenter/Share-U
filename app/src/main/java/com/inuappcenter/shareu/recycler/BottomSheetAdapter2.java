package com.inuappcenter.shareu.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.my_class.profName;
import com.inuappcenter.shareu.my_class.subjectName;
import com.inuappcenter.shareu.my_interface.OnItemClick;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BottomSheetAdapter2 extends RecyclerView.Adapter<BottomSheetAdapter2.ViewHolder> {

    private ArrayList<profName> mitems;
    private Context mContext;
    private OnItemClick mCallback;

    public BottomSheetAdapter2(ArrayList<profName> mitems, Context mContext, OnItemClick mCallback) {
        this.mitems = mitems;
        this.mContext = mContext;
        this.mCallback = mCallback;
    }

    @NonNull
    @Override
    public BottomSheetAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_bottomsheet,parent,false);
        return new BottomSheetAdapter2.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final profName item = mitems.get(position);
        holder.tv_bottomsheet.setText(item.getProfName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.onClick2(item.getProfName());
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
