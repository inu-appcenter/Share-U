package com.inuappcenter.shareu.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.hbisoft.pickit.PickiT;
import com.hbisoft.pickit.PickiTCallbacks;
import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FileUploadActivity extends AppCompatActivity implements PickiTCallbacks {

    // 권한 요청 시에 사용됨.
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    // URI로부터 실제 path를 알아내는 데에 사용.
    PickiT pickiT;
    // 사진 다이얼로그 요청 시에 사용됨.
    private static int PICK_FROM_FILE = 9999;

    private ArrayList<String> dataList;
    public EditText edtv_select_subject,edtv_select_prof,edtv_content,edtv_file_name;
    public TextView tv_upload_file;
    public int one,two,three,four;
    public RetrofitService service;
    RequestBody title,subjectName,profName,content;
    public MultipartBody.Part filePart;
    Boolean file_upload_check=false;
    MediaType type = MediaType.parse("multipart/form-data");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_file_register);
        init();

        edtv_select_subject.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                check();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        edtv_select_prof.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                check();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        edtv_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                check();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        tv_upload_file.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                check();
                if(one>=1 && two>=1 &&three>=1 &&four>=1 && file_upload_check==true)
                {
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
                    Snackbar.make(FileUploadActivity.this.findViewById(R.id.root), "모든 내용을 채워주세요!", Snackbar.LENGTH_SHORT).show();
                }
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
                        //Log.e("메시지",response.body().get(i).getSubjectName()+"");
                        dataList.add(response.body().get(i).getSubjectName());

                    }

                }
                //Log.e("힝",dataList.size()+"");

            }
            @Override
            public void onFailure(Call<List<com.inuappcenter.shareu.model.File>> call, Throwable t) {

                //Toast.makeText(getApplicationContext(), "실패지렁", Toast.LENGTH_SHORT).show();
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
                        //Log.e("메시지",response.body().get(i).getProfName()+"");
                        dataList.add(response.body().get(i).getProfName());

                    }

                }
                //Log.e("힝",dataList.size()+"");

            }
            @Override
            public void onFailure(Call<List<com.inuappcenter.shareu.model.File>> call, Throwable t) {

                //Toast.makeText(getApplicationContext(), "실패지렁", Toast.LENGTH_SHORT).show();
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
        // PickiT 초기화
        pickiT = new PickiT(this, this);
        //리스너랑 콜백이랑 같다.
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FROM_FILE && resultCode == RESULT_OK) {
            pickiT.getPath(data.getData(), Build.VERSION.SDK_INT);
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
     * @param //uri 업로드할 파일의 uri.
     */
    private void uploadFile(String path,String desc) {
        service = RetrofitHelper.create();
        type = MediaType.parse("multipart/form-data");
        // POST의 file 부분 생성
        File imageFile = new File(path);
        RequestBody reqFile = RequestBody.create(type, imageFile);
        filePart = MultipartBody.Part.createFormData("userfile", imageFile.getName(), reqFile);

        Snackbar.make(FileUploadActivity.this.findViewById(R.id.root), "파일 업로드가 완료되었습니다.", Snackbar.LENGTH_SHORT).show();
        file_upload_check=true;
        check();
    }

    void init()
    {
        edtv_file_name=(EditText)findViewById(R.id.edtv_file_name);
        edtv_select_subject = (EditText) findViewById(R.id.edtv_select_subject);
        edtv_select_prof = (EditText) findViewById(R.id.edtv_select_prof);
        edtv_content = (EditText)findViewById(R.id.edtv_content);
        tv_upload_file = (TextView)findViewById(R.id.tv_upload_file);
    }
    void check()
    {
        if(edtv_file_name.getText().toString().trim().length()==0)
        {
            one=0;
        }
        else
        {
            one = edtv_file_name.getText().toString().length();
        }
        if(edtv_select_subject.toString().trim().length()==0)
        {
            two=0;
        }
        else
        {
            two = edtv_select_subject.getText().toString().length();
        }
        if(edtv_select_prof.getText().toString().trim().length()==0)
        {
            three=0;
        }
        else
        {
            three = edtv_select_prof.getText().toString().length();
        }
        if(edtv_content.getText().toString().trim().length()==0)
        {
            four=0;
        }
        else
        {
            four = edtv_content.getText().toString().length();
        }
        Log.e("변신!",one+" "+two+" "+three+" "+four);
        if(one>=1 && two>=1 &&three>=1 &&four>=1 && file_upload_check==true)
        {
            tv_upload_file.setBackgroundResource(R.color.Iris);
        }
        else
        {
            tv_upload_file.setBackgroundResource(R.color.gray);
        }

    }
    /**
     * PickiT의 파일 복사가 시작되었을 때에 호출됨.
     */

    @Override
    public void PickiTonStartListener() {
        Log.d("UploadImageActivity", "Cache file generation started!");
    }

    /**
     * PickiT의 파일 복사가 진행중일 때에 호출됨.
     * @param progress 진행률 (0-100).
     */
    @Override
    public void PickiTonProgressUpdate(int progress) {
        Log.d("UploadImageActivity", "Cache file generation in progress: " + progress);
    }

    /**
     * PickiT의 파일 복사가 끝났을 때, 혹은 복사가 필요 없을 때에는 파일 선택 직후에 호출됨.
     * @param path 파일 경로
     * @param wasDriveFile 드라이브에 있는(로컬이 아닌) 파일이었는가?
     * @param wasUnknownProvider 파일 제공자를 알 수 없었는가?
     * @param wasSuccessful 성공적이었는가?
     * @param Reason 만약 성공적이지 않았을 때, 그 이유.
     */
    @Override
    public void PickiTonCompleteListener(String path, boolean wasDriveFile, boolean wasUnknownProvider, boolean wasSuccessful, String Reason) {
        Log.d("UploadImageActivity", "Cache file generation completed!");

        if (wasSuccessful) {
            Log.d("UploadImageActivity", "The path: " + path);
            uploadFile(path, "success ><");
        }
        else {
            Log.e("UploadImageActivity", "Path resolutuon failed: " + Reason);
        }
    }

}

