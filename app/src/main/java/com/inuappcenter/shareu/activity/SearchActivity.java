package com.inuappcenter.shareu.activity;

import android.os.Bundle;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.fragment.MajorFragment;
import com.inuappcenter.shareu.fragment.SearchAllResultFragment;
import com.inuappcenter.shareu.fragment.SearchNoResultFragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SearchActivity extends AppCompatActivity {

    /*private FragmentManager fragmentManager;
    private SearchAllResultFragment searchAllResultFragment;
    private SearchNoResultFragment searchNoResultFragment;
    private FragmentTransaction transaction;*/
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main_search);
       /* fragmentManager = getSupportFragmentManager();
        *//*searchAllResultFragment = new SearchAllResultFragment();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.layout_frame_search_result,searchAllResultFragment);
        transaction.commit();
*//*
        searchNoResultFragment = new SearchNoResultFragment();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.layout_frame_search_result,searchNoResultFragment);
        transaction.commit();

        //TODO : 검색 결과 구현*/

    }
}
