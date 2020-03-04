package com.inuappcenter.shareu.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;
import com.hbisoft.pickit.PickiT;
import com.hbisoft.pickit.PickiTCallbacks;
import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.fragment.BottomSheetFragement;
import com.inuappcenter.shareu.fragment.BottomSheetFragment2;
import com.inuappcenter.shareu.my_class.Fuck;
import com.inuappcenter.shareu.my_class.TokenManager;
import com.inuappcenter.shareu.my_class.subjectName;
import com.inuappcenter.shareu.my_interface.OnItemClick;
import com.inuappcenter.shareu.recycler.BottomSheetAdapter;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FileUploadActivity extends AppCompatActivity implements  OnItemClick,PickiTCallbacks{

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
    private TSnackbar snackbar;
    private EditText edtv_content,edtv_file_name;
    private TextView edtv_select_subject,edtv_select_prof;
    private TextView tv_upload_file;
    private ImageButton img_btn_file_upload_on;
    private int one,two,three,four;
    private ArrayList<com.inuappcenter.shareu.my_class.subjectName> dataList;
    private ArrayList<com.inuappcenter.shareu.my_class.profName> dataList2;
    private RetrofitService service;
    private ResponseBody title,subjectName,profName,content;
    private MultipartBody.Part filePart;
    private Boolean file_upload_check=false;
    private MediaType type = MediaType.parse("multipart/form-data");
    private BottomSheetFragement dialog;
    private BottomSheetFragment2 dialog2;


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
            }

            @Override
            public void afterTextChanged(Editable editable) {
                check();
            }
        });
        edtv_select_prof.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                check();
            }
        });
        edtv_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                check();
            }
        });

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (view.getId())
                {
                    case R.id.tv_upload_file:
                        check();

                        if(one>20)
                        {
                            snackbar = TSnackbar.make(findViewById(android.R.id.content),"제목을 20자 이하로 채워주세요!",TSnackbar.LENGTH_SHORT);
                            snackbar.setActionTextColor(Color.WHITE);
                            View snackbarView = snackbar.getView();
                            snackbarView.setBackgroundColor(Color.parseColor("#574FBA"));
                            TextView textView = (TextView)snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
                            textView.setTextColor(Color.WHITE);
                            snackbar.show();
                        }
                        else if(four>200)
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
                        else if(!(one>=1 && two>=1 &&three>=1 &&four>=1 && file_upload_check==true))
                        {
                    /*progressSnackbar2.setText("내용을 30자 이상 채워주세요!");
                    progressSnackbar2.show();*/
                            snackbar = TSnackbar.make(findViewById(android.R.id.content),"모든 항목을 채워주세요!",TSnackbar.LENGTH_SHORT);
                            snackbar.setActionTextColor(Color.WHITE);
                            View snackbarView = snackbar.getView();
                            snackbarView.setBackgroundColor(Color.parseColor("#574FBA"));
                            TextView textView = (TextView)snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
                            textView.setTextColor(Color.WHITE);
                            snackbar.show();
                        }
                        else if(four<30)
                        {
                    /*progressSnackbar2.setText("모든 내용을 채워주세요");
                    progressSnackbar2.show();*/
                            snackbar = TSnackbar.make(findViewById(android.R.id.content),"내용을 30자 이상 채워주세요!",TSnackbar.LENGTH_SHORT);
                            snackbar.setActionTextColor(Color.WHITE);
                            View snackbarView = snackbar.getView();
                            snackbarView.setBackgroundColor(Color.parseColor("#574FBA"));
                            TextView textView = (TextView)snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
                            textView.setTextColor(Color.WHITE);
                            snackbar.show();
                        }
                        else
                        {
                    /*progressSnackbar.setText("자료 업로드 중...");
                    progressSnackbar.show();*/
                            tv_upload_file.setEnabled(false);
                            snackbar = TSnackbar.make(findViewById(android.R.id.content),"자료 업로드 중입니다...잠시만 기다려주세요",TSnackbar.LENGTH_INDEFINITE);
                            snackbar.setActionTextColor(Color.WHITE);
                            View snackbarView = snackbar.getView();
                            snackbarView.setBackgroundColor(Color.parseColor("#574FBA"));
                            TextView textView = (TextView)snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
                            textView.setTextColor(Color.WHITE);
                            snackbar.show();

                            TokenManager tm = TokenManager.getInstance();
                            String token = tm.getToken(getApplicationContext());
                            //Log.e("시발",token);
                            // 이제 올리기
                            RequestBody titleBody = RequestBody.create(
                                    MediaType.parse("text/plain"),
                                    edtv_file_name.getText()+""
                            );


                            RequestBody contentBody = RequestBody.create(
                                    MediaType.parse("text/plain"),
                                    edtv_content.getText()+""
                            );

                            RequestBody subjectNameBody = RequestBody.create(
                                    MediaType.parse("text/plain"),
                                    edtv_select_subject.getText()+""
                            );

                            RequestBody profNameBody = RequestBody.create(
                                    MediaType.parse("text/plain"),
                                    edtv_select_prof.getText()+""
                            );

                            RequestBody tokenBody = RequestBody.create(
                                    MediaType.parse("text/plain"),
                                    token
                            );
                            service.uploadImage(
                                    titleBody,
                                    contentBody,
                                    subjectNameBody,
                                    profNameBody,
                                    tokenBody,
                                    filePart
                            ).enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if(response.isSuccessful())
                                    {
                                        Intent intent = new Intent(getApplicationContext(), UploadedActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                    /*snackbar = TSnackbar.make(findViewById(android.R.id.content),"업로드 성공",TSnackbar.LENGTH_INDEFINITE);
                                    View snackbarView = snackbar.getView();
                                    snackbarView.setBackgroundColor(Color.parseColor("#574FBA"));
                                    snackbar.show();*/

                                }



                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Log.e("흠",t.getCause()+"");
                                    Intent intent = new Intent(getApplicationContext(),ServerFailActivity.class);
                                    startActivity(intent);
                                }
                            });
                        }
                        break;
                    case R.id.imageButton4:
                        finish();
                        break;
                    case R.id.img_btn_file_upload2:

                        //View view2 = getLayoutInflater().inflate(R.layout.layout_bottomsheet,null);
                        dialog = new BottomSheetFragement();
                        dialog.show(getSupportFragmentManager(),"냐아옹");
                        break;
                    case R.id.img_btn_file_upload3:
                        dialog2 = new BottomSheetFragment2();
                        dialog2.show(getSupportFragmentManager(),"냐아옹");
                        break;
                }

            }
        };

        tv_upload_file.setOnClickListener(onClickListener);
        findViewById(R.id.imageButton4).setOnClickListener(onClickListener);
        findViewById(R.id.img_btn_file_upload2).setOnClickListener(onClickListener);
        findViewById(R.id.img_btn_file_upload3).setOnClickListener(onClickListener);

        // 권한내놔!
        giveMePermissions();

        // 버튼 클릭 리스너 설정 부분
        findViewById(R.id.img_btn_file_upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickFile();
            }
        });
        findViewById(R.id.img_btn_file_upload_on).setOnClickListener(new View.OnClickListener() {
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
                //Snackbar.make(FileUploadActivity.this.findViewById(R.id.root), "권한내놔!!!!!!!!", Snackbar.LENGTH_LONG).show();
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
        type = MediaType.parse("application/octet-stream"); //body의 타입은 multipart가 맞는데 body 속 파일 타입은 모든 파일을 수용할 수 있는
        //application/octet-stream
        //안 해도 문제는 없지만 HTTP 표준에 어긋
        // POST의 file 부분 생성
        File imageFile = new File(path);
        RequestBody reqFile = RequestBody.create(type, imageFile);
        filePart = MultipartBody.Part.createFormData("userfile", imageFile.getName(), reqFile);
        TextView tv_uploaded_file_name = findViewById(R.id.tv_uploaded_file_name);
        tv_uploaded_file_name.setVisibility(View.VISIBLE);
        tv_uploaded_file_name.setText(imageFile.getName()+"");
/*        progressSnackbar.setText("자료 업로드 중...");
        progressSnackbar.show();*/
        file_upload_check=true;
        img_btn_file_upload_on.setVisibility(View.VISIBLE);

        check();
    }

    void init()
    {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        edtv_file_name=findViewById(R.id.edtv_file_name);
        edtv_select_subject =  findViewById(R.id.edtv_select_subject);
        edtv_select_prof =  findViewById(R.id.edtv_select_prof);
        edtv_content = findViewById(R.id.edtv_content);
        tv_upload_file = findViewById(R.id.tv_upload_file);
        img_btn_file_upload_on=findViewById(R.id.img_btn_file_upload_on);
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
        /*progressSnackbar.setText("자료 업로드 중..."+progress+"%");
        progressSnackbar.show();*/
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

    @Override
    public void onClick(String value) {
        TextView edtv_select_subject = findViewById(R.id.edtv_select_subject);
        edtv_select_subject.setText(value);
        dialog.dismiss();
    }

    @Override
    public void onClick2(String value) {
        TextView edtv_select_prof = findViewById(R.id.edtv_select_prof);
        edtv_select_prof.setText(value);
        dialog2.dismiss();
    }
}

