package com.inuappcenter.shareu.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;
import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.my_class.Code;
import com.inuappcenter.shareu.my_class.Fuck;
import com.inuappcenter.shareu.my_class.Major;
import com.inuappcenter.shareu.recycler.MajorAdapter;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;
import com.viethoa.models.AlphabetItem;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private TextView etv_id,etv_passwd,etv_check_passwd,etv_name,etv_phone,tv_modify;
    private String items[]=new String[200];
    private TSnackbar snackbar;
    private ImageView btn_backpress;
    private AutoCompleteTextView etv_major;
    private List<String> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        init();
        tv_modify.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                check();
            }
        });
        btn_backpress.setOnClickListener(v->finish());

    }

    @Override
    protected void onResume() {
        super.onResume();
        list=new ArrayList<String>();
        settingList();
        etv_major.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,list));
    }
    void check()
    {


        if(etv_id.getText().length()>0 && etv_passwd.getText().length()>0 &&
        etv_check_passwd.getText().length()>0 && etv_name.getText().length()>0 &&
        etv_phone.getText().length()>0)
        {
            String a = etv_check_passwd.getText()+"";

            if(a.equals(etv_passwd.getText()+""))
            {
                RetrofitService networkService = RetrofitHelper.create();
                networkService.signUp(etv_id.getText()+"",etv_passwd.getText()+"",etv_phone.getText()+"",
                        etv_major.getText()+"",etv_name.getText()+"").enqueue(new Callback<Fuck>() {
                    @Override
                    public void onResponse(Call<Fuck> call, Response<Fuck> response) {

                        if (response.isSuccessful()) {
                            Intent intent = new Intent(getApplicationContext(),RegisterAcceptActivity.class);
                            startActivity(intent);
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
            else
            {
                snackbar = TSnackbar.make(findViewById(android.R.id.content),"비밀번호가 서로 일치하지 않습니다.",TSnackbar.LENGTH_SHORT);
                snackbar.setActionTextColor(Color.WHITE);
                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(Color.parseColor("#574FBA"));
                TextView textView = (TextView)snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
                textView.setTextColor(Color.WHITE);
                snackbar.show();
            }
        }
        else
        {
            snackbar = TSnackbar.make(findViewById(android.R.id.content),"모든 항목을 채워주세요 !",TSnackbar.LENGTH_SHORT);
            snackbar.setActionTextColor(Color.WHITE);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.parseColor("#574FBA"));
            TextView textView = (TextView)snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();
        }
    }
    void init()
    {
        etv_id=findViewById(R.id.etv_id);
        etv_passwd=findViewById(R.id.etv_passwd);
        etv_check_passwd=findViewById(R.id.etv_check_passwd);
        etv_name=findViewById(R.id.etv_name);
        etv_phone=findViewById(R.id.etv_phone);
        tv_modify=findViewById(R.id.tv_modify);
        etv_major=findViewById(R.id.etv_major);
        btn_backpress=findViewById(R.id.btn_backpress);
    }
    @Override
    protected void onPause() {
        super.onPause();
        if(snackbar!=null && snackbar.isShown())
        {
            snackbar.dismiss();;
        }
    }
    private void settingList(){
        RetrofitService networkService = RetrofitHelper.create();
        networkService.getMajorList().enqueue(new Callback<List<Major>>() {
            @Override
            public void onResponse(Call<List<Major>> call, Response<List<Major>> response) {

                if (response.isSuccessful()) {
                    for(int i=0;i<response.body().size();i++)
                    {
                        list.add(response.body().get(i).getFirst());
                    }
                }


            }

            @Override
            public void onFailure(Call<List<Major>> call, Throwable t) {

            }
        });
    }
}
