package com.inuappcenter.shareu.recycler;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.model.Notice;
import com.inuappcenter.shareu.model.SuperiorLecture;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {

    private ArrayList<Notice> mitems;
    private Context mContext;

    public NoticeAdapter(Context context, ArrayList<Notice> items)
    {
        mitems= items;
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
        holder.btn_notice_right_arrow.setBackground(drawable);
    }

    @Override
    public int getItemCount() {
        return mitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_notice_title;
        Button btn_notice_right_arrow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_notice_title = itemView.findViewById(R.id.tv_notice_title);
            btn_notice_right_arrow = itemView.findViewById(R.id.btn_notice_right_arrow);
        }
    }
}
