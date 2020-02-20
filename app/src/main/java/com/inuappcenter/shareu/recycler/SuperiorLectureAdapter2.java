package com.inuappcenter.shareu.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.my_class.SuperiorLecture;
import com.willy.ratingbar.BaseRatingBar;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SuperiorLectureAdapter2 extends PagerAdapter {
    // LayoutInflater 서비스 사용을 위한 Context 참조 저장.
    private ArrayList<SuperiorLecture> mitems;
    private Context mContext;

    public SuperiorLectureAdapter2(ArrayList<SuperiorLecture> mitem, Context mContext) {
        this.mitems = mitem;
        this.mContext = mContext;
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

        item_default_superior.setImageResource(mitems.get(position).getImage());
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
}
