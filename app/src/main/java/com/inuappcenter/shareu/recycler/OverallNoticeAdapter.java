package com.inuappcenter.shareu.recycler;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.activity.DetailedNoticeActivity;
import com.inuappcenter.shareu.my_class.Notice;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OverallNoticeAdapter extends RecyclerView.Adapter<OverallNoticeAdapter.ViewHolder>{

    private List<Notice> mitems;
    private Context mContext;

    public OverallNoticeAdapter(Context Context) {
        mitems = new ArrayList<>();
        mContext = Context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_overall_notice,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Notice item = mitems.get(position);
        holder.tv_oveall_notice_title.setText(item.getTitle());
        holder.tv_overall_notice_date.setText(item.getNoticeDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailedNoticeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("key",mitems.get(position).getNoticeKey());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return mitems.size();
    }

    public void setData(List<Notice> notices) {
        mitems = notices;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_oveall_notice_title;
        TextView tv_overall_notice_date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_oveall_notice_title =itemView.findViewById(R.id.tv_overall_notice_title);
            tv_overall_notice_date =itemView.findViewById(R.id.tv_overall_notice_date);
        }
    }
}
