package com.inuappcenter.shareu.recycler;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Rating;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.model.SuperiorLecture;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.app.ComponentActivity;
import androidx.recyclerview.widget.RecyclerView;


public class SuperiorLectureAdapter extends RecyclerView.Adapter<SuperiorLectureAdapter.ViewHolder> {

    private ArrayList<SuperiorLecture> mitems;
    private Context mContext;

    public SuperiorLectureAdapter(Context context, ArrayList<SuperiorLecture> items)
    {
        mitems= items;
        mContext = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_superior,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final SuperiorLecture item = mitems.get(position);
        Drawable drawable = mContext.getResources().getDrawable(item.getImage());

        holder.item_default_superior.setBackground(drawable);
        holder.tv_superior_name.setText(item.getTitle());
        holder.ratingBar.setRating(item.getRating());

    }

    @Override
    public int getItemCount() {
        return this.mitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView item_default_superior;
        TextView tv_superior_name;
        RatingBar ratingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_default_superior = itemView.findViewById(R.id.item_default_superior);
            tv_superior_name = itemView.findViewById(R.id.tv_superior_name);
            ratingBar = (RatingBar)itemView.findViewById(R.id.ratingBar);

        }


    }

}
