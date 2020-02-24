package com.inuappcenter.shareu.activity;

import android.os.Bundle;
import android.view.WindowManager;

import com.inuappcenter.shareu.R;
import com.willy.ratingbar.BaseRatingBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailedFileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_file);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        BaseRatingBar ratingBar  = findViewById(R.id.review_ratingbar);
        ratingBar.setScrollable(false);
        ratingBar.setClickable(false);
        BaseRatingBar ratingBar2  = findViewById(R.id.before_user_ratingbar);
        ratingBar2.setScrollable(false);
        ratingBar2.setClickable(false);
    }
}
