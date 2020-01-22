package com.inuappcenter.shareu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.model.SuperiorLecture;
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



    }
}
