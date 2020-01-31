package com.inuappcenter.shareu.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.model.Code;
import com.inuappcenter.shareu.model.Major;
import com.inuappcenter.shareu.recycler.MajorAdapter;
import com.inuappcenter.shareu.recycler.MajorAdapter2;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import in.myinnos.alphabetsindexfastscrollrecycler.IndexFastScrollRecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.VISIBLE;

public class MajorActivity2 extends AppCompatActivity {

    private ArrayList<Major> dataList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_major);
        this.initializeData();
        TextView tv_major = (TextView) findViewById(R.id.tv_major) ;
        TextView tv_my_major = (TextView) findViewById(R.id.tv_my_major) ;
        tv_major.setTextColor(Color.parseColor("#574FBA"));
        tv_major.setTypeface(Typeface.DEFAULT_BOLD);
        tv_my_major.setVisibility(VISIBLE);
        Intent intent =getIntent();
        String name = intent.getExtras().getString("select_major"); /*String형*/
        tv_my_major.setText(name);
        //initializeData();


        RetrofitService networkService = RetrofitHelper.create();
        networkService.getDetailedMajorList(name).enqueue(new Callback<List<Major>>(){
            @Override
            public void onResponse(Call<List<Major> > call, Response<List<Major>> response) {
                if(response.isSuccessful())
                {
                    dataList = new ArrayList<>();
                    String flag ="?";
                    for(int i=0;i<response.body().size();i++)
                    {
                        if(flag.equals(response.body().get(i).third))
                        {
                            dataList.add(new Major(response.body().get(i).first,response.body().get(i).second,response.body().get(i).third,Code.ViewType.MAJOR));
                        }
                        else
                        {
                            flag=response.body().get(i).third;
                            dataList.add(new Major(response.body().get(i).third,null,response.body().get(i).third,Code.ViewType.INDEX));
                            dataList.add(new Major(response.body().get(i).first,response.body().get(i).second,response.body().get(i).third,Code.ViewType.MAJOR));
                        }
                    }



                }
                IndexFastScrollRecyclerView recyclerView = findViewById(R.id.recyclerview_select_major);
                LinearLayoutManager manager
                        = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
                recyclerView.setIndexTextSize(12);
                recyclerView.setIndexBarColor("#FFFFFF");
                recyclerView.setIndexBarTextColor("#000000");
                recyclerView.setIndexBarStrokeVisibility(false);
                recyclerView.setLayoutManager(manager); // LayoutManager 등록
                recyclerView.setAdapter(new MajorAdapter2(MajorActivity2.this,dataList));  // Adapter 등록
            }
            @Override
            public void onFailure(Call<List<Major>> call, Throwable t) {

            }
        });


        Button btn_left_bar_major = (Button)findViewById(R.id.btn_left_bar_major);
        Button.OnClickListener onClickListener2 = new Button.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                switch(view.getId())
                {
                    case R.id.btn_left_bar_major:
                        finish();
                }
            }
        };
        btn_left_bar_major.setOnClickListener(onClickListener2);

    }
    public void initializeData()
    {
        dataList = new ArrayList<>();

        dataList.add(new Major("사용자1님이 입장하셨습니다.33", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요33", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
    }
    public void initializeData2()
    {
        dataList = new ArrayList<>();

        dataList.add(new Major("사용자1님이 입장하셨습니다2.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요2", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
        dataList.add(new Major("사용자1님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자1", null, Code.ViewType.MAJOR));
        dataList.add(new Major("사용자2님이 입장하셨습니다.", null, null, Code.ViewType.INDEX));
        dataList.add(new Major("안녕하세요", "사용자2",null,  Code.ViewType.MAJOR));
    }
}
