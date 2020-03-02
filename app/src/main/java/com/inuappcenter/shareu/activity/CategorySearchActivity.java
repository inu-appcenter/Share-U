package com.inuappcenter.shareu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.fragment.CategorySearchAllFragment;
import com.inuappcenter.shareu.fragment.CategorySearchNoFragment;
import com.inuappcenter.shareu.fragment.MainFragment;
import com.inuappcenter.shareu.fragment.SearchAllResultFragment;
import com.inuappcenter.shareu.fragment.SearchNoResultFragment;
import com.inuappcenter.shareu.my_class.Document;
import com.inuappcenter.shareu.my_class.SuperiorLecture;
import com.inuappcenter.shareu.my_class.TokenManager;
import com.inuappcenter.shareu.my_class.categoryResend;
import com.inuappcenter.shareu.my_class.subjectName;
import com.inuappcenter.shareu.presenter.SearchAllResultContract;
import com.inuappcenter.shareu.presenter.SearchAllResultPresenter;
import com.inuappcenter.shareu.recycler.CategorySuccessedAdatper;
import com.inuappcenter.shareu.recycler.DocumentAdapter;
import com.inuappcenter.shareu.recycler.SuperiorLectureAdapter2;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategorySearchActivity extends AppCompatActivity {
    private TextView tv_my_major;
    private ImageView btn_backpress;
    FloatingActionButton fab_main;
    private String subjectName,profName,majorName;

    private MainFragment mainFragment;
    private CategorySearchAllFragment categorySearchAllFragment;
    private CategorySearchNoFragment categorySearchNoFragment;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_search);
        init();
    }

    void init()
    {
        tv_my_major=findViewById(R.id.tv_my_major);
        btn_backpress=findViewById(R.id.btn_backpress);
        fab_main=findViewById(R.id.fab_main);
        Intent intent =getIntent();
        subjectName=intent.getExtras().getString("major");
        profName =intent.getExtras().getString("prof");


    }

    @Override
    public void onResume() {
        super.onResume();

        listener();
        giveMyMajor();
        giveList();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    void listener()
    {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_backpress :
                        finish();
                        break;
                    case R.id.fab_main:
                        TokenManager tm = TokenManager.getInstance();
                        String token = tm.getToken(getApplicationContext());
                        if(token!=null)
                        {
                            Intent intent3 = new Intent(getApplicationContext(), FileUploadActivity.class);
                            startActivity(intent3);
                        }
                        else
                        {
                            Intent intent3 = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent3);
                        }
                        break ;

                }

            }
        } ;
        btn_backpress.setOnClickListener(onClickListener);
        fab_main.setOnClickListener(onClickListener);
    }

    void giveMyMajor()
    {
        RetrofitService networkService = RetrofitHelper.create();
        networkService.categoryResend(subjectName,profName).enqueue(new Callback<List<categoryResend>>() {
            @Override
            public void onResponse(Call<List<categoryResend>> call, Response<List<categoryResend>> response) {
                if (response.isSuccessful()) {
                    majorName=response.body().get(0).getMajorName();
                    tv_my_major.setText(majorName);
                }

            }

            @Override
            public void onFailure(Call<List<categoryResend>> call, Throwable t) {
                Intent intent = new Intent(getApplicationContext(), ServerFailActivity.class);
                startActivity(intent);
            }
        });
    }
    void giveList()
    {
        RetrofitService networkService = RetrofitHelper.create();
        networkService.documentTop5DateList("",subjectName,profName).enqueue(new Callback<List<Document>>() {
            @Override
            public void onResponse(Call<List<Document>> call, Response<List<Document>> response) {
                if (response.isSuccessful()) {
                    if(response.body().size()==0)
                    {

                        fragmentManager = getSupportFragmentManager();
                        categorySearchNoFragment = new CategorySearchNoFragment();
                        transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.layout_frame_search_result,categorySearchNoFragment);
                        transaction.commit();
                    }
                    else
                    {
                        fragmentManager = getSupportFragmentManager();
                        categorySearchAllFragment=new CategorySearchAllFragment(subjectName,profName);
                        transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.layout_frame_search_result,categorySearchAllFragment);
                        transaction.commit();
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Document>> call, Throwable t) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
