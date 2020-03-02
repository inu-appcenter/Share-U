package com.inuappcenter.shareu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.fragment.BottomSheetPlusPoint;
import com.inuappcenter.shareu.my_class.Document;
import com.inuappcenter.shareu.my_class.documentPage;
import com.inuappcenter.shareu.my_class.reviewList;
import com.inuappcenter.shareu.my_class.score;
import com.inuappcenter.shareu.my_interface.OnItemClick;
import com.inuappcenter.shareu.recycler.CategorySuccessedAdatper;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;
import com.willy.ratingbar.BaseRatingBar;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.inuappcenter.shareu.R.id.btn_backpress;
import static com.inuappcenter.shareu.R.id.tv_detailed_file_date;
import static com.inuappcenter.shareu.R.id.tv_no_review;

public class DetailedFileActivity extends AppCompatActivity implements OnItemClick {
    private BottomSheetPlusPoint bottomSheetPlusPoint;
    private BaseRatingBar review_ratingbar;
    private BaseRatingBar before_user_ratingbar;
    private TextView tv_get_file,tv_detailed_file_user_name,tv_detailed_file_category,
            tv_detailed_file_name,tv_detailed_file_type,tv_detailed_file_date;
    private TextView tv_review_date,tv_review_name,tv_review_content,tv_my_detailed_file_review,tv_no_review;
    private TextView tv_my_major;
    private TextView tv_detailed_file_content,tv_more;
    private LinearLayout yes_review;
    private int key;
    private ImageView btn_backpress;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_file);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        init();

        Intent intent =getIntent();
        key =intent.getExtras().getInt("key");
    }

    @Override
    protected void onResume() {
        super.onResume();
        listen();
        giveMeStar();
        giveMeList();
        giveMeReview();

    }
    void listen()
    {
        tv_more.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ReviewActivity.class);
                intent.putExtra("key",key);
                intent.putExtra("name",tv_my_major.getText()+"");
                startActivity(intent);
            }
        });

        tv_get_file.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //TODO : 상황에 따라 포인트 지급 , 차감 결정..
                bottomSheetPlusPoint.show(getSupportFragmentManager(),"냐옹");

            }
        });
        btn_backpress.setOnClickListener(v->finish());
    }

    void giveMeStar()
    {
        RetrofitService networkService = RetrofitHelper.create();
        networkService.score(key).enqueue(new Callback<List<score>>() {
            @Override
            public void onResponse(Call<List<score>> call, Response<List<score>> response) {
                if (response.isSuccessful()) {
                    review_ratingbar.setRating(response.body().get(0).getScore());

                }

            }

            @Override
            public void onFailure(Call<List<score>> call, Throwable t) {

            }
        });
    }
    void giveMeReview()
    {
        RetrofitService networkService = RetrofitHelper.create();
        networkService.reviewList(key).enqueue(new Callback<List<reviewList>>() {
            @Override
            public void onResponse(Call<List<reviewList>> call, Response<List<reviewList>> response) {
                if (response.isSuccessful()) {
                    tv_my_detailed_file_review.setText("리뷰 ("+response.body().size()+")");
                    if(response.body().size()==0)
                    {
                        tv_no_review.setVisibility(View.VISIBLE);
                        yes_review.setVisibility(View.GONE);
                        tv_more.setVisibility(View.GONE);

                    }
                    else
                    {
                        tv_no_review.setVisibility(View.GONE);
                        yes_review.setVisibility(View.VISIBLE);
                        tv_more.setVisibility(View.VISIBLE);
                        tv_review_date.setText(response.body().get(0).getUploadDate());
                        String name = response.body().get(0).getUploadId();
                        name=name.substring(0,5);
                        name+="***";
                        tv_review_name.setText(name);
                        before_user_ratingbar.setRating(response.body().get(0).getScore());
                        tv_review_content.setText(response.body().get(0).getReview());
                    }

                }

            }

            @Override
            public void onFailure(Call<List<reviewList>> call, Throwable t) {

            }
        });
    }
    void giveMeList()
    {
        RetrofitService networkService = RetrofitHelper.create();
        networkService.documentPage(key).enqueue(new Callback<List<documentPage>>() {
            @Override
            public void onResponse(Call<List<documentPage>> call, Response<List<documentPage>> response) {
                if (response.isSuccessful()) {
                    tv_my_major.setText(response.body().get(0).getTitle());
                    tv_detailed_file_content.setText(response.body().get(0).getContent());
                    //review_ratingbar.setRating(response.body().get(0).get);
                    String name = response.body().get(0).getUploadId();
                    name=name.substring(0,5);
                    name+="****";
                    tv_detailed_file_user_name.setText(name);
                    //tv_detailed_file_category.setText(response.body().get(0))
                    tv_detailed_file_date.setText(response.body().get(0).getUploadDate());
                    tv_detailed_file_category.setText(response.body().get(0).getMajorName());
                    tv_detailed_file_type.setText(response.body().get(0).getExtension());
                    tv_detailed_file_name.setText(response.body().get(0).getSubjectName());
                }

            }

            @Override
            public void onFailure(Call<List<documentPage>> call, Throwable t) {

            }
        });
    }

    void init()
    {
        bottomSheetPlusPoint=new BottomSheetPlusPoint();
        review_ratingbar  = findViewById(R.id.review_ratingbar);
        review_ratingbar.setScrollable(false);
        review_ratingbar.setClickable(false);
        before_user_ratingbar  = findViewById(R.id.before_user_ratingbar);
        before_user_ratingbar.setScrollable(false);
        before_user_ratingbar.setClickable(false);
        tv_get_file = findViewById(R.id.tv_get_file);
        tv_detailed_file_user_name=findViewById(R.id.tv_detailed_file_user_name);
        tv_detailed_file_category=findViewById(R.id.tv_detailed_file_category);
        tv_detailed_file_name = findViewById(R.id.tv_detailed_file_name);
        tv_detailed_file_type=findViewById(R.id.tv_detailed_file_type);
        tv_detailed_file_date = findViewById(R.id.tv_detailed_file_date);
        tv_review_date=findViewById(R.id.tv_review_date);
        tv_review_name=findViewById(R.id.tv_review_name);
        tv_review_content=findViewById(R.id.tv_review_content);
        tv_my_detailed_file_review=findViewById(R.id.tv_my_detailed_file_review);
        tv_no_review=findViewById(R.id.tv_no_review);
        tv_my_major = findViewById(R.id.tv_my_major);
        tv_detailed_file_content=findViewById(R.id.tv_detailed_file_content);
        yes_review=findViewById(R.id.layout_yes_review);
        tv_more = findViewById(R.id.tv_more);
        btn_backpress=findViewById(R.id.btn_backpress);
    }

    @Override
    public void onClick(String value) {
        bottomSheetPlusPoint.dismiss();
    }

    @Override
    public void onClick2(String value) {

    }
}
