package com.inuappcenter.shareu.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.my_class.subjectName;
import com.inuappcenter.shareu.my_interface.OnItemClick;

import java.util.ArrayList;

import androidx.annotation.NonNull;
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
        holder.tv_bottomsheet.setText(item.getMajorName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.onClick(item.getMajorName());
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
