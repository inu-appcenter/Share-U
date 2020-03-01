package com.inuappcenter.shareu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.fragment.MainFragment;
import com.inuappcenter.shareu.fragment.MajorFragment;
import com.inuappcenter.shareu.fragment.SearchAllResultFragment;
import com.inuappcenter.shareu.fragment.SearchNoResultFragment;
import com.inuappcenter.shareu.my_class.Document;
import com.inuappcenter.shareu.my_class.Notice;
import com.inuappcenter.shareu.my_class.SuperiorLecture;
import com.inuappcenter.shareu.my_class.TokenManager;
import com.inuappcenter.shareu.my_interface.OnItemClick;
import com.inuappcenter.shareu.recycler.NoticeAdapter;
import com.inuappcenter.shareu.recycler.SuperiorLectureAdapter2;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClick {

    //Drawer 처리
    DrawerLayout drawer_my_page;
   /* private ArrayList<Notice> dataList;
    private ViewPager viewPager ;*/
    private EditText etv_search;
    private ImageButton etv_search_click;
    private FragmentManager fragmentManager;
    private ImageView btn_backpress;

    private MainFragment mainFragment;
    private SearchAllResultFragment searchAllResultFragment;
    private SearchNoResultFragment searchNoResultFragment;

    private FragmentTransaction transaction;

    private View drawer_login;
    private View drawer_logout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        check_login();
        //TODO : 프래그먼트 전환되면 햄버거 바 백스페이스로 바꾸고 마이페이지 버튼도 비활성화, 원래대로 돌아오면 다시 살려놓자.
        // 사라져야 하는거 drawer_my_page랑 btn_left_bar_main,btn_backpress


        fragmentManager = getSupportFragmentManager();
        mainFragment = new MainFragment();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.layout_frame_main,mainFragment);
        transaction.commit();

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_left_bar_main :
                        Intent intent = new Intent(getApplicationContext(),MajorActivity.class);
                        startActivity(intent);
                        break ;
                    case R.id.btn_my_page_main :
                        drawer_my_page.openDrawer(GravityCompat.END);
                        if(check_login())
                        {
                            drawer_login.setVisibility(View.VISIBLE);
                            drawer_logout.setVisibility(View.GONE);
                        }
                        else
                        {
                            drawer_login.setVisibility(View.GONE);
                            drawer_logout.setVisibility(View.VISIBLE);
                        }
                        break ;
                    case R.id.fab_main:
                        Intent intent3 = new Intent(getApplicationContext(), FileUploadActivity.class);
                        startActivity(intent3);
                        break ;
                    case R.id.etv_search_click:
                        //Toast.makeText(getApplicationContext(),"냐냐냔냥",Toast.LENGTH_SHORT).show();
                        //최신자료는 우수자료 상관없이 다 뜸
                        RetrofitService networkService = RetrofitHelper.create();
                        networkService.documentTop5DateList(etv_search.getText()+"").enqueue(new Callback<List<Document>>() {
                            @Override
                            public void onResponse(Call<List<Document>> call, Response<List<Document>> response) {
                                if (response.isSuccessful()) {

                                    if(response.body().size()==0)
                                    {
                                        fragmentManager = getSupportFragmentManager();
                                        searchNoResultFragment = new SearchNoResultFragment();
                                        transaction = fragmentManager.beginTransaction();
                                        transaction.replace(R.id.layout_frame_main,searchNoResultFragment);
                                        transaction.commit();
                                    }
                                    else
                                    {
                                        fragmentManager = getSupportFragmentManager();
                                        searchAllResultFragment=new SearchAllResultFragment();
                                        transaction = fragmentManager.beginTransaction();
                                        transaction.replace(R.id.layout_frame_main,searchAllResultFragment);
                                        transaction.commit();
                                    }
                                }

                            }

                            @Override
                            public void onFailure(Call<List<Document>> call, Throwable t) {

                            }
                        });
                        break;
                        //메인화면 검색
                }

            }
        } ;
        //과목 선택 처리
        Button btn_left_bar_main = (Button) findViewById(R.id.btn_left_bar_main) ;
        btn_left_bar_main.setOnClickListener(onClickListener) ;
        //drawer 처리
        Button btn_my_page_main = (Button)findViewById(R.id.btn_my_page_main);
        btn_my_page_main.setOnClickListener(onClickListener) ;

        FloatingActionButton fab_main = (FloatingActionButton)findViewById(R.id.fab_main);
        fab_main.setOnClickListener(onClickListener);

        etv_search_click.setOnClickListener(onClickListener);

    }
    void init()
    {
        etv_search=findViewById(R.id.etv_search);
        etv_search_click = findViewById(R.id.etv_search_click);
        btn_backpress = findViewById(R.id.btn_backpress);

        //drawer 처리
        drawer_login = (View)findViewById(R.id.drawer_login);
        drawer_logout = (View)findViewById(R.id.drawer_logout);
        drawer_my_page = (DrawerLayout)findViewById(R.id.include_drawer_my_page);
        drawer_my_page.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    boolean check_login()
    {
        TokenManager tm= TokenManager.getInstance();
        String token = tm.getToken(this);
        if(token!=null)
        {
            return true;
        }
        else
        {
            return false;
        }
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
    public void closeDrawer()
    {
        drawer_my_page.closeDrawer(GravityCompat.END);
    }

    @Override
    public void onClick(String value) {
        //drawer를 닫을꺼야
        drawer_my_page.closeDrawer(GravityCompat.END);
    }

    @Override
    public void onClick2(String value) {

    }

    void giveMeResult()
    {

    }

}



