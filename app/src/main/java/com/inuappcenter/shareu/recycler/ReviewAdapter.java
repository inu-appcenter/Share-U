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
import com.inuappcenter.shareu.activity.DetailedFileActivity;
import com.inuappcenter.shareu.activity.DetailedNoticeActivity;
import com.inuappcenter.shareu.my_class.Notice;
import com.inuappcenter.shareu.my_class.reviewList;
import com.willy.ratingbar.BaseRatingBar;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private List<reviewList> mitems;
    private Context mContext;

    public ReviewAdapter(Context context,List<reviewList> mitems)
    {
        this.mitems= mitems;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_review,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final reviewList item = mitems.get(position);

        holder.tv_review_date.setText(item.getUploadDate());
        String name = item.getUploadId();
        name=name.substring(0,5);
        name+="***";
        holder.tv_review_name.setText(name);
        holder.tv_review_content.setText(item.getReview());
        holder.before_user_ratingbar.setRating(item.getScore());
        holder.before_user_ratingbar.setScrollable(false);
        holder.before_user_ratingbar.setClickable(false);
    }

    @Override
    public int getItemCount() {
        return mitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_review_date,tv_review_name,tv_review_content;
        BaseRatingBar before_user_ratingbar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_review_date = itemView.findViewById(R.id.tv_review_date);
            tv_review_name=itemView.findViewById(R.id.tv_review_name);
            tv_review_content=itemView.findViewById(R.id.tv_review_content);
            before_user_ratingbar = itemView.findViewById(R.id.before_user_ratingbar);
        }
    }
    public void setItem(List<reviewList> data)
    {
        mitems=data;
        notifyDataSetChanged();
    }
}
