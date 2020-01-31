package com.inuappcenter.shareu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.activity.MainActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

public class DrawerFragment extends Fragment {
    DrawerLayout drawer_my_page;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_drawer_my_page, container, false);
        View.OnClickListener onClickListener = new TextView.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.tv_my_update_my_page2:
                        Toast.makeText(getActivity(),"얍",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.btn_drawer_back2:
                        ((MainActivity)getActivity()).closeDrawer();
                        break;
                }

            }
        } ;
        TextView tv_my_update_my_page = (TextView) view.findViewById(R.id.tv_my_update_my_page2) ;
        tv_my_update_my_page.setOnClickListener(onClickListener);
        ImageButton btn_drawer_back2 = (ImageButton) view.findViewById(R.id.btn_drawer_back2) ;
        btn_drawer_back2.setOnClickListener(onClickListener) ;
        return view;
    }

}
