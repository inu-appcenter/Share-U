package com.inuappcenter.shareu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.my_class.reviewList;
import com.inuappcenter.shareu.recycler.CategorySuccessedAdatper;
import com.inuappcenter.shareu.recycler.ReviewAdapter;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewActivity extends AppCompatActivity {
    private int key;
    private String name;
    private ImageView btn_backpress;
    private TextView tv_my_major,tv_my_detailed_file_review;
    private ReviewAdapter reviewAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        init();
        Intent intent =getIntent();
        key =intent.getExtras().getInt("key");
        name = intent.getExtras().getString("name");
        btn_backpress.setOnClickListener(v->finish());

    }

    @Override
    protected void onResume() {
        super.onResume();
        giveMeReview();
    }

    void giveMeReview()
    {
        RetrofitService networkService = RetrofitHelper.create();
        networkService.reviewList(key).enqueue(new Callback<List<reviewList>>() {
            @Override
            public void onResponse(Call<List<reviewList>> call, Response<List<reviewList>> response) {
                if (response.isSuccessful()) {
                    tv_my_detailed_file_review.setText("리뷰 ("+response.body().size()+")");
                    List<reviewList>tmp_list = new ArrayList<>();
                    for(int i=0;i<response.body().size();i++)
                    {
                        tmp_list.add(new reviewList(response.body().get(i).getUploadDate(),response.body().get(i).getUploadId(),

                                response.body().get(i).getReview(),response.body().get(i).getScore(),response.body().get(i).getReviewKey()));
                    }
                    reviewAdapter =new ReviewAdapter(getApplicationContext(),tmp_list);
                    RecyclerView recyclerView2=findViewById(R.id.recyclerview_review);
                    LinearLayoutManager layoutManager2=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
                    recyclerView2.setHasFixedSize(true);
                    recyclerView2.setLayoutManager(layoutManager2);
                    recyclerView2.setAdapter(reviewAdapter);
                }

            }

            @Override
            public void onFailure(Call<List<reviewList>> call, Throwable t) {

            }
        });
    }
    void init()
    {
        btn_backpress=findViewById(R.id.btn_backpress);
        tv_my_major=findViewById(R.id.tv_my_major);
        tv_my_detailed_file_review=findViewById(R.id.tv_my_detailed_file_review);
    }
    void listen()
    {

        tv_my_major.setText(name);
    }
}
