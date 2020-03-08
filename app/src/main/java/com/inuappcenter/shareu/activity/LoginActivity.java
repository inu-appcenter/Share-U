package com.inuappcenter.shareu.activity;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Annotation;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.R.id;
import com.androidadvance.topsnackbar.TSnackbar;
import com.bumptech.glide.load.HttpException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.my_class.Fuck;
import com.inuappcenter.shareu.my_class.Login;
import com.inuappcenter.shareu.my_class.RestError;
import com.inuappcenter.shareu.my_class.TokenManager;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import org.json.JSONObject;

import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import okhttp3.internal.http2.ErrorCode;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextView tv_login_go,tv_register_go;
    private EditText etv_id;
    private EditText etv_passwd;
    private ImageView btn_backpress;
    private TSnackbar snackbar;
    private TextView tv_find_passwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        init();

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.tv_login_go:
                        giveLogin();
                        break;
                    case R.id.btn_backpress:
                        finish();
                        break;
                    case R.id.tv_find_passwd:
                        Intent intent = new Intent(getApplicationContext(),FindPassswdActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.tv_register_go:
                        Intent intent2 = new Intent(getApplicationContext(),RegisterActivity.class);
                        startActivity(intent2);
                        break;
                }


            }
        };

        tv_login_go.setOnClickListener(onClickListener);
        btn_backpress.setOnClickListener(onClickListener);
        tv_find_passwd.setOnClickListener(onClickListener);
        tv_register_go.setOnClickListener(onClickListener);
    }

    void init()
    {
        tv_login_go = findViewById(R.id.tv_login_go);
        etv_id = findViewById(R.id.etv_id);
        etv_passwd = findViewById(R.id.etv_passwd);
        btn_backpress = findViewById(R.id.btn_backpress);
        tv_find_passwd=findViewById(R.id.tv_find_passwd);
        tv_register_go=findViewById(R.id.tv_register_go);
    }
    void giveLogin()
    {
        RetrofitService networkService = RetrofitHelper.create();
        networkService.login(etv_id.getText() + "", etv_passwd.getText() + "").enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.isSuccessful()) {
                    TokenManager tm = TokenManager.getInstance();
                    tm.putToken(getApplicationContext(), response.body().getToken());
                    Toast.makeText(getApplicationContext(),"로그인 되었습니다.",Toast.LENGTH_SHORT).show();
                    finish();
                    /*if(response.body().getAns().equals(etv_passwd.getText()))
                    {
                        snackbar = TSnackbar.make(findViewById(android.R.id.content),"비밀번호를 확인해주세요!",TSnackbar.LENGTH_SHORT);
                        snackbar.setActionTextColor(Color.WHITE);
                        View snackbarView = snackbar.getView();
                        snackbarView.setBackgroundColor(Color.parseColor("#574FBA"));
                        TextView textView = (TextView)snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
                        textView.setTextColor(Color.WHITE);
                        snackbar.show();
                    }
                    else
                    {
                        TokenManager tm = TokenManager.getInstance();
                        tm.putToken(getApplicationContext(), response.body().getToken());
                        Toast.makeText(getApplicationContext(),"로그인 되었습니다.",Toast.LENGTH_SHORT).show();
                        finish();
                    }*/
                }
                else
                {
                    snackbar = TSnackbar.make(findViewById(android.R.id.content),"아이디 또는 비밀번호를 확인해주세요!",TSnackbar.LENGTH_SHORT);
                    snackbar.setActionTextColor(Color.WHITE);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(Color.parseColor("#574FBA"));
                    TextView textView = (TextView)snackbarView.findViewById(id.snackbar_text);
                    textView.setTextColor(Color.WHITE);
                    snackbar.show();
                    /*try
                    {
                        String result = response.errorBody().string();
                        Gson gson = new GsonBuilder().create();
                        Fuck  myerror = gson.fromJson(result, Fuck.class);
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace ();
                    }*/

                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Intent intent = new Intent(getApplicationContext(), ServerFailActivity.class);
                startActivity(intent);
            }
        });
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
