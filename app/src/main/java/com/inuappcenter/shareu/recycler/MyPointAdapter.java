package com.inuappcenter.shareu.recycler;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.my_class.reviewList;
import com.inuappcenter.shareu.my_class.userPointList;
import com.willy.ratingbar.BaseRatingBar;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyPointAdapter extends RecyclerView.Adapter<MyPointAdapter.ViewHolder> {

    private List<userPointList> mitems;
    private Context mContext;

    public MyPointAdapter(Context context,List<userPointList> mitems)
    {
        this.mitems= mitems;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_point,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final userPointList item = mitems.get(position);
        holder.tv_date.setText(item.getPointloadDate());
        holder.tv_title.setText(item.getTitle());
        if(item.getPoint()==5)//업로드
        {
            holder.tv_point.setText("+5p");
            holder.tv_subtitle.setText(" 게시물 등록");
            holder.tv_point.setTextColor(Color.parseColor("#574fba"));
        }
        else if(item.getPoint()==2)//리뷰
        {
            holder.tv_point.setText("+2p");
            holder.tv_subtitle.setText(" 리뷰 등록");
            holder.tv_point.setTextColor(Color.parseColor("#574fba"));
        }
        else if(item.getPoint()==-3)//다운
        {
            holder.tv_point.setText("-3p");
            holder.tv_point.setTextColor(Color.parseColor("#c9c9f4"));
            holder.tv_subtitle.setText(" 게시물 다운");
        }
        else
        {
            String hi;
            if(item.getPoint()>=0)
            {
                hi ="+"+item.getPoint()+"p";
                holder.tv_point.setTextColor(Color.parseColor("#574fba"));
            }
            else
            {
                hi ="-"+item.getPoint()+"p";
                holder.tv_point.setTextColor(Color.parseColor("#c9c9f4"));
            }
            holder.tv_point.setText(hi);
        }
       /* holder.tv_review_date.setText(item.getUploadDate());
        String name = item.getUploadId();
        name=name.substring(0,5);
        name+="***";
        holder.tv_review_name.setText(name);
        holder.tv_review_content.setText(item.getReview());
        holder.before_user_ratingbar.setRating(item.getScore());
        holder.before_user_ratingbar.setScrollable(false);
        holder.before_user_ratingbar.setClickable(false);*/
    }

    @Override
    public int getItemCount() {
        return mitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_date,tv_title,tv_subtitle,tv_point;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_date= itemView.findViewById(R.id.tv_date);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_subtitle=itemView.findViewById(R.id.tv_subtitle);
            tv_point=itemView.findViewById(R.id.tv_point);
        }
    }
    public void setItem(List<userPointList> data)
    {
        mitems=data;
        notifyDataSetChanged();
    }
}

