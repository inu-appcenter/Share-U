package com.inuappcenter.shareu.recycler;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.activity.DetailedFileActivity;
import com.inuappcenter.shareu.activity.MyDetailedUploadActivtity;
import com.inuappcenter.shareu.activity.UploadedActivity;
import com.inuappcenter.shareu.my_class.Fuck;
import com.inuappcenter.shareu.my_class.Major;
import com.inuappcenter.shareu.my_class.MyUpload;
import com.inuappcenter.shareu.my_class.Notice;
import com.inuappcenter.shareu.my_class.TokenManager;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyUploadAdapter extends RecyclerView.Adapter<MyUploadAdapter.ViewHolder> {
    private Context mContext;
    private List<MyUpload> mitems;
    private int key;

    public MyUploadAdapter(Context mContext) {
        this.mContext = mContext;
        this.mitems = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyUploadAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_my_upload,parent,false);
        return new MyUploadAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyUploadAdapter.ViewHolder holder, int position) {
        final MyUpload item = mitems.get(position);
        if(mitems.get(position).getExtension().equals("PPT") || mitems.get(position).getExtension().equals("PPTX")||
                mitems.get(position).getExtension().equals("ppt") || mitems.get(position).getExtension().equals("pptx")
        )
        {
            holder.img_my_upload.setImageResource(R.drawable.ppt);
        }
        else if(mitems.get(position).getExtension().equals("HWP") ||mitems.get(position).getExtension().equals("hwp") )
        {
            holder.img_my_upload.setImageResource(R.drawable.korean);
        }
        else if(mitems.get(position).getExtension().equals("DOC") || mitems.get(position).getExtension().equals("DOCX")
                ||mitems.get(position).getExtension().equals("doc") || mitems.get(position).getExtension().equals("docx")
        )
        {
            holder.img_my_upload.setImageResource(R.drawable.word);
        }
        else if(mitems.get(position).getExtension().equals("AI")
                ||mitems.get(position).getExtension().equals("ai")
        )
        {
            holder.img_my_upload.setImageResource(R.drawable.ai);
        }
        else if(mitems.get(position).getExtension().equals("PS")
                ||mitems.get(position).getExtension().equals("ps")
        )
        {
            holder.img_my_upload.setImageResource(R.drawable.ps);
        }

        else if(mitems.get(position).getExtension().equals("JPEG") || mitems.get(position).getExtension().equals("JPG")||
                mitems.get(position).getExtension().equals("jpeg") || mitems.get(position).getExtension().equals("jpg")
        )
        {
            holder.img_my_upload.setImageResource(R.drawable.jpeg);
        }
        else if(mitems.get(position).getExtension().equals("PNG") || mitems.get(position).getExtension().equals("png") )
        {
            holder.img_my_upload.setImageResource(R.drawable.png);
        }
        else if(mitems.get(position).getExtension().equals("XLS")||mitems.get(position).getExtension().equals("XLSX")||
                mitems.get(position).getExtension().equals("XLSM") || mitems.get(position).getExtension().equals("CSV")||
                mitems.get(position).getExtension().equals("xls")||mitems.get(position).getExtension().equals("xlsx")||
                mitems.get(position).getExtension().equals("xlsm") || mitems.get(position).getExtension().equals("csv")
        )
        {
            holder.img_my_upload.setImageResource(R.drawable.excel);
        }
        else if(mitems.get(position).getExtension().equals("MP3")||mitems.get(position).getExtension().equals("mp3"))
        {
            holder.img_my_upload.setImageResource(R.drawable.mp3);
        }
        else if(mitems.get(position).getExtension().equals("ZIP") ||mitems.get(position).getExtension().equals("zip")  )
        {
            holder.img_my_upload.setImageResource(R.drawable.zip);
        }
        else
        {
            holder.img_my_upload.setImageResource(R.drawable.file);
        }
        holder.tv_my_upload_title.setText(item.getTitle());
        holder.tv_my_upload_date.setText(item.getUploadDate());
        holder.btn_my_upload.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(mContext,holder.btn_my_upload);
                popupMenu.inflate(R.menu.popup);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.popup_modify:
                                Intent intent = new Intent(mContext.getApplicationContext(), MyDetailedUploadActivtity.class);
                                key = mitems.get(position).getDocumentKey();
                                intent.putExtra("key",key);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                mContext.startActivity(intent);
                                break;
                            case R.id.popup_delete:
                                key = mitems.get(position).getDocumentKey();
                                tellServer(key);
                                mitems.remove(position);
                                notifyItemRemoved(position);
                                notifyItemChanged(position,mitems.size());
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext.getApplicationContext(), DetailedFileActivity.class);
                intent.putExtra("key",mitems.get(position).getDocumentKey());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.mitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img_my_upload;
        TextView tv_my_upload_date;
        TextView tv_my_upload_title;
        ImageButton btn_my_upload;

        //요기까지했지롱
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_my_upload=itemView.findViewById(R.id.img_my_upload);
            tv_my_upload_date = itemView.findViewById(R.id.tv_my_upload_date);
            tv_my_upload_title = itemView.findViewById(R.id.tv_my_upload_title);
            btn_my_upload=itemView.findViewById(R.id.btn_my_upload);
        }

    }
    public void setData(List<MyUpload> notices) {
        mitems = notices;
        notifyDataSetChanged();
    }
    void tellServer(int key)
    {

        TokenManager tm = TokenManager.getInstance();
        String token = tm.getToken(mContext.getApplicationContext());
        RetrofitService networkService = RetrofitHelper.create();
        networkService.delete_doc(key,token).enqueue(new Callback<Fuck>() {

            @Override
            public void onResponse(Call<Fuck> call, Response<Fuck> response) {
                if (response.isSuccessful()) {
                }

            }

            @Override
            public void onFailure(Call<Fuck> call, Throwable t) {

            }
        });
    }
}
