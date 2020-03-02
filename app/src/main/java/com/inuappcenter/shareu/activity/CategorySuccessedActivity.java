package com.inuappcenter.shareu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.my_class.Document;
import com.inuappcenter.shareu.recycler.CategorySuccessedAdatper;
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

public class CategorySuccessedActivity extends AppCompatActivity {

    private String subjectName,profName;
    private CategorySuccessedAdatper categorySuccessedAdatper;
    private TextView tv_my_major;
    private ImageView btn_backpress,btn_down,btn_up;
    private LinearLayout click_me;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_succeesed);
        Intent intent = getIntent();
        subjectName = intent.getExtras().getString("subjectName");
        profName=intent.getExtras().getString("profName");
        subjectName=subjectName.replace("\"","");
        tv_my_major=findViewById(R.id.tv_my_major);
        btn_backpress=findViewById(R.id.btn_backpress);
        click_me=findViewById(R.id.click_me);
        btn_up=findViewById(R.id.btn_up);
        btn_down=findViewById(R.id.btn_down);

    }

    @Override
    protected void onResume() {
        super.onResume();
        tv_my_major.setText(subjectName);
        btn_backpress.setOnClickListener(v->finish());
        goDown();
        click_me.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(btn_down.getVisibility()==View.VISIBLE)//이제 오름차순으로 보여줘야해
                {
                    btn_down.setVisibility(View.GONE);
                    btn_up.setVisibility(View.VISIBLE);
                    goUp();

                }
                else
                {
                    btn_down.setVisibility(View.VISIBLE);
                    btn_up.setVisibility(View.GONE);
                    goDown();
                }
            }
        });


    }

    void goDown()
    {
        RetrofitService networkService = RetrofitHelper.create();
        networkService.more(subjectName,profName).enqueue(new Callback<List<Document>>() {
            @Override
            public void onResponse(Call<List<Document>> call, Response<List<Document>> response) {
                if (response.isSuccessful()) {
                    List<Document> tmp_list = new ArrayList<>();
                    for(int i=response.body().size()-1;i>=0;i--)
                    {
                        tmp_list.add(new Document(response.body().get(i).getTitle(),
                                response.body().get(i).getUploadDate(),
                                response.body().get(i).getDocumentKey(),
                                response.body().get(i).getExtension(),response.body().get(i).getSubjectName(),
                                response.body().get(i).getProfName(),null,response.body().get(i).getAvg_score()));
                    }
                    categorySuccessedAdatper=new CategorySuccessedAdatper(tmp_list,getApplicationContext());
                    RecyclerView recyclerView2=findViewById(R.id.recyclerview_category_success);
                    LinearLayoutManager layoutManager2=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
                    recyclerView2.setHasFixedSize(true);
                    recyclerView2.setLayoutManager(layoutManager2);
                    recyclerView2.setAdapter(categorySuccessedAdatper);
                }

            }

            @Override
            public void onFailure(Call<List<Document>> call, Throwable t) {
                Intent intent = new Intent(getApplicationContext(), ServerFailActivity.class);
                startActivity(intent);
            }
        });

    }
    void goUp()
    {
        RetrofitService networkService = RetrofitHelper.create();
        networkService.more(subjectName,profName).enqueue(new Callback<List<Document>>() {
            @Override
            public void onResponse(Call<List<Document>> call, Response<List<Document>> response) {
                if (response.isSuccessful()) {
                    List<Document> tmp_list = new ArrayList<>();
                    for(int i=0;i<response.body().size();i++)
                    {
                        tmp_list.add(new Document(response.body().get(i).getTitle(),
                                response.body().get(i).getUploadDate(),
                                response.body().get(i).getDocumentKey(),
                                response.body().get(i).getExtension(),response.body().get(i).getSubjectName(),
                                response.body().get(i).getProfName(),null,response.body().get(i).getAvg_score()));
                    }
                    categorySuccessedAdatper=new CategorySuccessedAdatper(tmp_list,getApplicationContext());
                    RecyclerView recyclerView2=findViewById(R.id.recyclerview_category_success);
                    LinearLayoutManager layoutManager2=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
                    recyclerView2.setHasFixedSize(true);
                    recyclerView2.setLayoutManager(layoutManager2);
                    recyclerView2.setAdapter(categorySuccessedAdatper);
                }

            }

            @Override
            public void onFailure(Call<List<Document>> call, Throwable t) {

            }
        });
    }
}
