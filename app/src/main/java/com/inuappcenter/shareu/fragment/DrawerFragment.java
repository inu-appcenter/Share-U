package com.inuappcenter.shareu.fragment;

import android.content.Intent;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.activity.DetailedNoticeActivity;
import com.inuappcenter.shareu.activity.LoginActivity;
import com.inuappcenter.shareu.activity.MainActivity;
import com.inuappcenter.shareu.activity.ModifyInformActivity;
import com.inuappcenter.shareu.activity.MyGiveActivity;
import com.inuappcenter.shareu.activity.MyUploadActivity;
import com.inuappcenter.shareu.activity.OverallNoticeActivity;
import com.inuappcenter.shareu.activity.ServerFailActivity;
import com.inuappcenter.shareu.my_class.BooleanFuck;
import com.inuappcenter.shareu.my_class.Fuck;
import com.inuappcenter.shareu.my_class.Login;
import com.inuappcenter.shareu.my_class.MyPage;
import com.inuappcenter.shareu.my_class.MyUpload;
import com.inuappcenter.shareu.my_class.Notice;
import com.inuappcenter.shareu.my_class.SumPoint;
import com.inuappcenter.shareu.my_class.TokenManager;
import com.inuappcenter.shareu.my_interface.OnItemClick;
import com.inuappcenter.shareu.recycler.MyGiveAdapter;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrawerFragment extends Fragment {
    OnItemClick mListener;
    private View view;
    private TextView tv_my_major;
    private TextView tv_my_adress;
    private TextView tv_my_name;
    private TextView tv_numeric_point;
    private TextView tv_my_change_my_page;

    private LinearLayout give_me_welcome_point,my_point;
    private ConstraintLayout welcome_point;
    private FrameLayout getpoint;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_drawer_my_page, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        init();
        mListener=(OnItemClick)getActivity();
        //Log.e("토큰 : ",tm.getToken(getActivity()));
        TokenManager tm = TokenManager.getInstance();
        String token = tm.getToken(getActivity());
        if(token!=null)
        {
            //마이페이지 정보 받아오기
            giveMyImform();
            giveMyPoint();
            checkWelcomePoint();

        }
        else
        {

        }

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
                    case R.id.tv_my_notice_login:
                        //로그인 해라
                        Intent intent5 = new Intent(view.getContext(), LoginActivity.class);
                        intent5.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        view.getContext().startActivity(intent5);
                        mListener.onClick("닫혀라!");
                        break;
                    case R.id.tv_my_notice_logout:
                        giveMeLogout();
                        mListener.onClick("닫혀라!");
                        Toast.makeText(getActivity(),"로그아웃 되었습니다.",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.tv_my_change_my_page:
                        Intent intent6 = new Intent(view.getContext(), ModifyInformActivity.class);
                        intent6.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        view.getContext().startActivity(intent6);
                        break;
                    case R.id.getpoint:
                        Log.e("읭","읭");
                        giveMeWelcomePoint();
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

        TextView tv_my_notice_login = (TextView)view.findViewById(R.id.tv_my_notice_login);
        tv_my_notice_login.setOnClickListener(onClickListener);

        TextView tv_my_notice_logout = (TextView)view.findViewById(R.id.tv_my_notice_logout);
        tv_my_notice_logout.setOnClickListener(onClickListener);

        welcome_point.setOnClickListener(onClickListener);
        tv_my_change_my_page.setOnClickListener(onClickListener);
        getpoint.setOnClickListener(onClickListener);
    }

    void init()
    {
        tv_my_major=view.findViewById(R.id.tv_my_major);
        tv_my_adress = view.findViewById(R.id.tv_my_adress);
        tv_my_name = view.findViewById(R.id.tv_my_name);
        tv_numeric_point = view.findViewById(R.id.tv_numeric_point);
        tv_my_change_my_page=view.findViewById(R.id.tv_my_change_my_page);
        give_me_welcome_point=view.findViewById(R.id.give_me_welcome_point);
        my_point = view.findViewById(R.id.my_point);
        welcome_point=view.findViewById(R.id.welcome_point);
        getpoint=view.findViewById(R.id.getpoint);
    }

    void giveMyImform()
    {
        TokenManager tm = TokenManager.getInstance();
        String token = tm.getToken(getActivity());
        Log.e("토큰?",token);
        RetrofitService networkService = RetrofitHelper.create();
        networkService.mypage(token).enqueue(new Callback<MyPage>(){
            @Override
            public void onResponse(Call<MyPage > call, Response<MyPage> response)
            {
                if(response.isSuccessful())
                {
                    tv_my_major.setText(response.body().getMajor());
                    tv_my_adress.setText(response.body().getId());
                    tv_my_name.setText(response.body().getName());
                }

            }
            @Override
            public void onFailure(Call<MyPage> call, Throwable t) {

            }
        });
    }
    void giveMyPoint()
    {
        TokenManager tm = TokenManager.getInstance();
        String token = tm.getToken(getActivity());
        Log.e("읭",token);
        RetrofitService networkService = RetrofitHelper.create();
        networkService.sumpoint(token).enqueue(new Callback<List<SumPoint> >(){
            @Override
            public void onResponse(Call<List<SumPoint>> call, Response<List<SumPoint>> response)
            {
                if(response.isSuccessful())
                {
                   tv_numeric_point.setText(response.body().get(0).getPoint()+"");
                }

            }
            @Override
            public void onFailure(Call<List<SumPoint>> call, Throwable t) {

            }
        });
    }
    void giveMeLogout()
    {
        TokenManager tm = TokenManager.getInstance();
        tm.putToken(getActivity(),null);
    }
    void checkWelcomePoint()
    {
        TokenManager tm = TokenManager.getInstance();
        String token = tm.getToken(getActivity());
        RetrofitService networkService = RetrofitHelper.create();
        networkService.checkGiftPoint(token).enqueue(new Callback<BooleanFuck>() {
            @Override
            public void onResponse(Call<BooleanFuck> call, Response<BooleanFuck> response) {
                if (response.isSuccessful()) {
                    if(response.body().getAns()==true)
                    {
                        my_point.setVisibility(View.GONE);
                        welcome_point.setVisibility(View.VISIBLE);

                    }
                    else
                    {
                        my_point.setVisibility(View.VISIBLE);
                        welcome_point.setVisibility(View.GONE);
                    }
                }

            }

            @Override
            public void onFailure(Call<BooleanFuck> call, Throwable t) {

            }
        });
    }
    void giveMeWelcomePoint()
    {
        TokenManager tm = TokenManager.getInstance();
        String token = tm.getToken(getActivity());
        RetrofitService networkService = RetrofitHelper.create();
        networkService.giftPoint(token).enqueue(new Callback<BooleanFuck>() {
            @Override
            public void onResponse(Call<BooleanFuck> call, Response<BooleanFuck> response) {
                if (response.isSuccessful()) {
                    if(response.body().getAns()==true)
                    {
                        my_point.setVisibility(View.VISIBLE);
                        welcome_point.setVisibility(View.GONE);
                        giveMyPoint();
                        Toast.makeText(getActivity(),"10포인트를 선물 받았습니다!",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        my_point.setVisibility(View.VISIBLE);
                        welcome_point.setVisibility(View.GONE);
                        Toast.makeText(getActivity(),"이미 선물 받았습니다.",Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<BooleanFuck> call, Throwable t) {

            }
        });
    }

}
