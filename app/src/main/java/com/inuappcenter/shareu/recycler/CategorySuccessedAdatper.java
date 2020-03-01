package com.inuappcenter.shareu.recycler;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.my_class.Document;
import com.willy.ratingbar.BaseRatingBar;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategorySuccessedAdatper extends RecyclerView.Adapter<CategorySuccessedAdatper.ViewHolder> {

    private List<Document> mitems;
    private Context mContext;

    public CategorySuccessedAdatper(List<Document> items,Context context)
    {
        this.mitems= items;
        this.mContext = context;
    }

    @NonNull
    @Override
    public CategorySuccessedAdatper.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_category_successed,parent,false);
        return new CategorySuccessedAdatper.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategorySuccessedAdatper.ViewHolder holder, int position) {
        final Document item = mitems.get(position);

        holder.tv_title.setText(item.getTitle());
        holder.tv_date.setText(item.getUploadDate());
        holder.tv_profName.setText(item.getProfName());
        holder.superior_ratingbar.setRating(item.getAvg_score());
        if(item.getExtension().equals("PPT") || item.getExtension().equals("PPTX"))
        {
            holder.img_extension.setImageResource(R.drawable.ppt);
        }
        else if(item.getExtension().equals("HWP"))
        {
            holder.img_extension.setImageResource(R.drawable.korean);
        }
        else if(item.getExtension().equals("DOC") || item.getExtension().equals("DOCX"))
        {
            holder.img_extension.setImageResource(R.drawable.word);
        }
        else if(item.getExtension().equals("AI"))
        {
            holder.img_extension.setImageResource(R.drawable.ai);
        }
        else if(item.getExtension().equals("PS"))
        {
            holder.img_extension.setImageResource(R.drawable.ps);
        }

        else if(item.getExtension().equals("JPEG") || item.getExtension().equals("JPG"))
        {
            holder.img_extension.setImageResource(R.drawable.jpeg);
        }
        else if(item.getExtension().equals("PNG"))
        {
            holder.img_extension.setImageResource(R.drawable.png);
        }
        else if(item.getExtension().equals("XLS")||item.getExtension().equals("XLSX")||
                mitems.get(position).getExtension().equals("XLSM") || mitems.get(position).getExtension().equals("CSV"))
        {
            holder.img_extension.setImageResource(R.drawable.excel);
        }
        else if(item.getExtension().equals("MP3"))
        {
            holder.img_extension.setImageResource(R.drawable.mp3);
        }
        else if(item.getExtension().equals("ZIP"))
        {
            holder.img_extension.setImageResource(R.drawable.zip);
        }
        else
        {
            holder.img_extension.setImageResource(R.drawable.file);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_title;
        TextView tv_date;
        TextView tv_profName;
        BaseRatingBar superior_ratingbar;
        ImageView img_extension;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_date = itemView.findViewById(R.id.tv_date);
            superior_ratingbar = itemView.findViewById(R.id.superior_ratingbar);
            img_extension=itemView.findViewById(R.id.img_extension);
            tv_profName=itemView.findViewById(R.id.tv_profName);

        }
    }

}
