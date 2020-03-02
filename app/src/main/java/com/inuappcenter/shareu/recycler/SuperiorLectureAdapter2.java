package com.inuappcenter.shareu.recycler;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.my_class.Notice;
import com.inuappcenter.shareu.my_class.SuperiorLecture;
import com.willy.ratingbar.BaseRatingBar;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SuperiorLectureAdapter2 extends PagerAdapter {
    // LayoutInflater 서비스 사용을 위한 Context 참조 저장.
    private List<SuperiorLecture> mitems;
    private Context mContext;

    public SuperiorLectureAdapter2( Context mContext) {
        this.mitems = new ArrayList<>();
        this.mContext = mContext;
    }
    public void setData(List<SuperiorLecture> superiorLectures) {
        mitems = superiorLectures;
        notifyDataSetChanged();
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view ;

        // LayoutInflater를 통해 "/res/layout/page.xml"을 뷰로 생성.
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item_recyclerview_superior, container, false);


            /*TextView textView = (TextView) view.findViewById(R.id.title) ;
            textView.setText("TEXT " + position) ;*/
        ImageView item_default_superior=view.findViewById(R.id.item_default_superior);
        TextView tv_superior_name=view.findViewById(R.id.tv_superior_name);
        BaseRatingBar ratingBar=view.findViewById(R.id.superior_ratingbar);
        if(mitems.get(position).getExtension().equals("PPT") || mitems.get(position).getExtension().equals("PPTX")||
                mitems.get(position).getExtension().equals("ppt") || mitems.get(position).getExtension().equals("pptx")
        )
        {
            item_default_superior.setImageResource(R.drawable.ppt);
        }
        else if(mitems.get(position).getExtension().equals("HWP") ||mitems.get(position).getExtension().equals("hwp") )
        {
            item_default_superior.setImageResource(R.drawable.korean);
        }
        else if(mitems.get(position).getExtension().equals("DOC") || mitems.get(position).getExtension().equals("DOCX")
                ||mitems.get(position).getExtension().equals("doc") || mitems.get(position).getExtension().equals("docx")
        )
        {
            item_default_superior.setImageResource(R.drawable.word);
        }
        else if(mitems.get(position).getExtension().equals("AI")
        ||mitems.get(position).getExtension().equals("ai")
        )
        {
            item_default_superior.setImageResource(R.drawable.ai);
        }
        else if(mitems.get(position).getExtension().equals("PS")
        ||mitems.get(position).getExtension().equals("ps")
        )
        {
            item_default_superior.setImageResource(R.drawable.ps);
        }

        else if(mitems.get(position).getExtension().equals("JPEG") || mitems.get(position).getExtension().equals("JPG")||
                mitems.get(position).getExtension().equals("jpeg") || mitems.get(position).getExtension().equals("jpg")
        )
        {
            item_default_superior.setImageResource(R.drawable.jpeg);
        }
        else if(mitems.get(position).getExtension().equals("PNG") || mitems.get(position).getExtension().equals("png") )
        {
            item_default_superior.setImageResource(R.drawable.png);
        }
        else if(mitems.get(position).getExtension().equals("XLS")||mitems.get(position).getExtension().equals("XLSX")||
                mitems.get(position).getExtension().equals("XLSM") || mitems.get(position).getExtension().equals("CSV")||
                mitems.get(position).getExtension().equals("xls")||mitems.get(position).getExtension().equals("xlsx")||
                mitems.get(position).getExtension().equals("xlsm") || mitems.get(position).getExtension().equals("csv")
        )
        {
            item_default_superior.setImageResource(R.drawable.excel);
        }
        else if(mitems.get(position).getExtension().equals("MP3")||mitems.get(position).getExtension().equals("mp3"))
        {
            item_default_superior.setImageResource(R.drawable.mp3);
        }
        else if(mitems.get(position).getExtension().equals("ZIP") ||mitems.get(position).getExtension().equals("zip")  )
        {
            item_default_superior.setImageResource(R.drawable.zip);
        }
        else
        {
            item_default_superior.setImageResource(R.drawable.file);
        }
        //item_default_superior.setImageResource(mitems.get(position).getImage());
        tv_superior_name.setText(mitems.get(position).getTitle());
        ratingBar.setRating(mitems.get(position).getRating());


        // 뷰페이저에 추가.
        container.addView(view,0) ;

        return view ;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // 뷰페이저에서 삭제.
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        // 전체 페이지 수는 10개로 고정.
        return mitems.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    public void setItem(List<SuperiorLecture> data)
    {
        mitems=data;
        notifyDataSetChanged();
    }
}
