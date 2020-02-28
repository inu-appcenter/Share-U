package com.inuappcenter.shareu.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.my_class.Fuck;
import com.inuappcenter.shareu.my_class.Login;
import com.inuappcenter.shareu.my_class.MyInform;
import com.inuappcenter.shareu.my_class.SumPoint;
import com.inuappcenter.shareu.my_class.TokenManager;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyInformActivity extends AppCompatActivity {

    private EditText etv_id,etv_name,etv_phone,etv_major,etv_now_passwd,etv_modify_passwd,etv_fix_modify_passwd;
    TextView tv_modify,tv_numeric_point;
    private TSnackbar snackbar;
    private TokenManager tm = TokenManager.getInstance();
    private String token;
    private ImageView btn_backpress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_inform);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();

        giveMyPoint();
        giveMyInform();
        listenEvent();

    }
    void init()
    {
        etv_id=findViewById(R.id.etv_id);
        etv_name= findViewById(R.id.etv_name);
        etv_phone = findViewById(R.id.etv_phone);
        etv_major = findViewById(R.id.etv_major);
        etv_now_passwd = findViewById(R.id.etv_now_passwd);
        etv_modify_passwd=findViewById(R.id.etv_modify_passwd);
        etv_fix_modify_passwd=findViewById(R.id.etv_fix_modify_passwd);
        tv_modify=findViewById(R.id.tv_modify);
        tv_numeric_point=findViewById(R.id.tv_numeric_point);
        token = tm.getToken(this);
        btn_backpress=findViewById(R.id.btn_backpress);
    }

    boolean check()
    {
        if(etv_id.getText().length()>=1 && etv_name.getText().length()>=1 && etv_phone.getText().length()>=1 &&
        etv_major.getText().length()>=1 && etv_now_passwd.getText().length()>=1 && etv_modify_passwd.getText().length()>=1 &&
        etv_fix_modify_passwd.getText().length()>=1)
        {
            String a = etv_fix_modify_passwd.getText()+"";
            if(a.equals(etv_modify_passwd.getText()+""))
            {
                return true;
            }
            else
            {
                snackbar = TSnackbar.make(findViewById(android.R.id.content),"변경 비밀번호가 서로 일치하지 않습니다.",TSnackbar.LENGTH_SHORT);
                snackbar.setActionTextColor(Color.WHITE);
                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(Color.parseColor("#574FBA"));
                TextView textView = (TextView)snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
                textView.setTextColor(Color.WHITE);
                snackbar.show();
                return false;
            }
        }
        else
        {
            snackbar = TSnackbar.make(findViewById(android.R.id.content),"모든 항목을 채워주세요!",TSnackbar.LENGTH_SHORT);
            snackbar.setActionTextColor(Color.WHITE);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.parseColor("#574FBA"));
            TextView textView = (TextView)snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();
            return false;
        }
    }
    void listenEvent()
    {
        tv_modify.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //글자수랑 바꿀 비밀번호는 체크했다.
                if(check())
                {
                    RetrofitService networkService = RetrofitHelper.create();
                    networkService.login(etv_id.getText() + "", etv_now_passwd.getText() + "").enqueue(new Callback<Login>() {
                        @Override
                        public void onResponse(Call<Login> call, Response<Login> response) {
                            if (response.isSuccessful()) {

                                allowModify();

                            }
                            else
                            {
                                snackbar = TSnackbar.make(findViewById(android.R.id.content),"현재 비밀번호가 일치하지 않습니다.",TSnackbar.LENGTH_SHORT);
                                snackbar.setActionTextColor(Color.WHITE);
                                View snackbarView = snackbar.getView();
                                snackbarView.setBackgroundColor(Color.parseColor("#574FBA"));
                                TextView textView = (TextView)snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
                                textView.setTextColor(Color.WHITE);
                                snackbar.show();

                            }
                        }

                        @Override
                        public void onFailure(Call<Login> call, Throwable t) {

                        }
                    });
                }

            }
        });
        btn_backpress.setOnClickListener(v->finish());


    }

    void giveMyPoint()
    {
        RetrofitService networkService = RetrofitHelper.create();
        networkService.sumpoint(token).enqueue(new Callback<List<SumPoint>>(){
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
    void giveMyInform()
    {
        RetrofitService networkService = RetrofitHelper.create();
        networkService.changeBefore(token).enqueue(new Callback<MyInform>() {
            @Override
            public void onResponse(Call<MyInform> call, Response<MyInform> response) {
                if (response.isSuccessful()) {
                    etv_id.setText(response.body().getId());
                    etv_name.setText(response.body().getName());
                    etv_major.setText(response.body().getMajor());
                    etv_phone.setText(response.body().getTel());
                }

            }

            @Override
            public void onFailure(Call<MyInform> call, Throwable t) {

            }
        });
    }

    void allowModify()
    {
        RetrofitService networkService = RetrofitHelper.create();
        networkService.changeInfo(etv_id.getText()+"",etv_now_passwd.getText()+"",
                etv_phone.getText()+"",etv_major.getText()+"",etv_name.getText()+"",
                etv_modify_passwd.getText()+"",token).enqueue(new Callback<Fuck>() {
            @Override
            public void onResponse(Call<Fuck> call, Response<Fuck> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"내 정보 수정이 완료되었습니다."+'\n'+"바뀐 정보는 다시 로그인 후 확인 가능합니다.",Toast.LENGTH_LONG).show();
                    finish();
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
