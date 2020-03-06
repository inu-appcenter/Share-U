package com.inuappcenter.shareu.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.my_class.Fuck;
import com.inuappcenter.shareu.my_class.TokenManager;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindPassswdActivity extends Activity {

    private TextView tv_find_passwd;
    private ImageButton btn_backpress;
    private TextView etv_name,etv_id;
    private TSnackbar snackbar;
    private TokenManager tm = TokenManager.getInstance();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        setContentView(R.layout.activity_find_passwd);
        init();

    }

    @Override
    protected void onResume() {
        super.onResume();
        listenGo();
    }

    void init()
    {
        tv_find_passwd=findViewById(R.id.tv_find_passwd);
        btn_backpress = findViewById(R.id.btn_backpress);
        etv_name = findViewById(R.id.etv_name);
        etv_id = findViewById(R.id.etv_id);

    }
    void listenGo()
    {
        tv_find_passwd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //항목 모두 안 채웠을 때, 아이디 비밀번호 일치 안할 때 예외처리
                if(check())
                {
                    TokenManager tm = TokenManager.getInstance();
                    String token = tm.getToken(getApplicationContext());
                    RetrofitService networkService = RetrofitHelper.create();
                    networkService.tmpPasswd(etv_id.getText() + "", etv_name.getText() + "").enqueue(new Callback<Fuck>() {
                        @Override
                        public void onResponse(Call<Fuck> call, Response<Fuck> response) {
                            if (response.isSuccessful()) {
                               if(response.body().getAns().equals("success"))
                               {
                                   Intent intent =new Intent(getApplicationContext(),GetPasswdActivity.class);
                                   startActivity(intent);
                                   finish();

                               }
                               else
                               {
                                   Toast.makeText(getApplicationContext(),"아이디 또는 비밀번호를 확인해주세요.",Toast.LENGTH_SHORT).show();
                               }

                            }

                        }

                        @Override
                        public void onFailure(Call<Fuck> call, Throwable t) {
                            Intent intent = new Intent(getApplicationContext(), ServerFailActivity.class);
                            startActivity(intent);
                        }
                    });
                }


            }
        });
        btn_backpress.setOnClickListener(v->finish());
    }
    boolean check()
    {
        if(etv_id.getText().length()>=1 && etv_name.getText().length()>=1)
        {
            return true;
        }
        else
        {
            Toast.makeText(getApplicationContext(),"모든 항목을 채워주세요 !",Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if(snackbar!=null && snackbar.isShown())
        {
            snackbar.dismiss();;
        }
    }

}
