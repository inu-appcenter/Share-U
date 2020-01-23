package com.inuappcenter.shareu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.model.Notice;
import com.inuappcenter.shareu.model.SuperiorLecture;
import com.inuappcenter.shareu.recycler.NoticeAdapter;
import com.inuappcenter.shareu.recycler.SuperiorLectureAdapter;
import com.kingfisher.easyviewindicator.RecyclerViewIndicator;

import java.util.ArrayList;

import static java.lang.Boolean.FALSE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }
}
