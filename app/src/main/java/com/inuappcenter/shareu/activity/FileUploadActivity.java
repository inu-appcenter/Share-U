package com.inuappcenter.shareu.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.model.Code;
import com.inuappcenter.shareu.model.Major;
import com.inuappcenter.shareu.model.Notice;
import com.inuappcenter.shareu.model.SuperiorLecture;
import com.inuappcenter.shareu.model.profName;
import com.inuappcenter.shareu.model.subjectName;
import com.inuappcenter.shareu.recycler.MajorAdapter;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;
import com.inuappcenter.shareu.service.UriHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FileUploadActivity extends AppCompatActivity {

    // 권한 요청 시에 사용됨.
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    // 사진 다이얼로그 요청 시에 사용됨.
    private static int PICK_FROM_FILE = 9999;

    private ArrayList<String> dataList;
    public String sibal;
    public AutoCompleteTextView edtv_select_subject;
    public AutoCompleteTextView edtv_select_prof;
    public EditText edtv_content;
    public TextView tv_upload_file;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_file_register);
        edtv_select_subject = (AutoCompleteTextView) findViewById(R.id.edtv_select_subject);
        edtv_select_prof = (AutoCompleteTextView) findViewById(R.id.edtv_select_prof);
        edtv_content = (EditText)findViewById(R.id.edtv_content);
        tv_upload_file = (TextView)findViewById(R.id.tv_upload_file);
        tv_upload_file.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //Snackbar.make(FileUploadActivity.this.findViewById(R.id.root), "모든 내용을 채워주세요!", Snackbar.LENGTH_SHORT).show();
            }

        });
        RetrofitService networkService = RetrofitHelper.create();
        networkService.getSubjectName("").enqueue(new Callback<List<com.inuappcenter.shareu.model.File>>(){
            @Override
            public void onResponse(Call<List<com.inuappcenter.shareu.model.File> > call, Response<List<com.inuappcenter.shareu.model.File>> response)
            {
                if(response.isSuccessful())
                {
                    dataList=new ArrayList<>();
                    for(int i=0;i<response.body().size();i++)
                    {
                        Log.e("메시지",response.body().get(i).getSubjectName()+"");
                        dataList.add(response.body().get(i).getSubjectName());

                    }

                }
                // AutoCompleteTextView 에 아답터를 연결한다.
                edtv_select_subject.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_dropdown_item_1line,  dataList ));
                Log.e("힝",dataList.size()+"");

            }
            @Override
            public void onFailure(Call<List<com.inuappcenter.shareu.model.File>> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "실패지렁", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });


        RetrofitService networkService2 = RetrofitHelper.create();
        networkService2.getProfName("").enqueue(new Callback<List<com.inuappcenter.shareu.model.File>>(){
            @Override
            public void onResponse(Call<List<com.inuappcenter.shareu.model.File> > call, Response<List<com.inuappcenter.shareu.model.File>> response)
            {
                if(response.isSuccessful())
                {
                    dataList=new ArrayList<>();
                    for(int i=0;i<response.body().size();i++)
                    {
                        Log.e("메시지",response.body().get(i).getProfName()+"");
                        dataList.add(response.body().get(i).getProfName());

                    }

                }
                // AutoCompleteTextView 에 아답터를 연결한다.
                edtv_select_prof.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_dropdown_item_1line,  dataList ));
                Log.e("힝",dataList.size()+"");

            }
            @Override
            public void onFailure(Call<List<com.inuappcenter.shareu.model.File>> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "실패지렁", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

        // 권한내놔!
        giveMePermissions();

        // 버튼 클릭 리스너 설정 부분
        findViewById(R.id.img_btn_file_upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickFile();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FROM_FILE && resultCode == RESULT_OK) {
            uploadFile(data.getData());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_EXTERNAL_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                // good
            } else {
                Snackbar.make(FileUploadActivity.this.findViewById(R.id.root), "권한내놔!!!!!!!!", Snackbar.LENGTH_LONG).show();
            }
        }
    }

    /**
     * 외장 저장소 읽기/쓰기 권한을 요청함.
     */
    private void giveMePermissions() {
        int readPermission = ActivityCompat.checkSelfPermission(FileUploadActivity.this, PERMISSIONS_STORAGE[0]);
        int writePermission = ActivityCompat.checkSelfPermission(FileUploadActivity.this, PERMISSIONS_STORAGE[1]);

        if (writePermission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(FileUploadActivity.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }

    /**
     * 이미지 선택 창을 띄움. 그 결과는 선택 후에 onActivityResult 메소드로 전달될 것.
     */
    private void pickFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");

        startActivityForResult(intent, PICK_FROM_FILE);
    }

    /**
     * 파일의 서버에 업로드함.
     * @param uri 업로드할 파일의 uri.
     */
    private void uploadFile(Uri uri) {
        RetrofitService service = RetrofitHelper.create();
        MediaType type = MediaType.parse("multipart/form-data");



        // POST의 file 부분 생성
        File imageFile = new File(UriHelper.getPath(this, uri));
        RequestBody reqFile = RequestBody.create(type, imageFile);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("userfile", imageFile.getName(), reqFile);

        Snackbar.make(FileUploadActivity.this.findViewById(R.id.root), "파일 업로드가 완료되었습니다.", Snackbar.LENGTH_SHORT).show();

        // POST의 description 부분 생성
        EditText edtv_file_name = findViewById(R.id.edtv_file_name);
        RequestBody title = RequestBody.create(type, edtv_file_name.getText()+"");
        RequestBody subjectName = RequestBody.create(type,edtv_select_subject.getText()+"" );
        RequestBody profName = RequestBody.create(type,edtv_select_prof.getText()+"");
        RequestBody content = RequestBody.create(type, edtv_content+"");

        int one = edtv_file_name.getText().toString().length();
        int two = edtv_select_subject.getText().toString().length();
        int three = edtv_select_prof.getText().toString().length();
        int four = edtv_content.getText().toString().length();
        if(one>=1 && two>=1 &&three>=1 &&four>=1)
        {
            tv_upload_file.setBackgroundResource(R.color.Iris);
            tv_upload_file.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    // 이제 올리기
                    service.uploadImage(title,subjectName,profName,content,filePart).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            Snackbar.make(FileUploadActivity.this.findViewById(R.id.root), "등록에 성공하였습니다.", Snackbar.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Snackbar.make(FileUploadActivity.this.findViewById(R.id.root), "등록에 실패하였습니다.", Snackbar.LENGTH_SHORT).show();
                            t.printStackTrace();
                        }
                    });
                }
            });
        }
        else
        {
            //Snackbar.make(FileUploadActivity.this.findViewById(R.id.root), "모든 내용을 채워주세요!", Snackbar.LENGTH_SHORT).show();
        }
    }


}

