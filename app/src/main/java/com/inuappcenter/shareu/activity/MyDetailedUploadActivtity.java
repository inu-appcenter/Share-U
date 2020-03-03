package com.inuappcenter.shareu.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;
import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.my_class.Fuck;
import com.inuappcenter.shareu.my_class.TokenManager;
import com.inuappcenter.shareu.my_class.sendBeforeModify;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyDetailedUploadActivtity extends AppCompatActivity {

    private EditText edtv_file_name,edtv_content;
    private TextView edtv_select_subject,edtv_select_prof,tv_uploaded_file_name,tv_upload_file;
    private ImageView img_file;
    private int key;
    private TSnackbar snackbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_detailed_upload_activity);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        initializeView();
        Intent intent = getIntent();
        key=intent.getExtras().getInt("key");

    }
    private void initializeView() {

        edtv_file_name=findViewById(R.id.edtv_file_name);
        edtv_select_subject=findViewById(R.id.edtv_select_subject);
        edtv_select_prof=findViewById(R.id.edtv_select_prof);
        edtv_content=findViewById(R.id.edtv_content);
        tv_uploaded_file_name=findViewById(R.id.tv_uploaded_file_name);
        img_file=findViewById(R.id.img_file);

        View.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_backpress:
                        finish();
                        break ;
                    case R.id.tv_upload_file:
                        sendList();
                        Intent intent = new Intent(getApplicationContext(),ModifySuccessActivity.class);
                        startActivity(intent);
                        finish();
                        break;

                }

            }
        };

        tv_upload_file=findViewById(R.id.tv_upload_file);
        tv_upload_file.setOnClickListener(onClickListener);

        ImageButton btn_detailed_notice_backpress =(ImageButton)findViewById(R.id.btn_backpress);
        btn_detailed_notice_backpress.setOnClickListener(onClickListener);

    }

    @Override
    protected void onResume() {
        super.onResume();
        giveMeList();


    }

    void giveMeList()
    {
        RetrofitService networkService = RetrofitHelper.create();
        networkService.sendBeforeModify(key).enqueue(new Callback<List<sendBeforeModify>>() {

            @Override
            public void onResponse(Call<List<sendBeforeModify>> call, Response<List<sendBeforeModify>> response) {
                if (response.isSuccessful()) {
                    edtv_file_name.setText(response.body().get(0).getTitle());
                    tv_uploaded_file_name.setText(response.body().get(0).getFileName());
                    edtv_content.setText(response.body().get(0).getContent());
                    edtv_select_prof.setText(response.body().get(0).getProfName());
                    edtv_select_subject.setText(response.body().get(0).getSubjectName());
                    String type = response.body().get(0).getExtension();
                    findImage(type);
                }

            }

            @Override
            public void onFailure(Call<List<sendBeforeModify>> call, Throwable t) {

            }
        });
    }
    void findImage(String type)
    {
        if(type.equals("PPT") || type.equals("PPTX")||
                type.equals("ppt") || type.equals("pptx")
        )
        {
            img_file.setImageResource(R.drawable.ppt);
        }
        else if(type.equals("HWP") ||type.equals("hwp") )
        {
            img_file.setImageResource(R.drawable.korean);
        }
        else if(type.equals("DOC") || type.equals("DOCX")
                ||type.equals("doc") || type.equals("docx")
        )
        {
            img_file.setImageResource(R.drawable.word);
        }
        else if(type.equals("AI")
                ||type.equals("ai")
        )
        {
            img_file.setImageResource(R.drawable.ai);
        }
        else if(type.equals("PS")
                ||type.equals("ps")
        )
        {
            img_file.setImageResource(R.drawable.ps);
        }

        else if(type.equals("JPEG") || type.equals("JPG")||
                type.equals("jpeg") || type.equals("jpg")
        )
        {
            img_file.setImageResource(R.drawable.jpeg);
        }
        else if(type.equals("PNG") || type.equals("png") )
        {
            img_file.setImageResource(R.drawable.png);
        }
        else if(type.equals("XLS")||type.equals("XLSX")||
                type.equals("XLSM") || type.equals("CSV")||
                type.equals("xls")||type.equals("xlsx")||
                type.equals("xlsm") || type.equals("csv")
        )
        {
            img_file.setImageResource(R.drawable.excel);
        }
        else if(type.equals("MP3")||type.equals("mp3"))
        {
            img_file.setImageResource(R.drawable.mp3);
        }
        else if(type.equals("ZIP") ||type.equals("zip")  )
        {
            img_file.setImageResource(R.drawable.zip);
        }
        else
        {
            img_file.setImageResource(R.drawable.file);
        }
    }
    void sendList()
    {
        if(edtv_content.getText().length()<0 || edtv_file_name.getText().length()<0)
        {
            snackbar = TSnackbar.make(findViewById(android.R.id.content),"모든 항목을 채워주세요!",TSnackbar.LENGTH_SHORT);
            snackbar.setActionTextColor(Color.WHITE);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.parseColor("#574FBA"));
            TextView textView = (TextView)snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();
        }
        else if(edtv_file_name.getText().length()>20)
        {
            snackbar = TSnackbar.make(findViewById(android.R.id.content),"제목을 20자 이하로 채워주세요!",TSnackbar.LENGTH_SHORT);
            snackbar.setActionTextColor(Color.WHITE);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.parseColor("#574FBA"));
            TextView textView = (TextView)snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();
        }
        else if(edtv_content.getText().length()<30)
        {
            snackbar = TSnackbar.make(findViewById(android.R.id.content),"내용을 30자 이상으로 채워주세요!",TSnackbar.LENGTH_SHORT);
            snackbar.setActionTextColor(Color.WHITE);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.parseColor("#574FBA"));
            TextView textView = (TextView)snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();
        }
        else if(edtv_content.getText().length()>200)
        {
                    /*progressSnackbar2.setText("내용을 30자 이상 채워주세요!");
                    progressSnackbar2.show();*/
            snackbar = TSnackbar.make(findViewById(android.R.id.content),"내용을 200자 이하로 채워주세요!",TSnackbar.LENGTH_SHORT);
            snackbar.setActionTextColor(Color.WHITE);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.parseColor("#574FBA"));
            TextView textView = (TextView)snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();
        }
        TokenManager tm = TokenManager.getInstance();
        String token = tm.getToken(this);
        RetrofitService networkService = RetrofitHelper.create();
        networkService.modify(key,token,edtv_file_name.getText()+"",edtv_content.getText()+"",
                edtv_select_subject.getText()+"",edtv_select_prof.getText()+"").enqueue(new Callback<Fuck>() {

            @Override
            public void onResponse(Call<Fuck> call, Response<Fuck> response) {
                if (response.isSuccessful()) {

                }

            }

            @Override
            public void onFailure(Call<Fuck> call, Throwable t) {

            }
        });
    }
}
