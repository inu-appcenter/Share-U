package com.inuappcenter.shareu.recycler;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.activity.DetailedFileActivity;
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
        holder.superior_ratingbar.setClickable(false);
        holder.superior_ratingbar.setScrollable(false);
        if(mitems.get(position).getExtension().equals("PPT") || mitems.get(position).getExtension().equals("PPTX")||
                mitems.get(position).getExtension().equals("ppt") || mitems.get(position).getExtension().equals("pptx")
        )
        {
            holder.img_extension.setImageResource(R.drawable.ppt);
        }
        else if(mitems.get(position).getExtension().equals("HWP") ||mitems.get(position).getExtension().equals("hwp") )
        {
            holder.img_extension.setImageResource(R.drawable.korean);
        }
        else if(mitems.get(position).getExtension().equals("DOC") || mitems.get(position).getExtension().equals("DOCX")
                ||mitems.get(position).getExtension().equals("doc") || mitems.get(position).getExtension().equals("docx")
        )
        {
            holder.img_extension.setImageResource(R.drawable.word);
        }
        else if(mitems.get(position).getExtension().equals("AI")
                ||mitems.get(position).getExtension().equals("ai")
        )
        {
            holder.img_extension.setImageResource(R.drawable.ai);
        }
        else if(mitems.get(position).getExtension().equals("PS")
                ||mitems.get(position).getExtension().equals("ps")
        )
        {
            holder.img_extension.setImageResource(R.drawable.ps);
        }

        else if(mitems.get(position).getExtension().equals("JPEG") || mitems.get(position).getExtension().equals("JPG")||
                mitems.get(position).getExtension().equals("jpeg") || mitems.get(position).getExtension().equals("jpg")
        )
        {
            holder.img_extension.setImageResource(R.drawable.jpeg);
        }
        else if(mitems.get(position).getExtension().equals("PNG") || mitems.get(position).getExtension().equals("png") )
        {
            holder.img_extension.setImageResource(R.drawable.png);
        }
        else if(mitems.get(position).getExtension().equals("XLS")||mitems.get(position).getExtension().equals("XLSX")||
                mitems.get(position).getExtension().equals("XLSM") || mitems.get(position).getExtension().equals("CSV")||
                mitems.get(position).getExtension().equals("xls")||mitems.get(position).getExtension().equals("xlsx")||
                mitems.get(position).getExtension().equals("xlsm") || mitems.get(position).getExtension().equals("csv")
        )
        {
            holder.img_extension.setImageResource(R.drawable.excel);
        }
        else if(mitems.get(position).getExtension().equals("MP3")||mitems.get(position).getExtension().equals("mp3"))
        {
            holder.img_extension.setImageResource(R.drawable.mp3);
        }
        else if(mitems.get(position).getExtension().equals("ZIP") ||mitems.get(position).getExtension().equals("zip")  )
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
                Intent intent = new Intent(mContext.getApplicationContext(), DetailedFileActivity.class);
                intent.putExtra("key",mitems.get(position).getDocumentKey());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
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
