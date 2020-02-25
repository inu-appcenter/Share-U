package com.inuappcenter.shareu.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.fragment.BottomSheetPlusPoint;
import com.inuappcenter.shareu.my_interface.OnItemClick;
import com.willy.ratingbar.BaseRatingBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static com.inuappcenter.shareu.R.id.btn_backpress;

public class DetailedFileActivity extends AppCompatActivity implements OnItemClick {
    BottomSheetPlusPoint bottomSheetPlusPoint;
    BaseRatingBar ratingBar;
    BaseRatingBar ratingBar2;
    TextView tv_get_file;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_file);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        init();
        tv_get_file.setOnClickListener(v->bottomSheetPlusPoint.show(getSupportFragmentManager(),"냐옹"));


    }
    void init()
    {
        bottomSheetPlusPoint=new BottomSheetPlusPoint();
        ratingBar  = findViewById(R.id.review_ratingbar);
        ratingBar.setScrollable(false);
        ratingBar.setClickable(false);
        ratingBar2  = findViewById(R.id.before_user_ratingbar);
        ratingBar2.setScrollable(false);
        ratingBar2.setClickable(false);
        tv_get_file = findViewById(R.id.tv_get_file);
    }

    @Override
    public void onClick(String value) {
        bottomSheetPlusPoint.dismiss();
    }

    @Override
    public void onClick2(String value) {

    }
}
