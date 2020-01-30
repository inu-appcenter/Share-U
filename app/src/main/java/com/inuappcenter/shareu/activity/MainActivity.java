package com.inuappcenter.shareu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.model.Notice;
import com.inuappcenter.shareu.model.SuperiorLecture;
import com.inuappcenter.shareu.recycler.NoticeAdapter;
import com.inuappcenter.shareu.recycler.SuperiorLectureAdapter;
import com.kingfisher.easyviewindicator.RecyclerViewIndicator;

import java.util.ArrayList;

import static java.lang.Boolean.FALSE;

public class MainActivity extends AppCompatActivity {

    //Drawer 처리
    DrawerLayout drawer_my_page;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recycler 처리
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerview_main);
        RecyclerViewIndicator horizontalIndicator = (RecyclerViewIndicator)findViewById(R.id.recyclerViewIndicator);

        final EditText editText =(EditText)findViewById(R.id.etv_main);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<SuperiorLecture> items=new ArrayList<>();
        SuperiorLecture[] item=new SuperiorLecture[5];
        item[0]=new SuperiorLecture(R.drawable.excel,"#1",5);
        item[1]=new SuperiorLecture(R.drawable.excel,"#2",4);
        item[2]=new SuperiorLecture(R.drawable.excel,"#3",(float)4.5);
        item[3]=new SuperiorLecture(R.drawable.excel,"#4",3);
        item[4]=new SuperiorLecture(R.drawable.excel,"#5",2);

        for(int i=0;i<5;i++) items.add(item[i]);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new SuperiorLectureAdapter(getApplicationContext(),items));
        horizontalIndicator.setRecyclerView(recyclerView);


        RecyclerView recyclerView2=(RecyclerView)findViewById(R.id.recyclerview2_main);
        LinearLayoutManager layoutManager2=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(layoutManager2);

        ArrayList<Notice> items2=new ArrayList<>();
        Notice[] item2=new Notice[10];
        item2[0]=new Notice("1번 공지",R.drawable.rightarrow);
        item2[1]=new Notice("2번 공지",R.drawable.rightarrow);
        item2[2]=new Notice("3번 공지",R.drawable.rightarrow);
        item2[3]=new Notice("4번 공지",R.drawable.rightarrow);
        item2[4]=new Notice("5번 공지",R.drawable.rightarrow);
        item2[5]=new Notice("6번 공지",R.drawable.rightarrow);
        item2[6]=new Notice("7번 공지",R.drawable.rightarrow);
        item2[7]=new Notice("8번 공지",R.drawable.rightarrow);
        item2[8]=new Notice("9번 공지",R.drawable.rightarrow);
        item2[9]=new Notice("10번 공지",R.drawable.rightarrow);

        for(int i=0;i<10;i++) items2.add(item2[i]);

        recyclerView2.setAdapter(new NoticeAdapter(getApplicationContext(),items2));

        View view = (View)findViewById(R.id.drawer_login);
        view.setVisibility(View.GONE);
        drawer_my_page = (DrawerLayout)findViewById(R.id.include_drawer_my_page);
        View.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_left_bar_main :
                        Intent intent = new Intent(getApplicationContext(),MajorActivity.class);
                        startActivity(intent);
                        break ;
                    case R.id.btn_my_page_main :
                        drawer_my_page.openDrawer(GravityCompat.END);
                        break ;

                }

            }
        } ;
        //과목 선택 처리
        Button btn_left_bar_main = (Button) findViewById(R.id.btn_left_bar_main) ;
        btn_left_bar_main.setOnClickListener(onClickListener) ;
        //drawer 처리
        Button btn_my_page_main = (Button)findViewById(R.id.btn_my_page_main);
        btn_my_page_main.setOnClickListener(onClickListener) ;



    }
    @Override
    public void onBackPressed() {
        if (drawer_my_page.isDrawerOpen(GravityCompat.END)) {
            drawer_my_page.closeDrawer(GravityCompat.END);
        }
        else {
            super.onBackPressed();
        }
    }
        
}


