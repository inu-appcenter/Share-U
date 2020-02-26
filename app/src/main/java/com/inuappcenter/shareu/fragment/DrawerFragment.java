package com.inuappcenter.shareu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.activity.DetailedNoticeActivity;
import com.inuappcenter.shareu.activity.MainActivity;
import com.inuappcenter.shareu.activity.MyGiveActivity;
import com.inuappcenter.shareu.activity.MyUploadActivity;
import com.inuappcenter.shareu.activity.OverallNoticeActivity;
import com.inuappcenter.shareu.my_class.MyUpload;
import com.inuappcenter.shareu.recycler.MyGiveAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

public class DrawerFragment extends Fragment {
    DrawerLayout drawer_my_page;
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_drawer_my_page, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        View.OnClickListener onClickListener = new TextView.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.tv_my_notice_my_page:
                        Intent intent = new Intent(view.getContext(), OverallNoticeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        view.getContext().startActivity(intent);
                        break;

                    case R.id.btn_drawer_back:
                        ((MainActivity)getActivity()).closeDrawer();
                        break;

                    case R.id.tv_my_notice_my_page2:
                        Intent intent2 = new Intent(view.getContext(), OverallNoticeActivity.class);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        view.getContext().startActivity(intent2);
                        break;

                    case R.id.btn_drawer_back2:
                        ((MainActivity)getActivity()).closeDrawer();
                        break;

                    case R.id.tv_my_update_my_page:

                        Intent intent3 = new Intent(view.getContext(), MyUploadActivity.class);
                        intent3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        view.getContext().startActivity(intent3);
                        break;


                    case R.id.tv_my_get_my_page:
                        Intent intent4 = new Intent(view.getContext(), MyGiveActivity.class);
                        intent4.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        view.getContext().startActivity(intent4);
                        break;
                }

            }
        } ;


        TextView tv_my_notice_my_page = (TextView) view.findViewById(R.id.tv_my_notice_my_page) ;
        tv_my_notice_my_page.setOnClickListener(onClickListener);
        ImageButton btn_drawer_back = (ImageButton) view.findViewById(R.id.btn_drawer_back) ;
        btn_drawer_back.setOnClickListener(onClickListener) ;

        TextView tv_my_notice_my_page2 = (TextView) view.findViewById(R.id.tv_my_notice_my_page2) ;
        tv_my_notice_my_page2.setOnClickListener(onClickListener);

        ImageButton btn_drawer_back2 = (ImageButton) view.findViewById(R.id.btn_drawer_back2) ;
        btn_drawer_back2.setOnClickListener(onClickListener) ;

        TextView tv_my_update_my_page = (TextView) view.findViewById(R.id.tv_my_update_my_page) ;
        tv_my_update_my_page.setOnClickListener(onClickListener);

        TextView tv_my_get_my_page = (TextView) view.findViewById(R.id.tv_my_get_my_page) ;
        tv_my_get_my_page.setOnClickListener(onClickListener);
    }
}
