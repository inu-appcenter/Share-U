package com.inuappcenter.shareu.recycler;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.activity.DetailedNoticeActivity;
import com.inuappcenter.shareu.my_class.Notice;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {

    private List<Notice> mitems;
    private Context mContext;

    public NoticeAdapter(Context context)
    {
        mitems= new ArrayList<>();
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_notice,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Notice item = mitems.get(position);
        Drawable drawable = mContext.getResources().getDrawable(item.getImage());

        holder.tv_notice_title.setText(item.getTitle());
        holder.imv_notice_right_arrow.setBackground(drawable);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(mContext,"sibal",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(mContext, DetailedNoticeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("key",mitems.get(position).getNoticeKey());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_notice_title;
        ImageView imv_notice_right_arrow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_notice_title = itemView.findViewById(R.id.tv_notice_title);
            imv_notice_right_arrow = itemView.findViewById(R.id.imv_notice_right_arrow);
        }
    }
    public void setItem(List<Notice> data)
    {
        mitems=data;
        notifyDataSetChanged();
    }
}
