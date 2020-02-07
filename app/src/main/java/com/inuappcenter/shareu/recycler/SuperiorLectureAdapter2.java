package com.inuappcenter.shareu.recycler;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.model.SuperiorLecture;

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
        View view = null ;

        // LayoutInflater를 통해 "/res/layout/page.xml"을 뷰로 생성.
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item_recyclerview_superior, container, false);


            /*TextView textView = (TextView) view.findViewById(R.id.title) ;
            textView.setText("TEXT " + position) ;*/
        final SuperiorLecture item = mitems.get(position);
        ImageView item_default_superior=view.findViewById(R.id.item_default_superior);
        TextView tv_superior_name=view.findViewById(R.id.tv_superior_name);
        RatingBar ratingBar=view.findViewById(R.id.ratingBar);

        Drawable drawable = mContext.getResources().getDrawable(item.getImage());

        item_default_superior.setBackground(drawable);
        tv_superior_name.setText(item.getTitle());
        ratingBar.setRating(item.getRating());

        // 뷰페이저에 추가.
        container.addView(view) ;

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
        return 10;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (View)object);
    }
}
