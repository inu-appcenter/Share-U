package com.inuappcenter.shareu.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.Progress;
import com.downloader.utils.Utils;
import com.google.android.material.snackbar.Snackbar;
import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.fragment.BottomSheetMinusPoint;
import com.inuappcenter.shareu.fragment.BottomSheetPlusPoint;
import com.inuappcenter.shareu.fragment.BottomSheetSparsePoint;
import com.inuappcenter.shareu.fragment.BottomSheetZeroPoint;
import com.inuappcenter.shareu.my_class.BooleanFuck;
import com.inuappcenter.shareu.my_class.Document;
import com.inuappcenter.shareu.my_class.Fuck;
import com.inuappcenter.shareu.my_class.RestError;
import com.inuappcenter.shareu.my_class.SuperiorLecture;
import com.inuappcenter.shareu.my_class.TokenManager;
import com.inuappcenter.shareu.my_class.documentFile;
import com.inuappcenter.shareu.my_class.documentPage;
import com.inuappcenter.shareu.my_class.documentPage2;
import com.inuappcenter.shareu.my_class.reviewList;
import com.inuappcenter.shareu.my_class.score;
import com.inuappcenter.shareu.my_class.sendFileExtension;
import com.inuappcenter.shareu.my_interface.OnItemClick;
import com.inuappcenter.shareu.recycler.CategorySuccessedAdatper;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;
import com.willy.ratingbar.BaseRatingBar;

import org.json.JSONObject;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.inuappcenter.shareu.R.id.btn_backpress;
import static com.inuappcenter.shareu.R.id.tv_detailed_file_date;
import static com.inuappcenter.shareu.R.id.tv_no_review;

public class DetailedFileActivity extends AppCompatActivity implements OnItemClick {
    private BottomSheetMinusPoint bottomSheetMinusPoint=new BottomSheetMinusPoint();
    private BottomSheetPlusPoint bottomSheetPlusPoint=new BottomSheetPlusPoint();
    private BottomSheetSparsePoint bottomSheetSparsePoint=new BottomSheetSparsePoint();
    private BottomSheetZeroPoint bottomSheetZeroPoint=new BottomSheetZeroPoint();


    private BaseRatingBar review_ratingbar;
    private BaseRatingBar before_user_ratingbar;
    private TextView tv_get_file,tv_detailed_file_user_name,tv_detailed_file_category,
            tv_detailed_file_name,tv_detailed_file_type,tv_detailed_file_date;
    private TextView tv_review_date,tv_review_name,tv_review_content,tv_my_detailed_file_review,tv_no_review;
    private TextView tv_my_major;
    private TextView tv_detailed_file_content,tv_more;
    private LinearLayout yes_review;
    private int key;
    private ImageView btn_backpress,singo;

    private EditText edtv_review;
    BaseRatingBar user_ratingbar;
    private TextView tv_register;
    private TSnackbar snackbar;

    private String url;
    private String sub_url;
    private MutableLiveData<String> extension=new MutableLiveData<>();
    private LifecycleOwner lifecycleOwner;
    private String hi="";
    //private FragmentManager fm = getSupportFragmentManager();
    private Boolean flag=false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_file);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        init();
        lifecycleOwner=this;
        PRDownloader.initialize(getApplicationContext());
        Intent intent =getIntent();
        key =intent.getExtras().getInt("key");

    }

    @Override
    protected void onResume() {
        super.onResume();
        listen();
        giveMeStar();
        giveMeList();
        giveMeReview();

    }

    public MutableLiveData<String> getExtension() {
        return extension;
    }
    void listen()
    {
        tv_more.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ReviewActivity.class);
                intent.putExtra("key",key);
                intent.putExtra("name",tv_my_major.getText()+"");
                startActivity(intent);
            }
        });

        tv_get_file.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //TODO : 상황에 따라 포인트 지급 , 차감 결정..
                //check();
                //bottomSheetPlusPoint.show(getSupportFragmentManager(),"냐옹");
                //goDownload();
                TokenManager tm = TokenManager.getInstance();
                String token = tm.getToken(getApplicationContext());
                if(token==null)
                {
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                }
                else
                {
                    checkAlreadyDownload();
                }

            }
        });
        tv_register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                check();
            }
        });

        btn_backpress.setOnClickListener(v->finish());

        singo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SelectPoliceActivity.class);
                intent.putExtra("key",key);
                startActivity(intent);
            }
        });
    }

    void giveMeStar()
    {
        RetrofitService networkService = RetrofitHelper.create();
        networkService.score(key).enqueue(new Callback<List<score>>() {
            @Override
            public void onResponse(Call<List<score>> call, Response<List<score>> response) {
                if (response.isSuccessful()) {
                    review_ratingbar.setRating(response.body().get(0).getScore());

                }

            }

            @Override
            public void onFailure(Call<List<score>> call, Throwable t) {

            }
        });
    }
    void giveMeReview()
    {

        RetrofitService networkService = RetrofitHelper.create();
        networkService.reviewList(key).enqueue(new Callback<List<reviewList>>() {
            @Override
            public void onResponse(Call<List<reviewList>> call, Response<List<reviewList>> response) {
                if (response.isSuccessful()) {
                    tv_my_detailed_file_review.setText("리뷰 ("+response.body().size()+")");
                    if(response.body().size()==0)
                    {
                        tv_no_review.setVisibility(View.VISIBLE);
                        yes_review.setVisibility(View.GONE);
                        tv_more.setVisibility(View.GONE);

                    }
                    else
                    {
                        tv_no_review.setVisibility(View.GONE);
                        yes_review.setVisibility(View.VISIBLE);
                        tv_more.setVisibility(View.VISIBLE);
                        tv_review_date.setText(response.body().get(response.body().size()-1).getUploadDate());
                        String name = response.body().get(response.body().size()-1).getUploadId();
                        name=name.substring(0,5);
                        name+="***";
                        tv_review_name.setText(name);
                        before_user_ratingbar.setRating(response.body().get(response.body().size()-1).getScore());
                        tv_review_content.setText(response.body().get(response.body().size()-1).getReview());
                    }

                }

            }

            @Override
            public void onFailure(Call<List<reviewList>> call, Throwable t) {

            }
        });
    }

    void checkAlreadyDownload()
    {
        TokenManager tm = TokenManager.getInstance();
        String token = tm.getToken(this);

        RetrofitService networkService = RetrofitHelper.create();
        networkService.documentPage(key,token).enqueue(new Callback<documentPage>() {
            @Override
            public void onResponse(Call<documentPage> call, Response<documentPage> response) {

                Log.e("냐",response.body().ans+"");
                if (response.isSuccessful()) {
                    if(response.body().ans==true)//받은 적 없다
                    {
                        goDownload();
                    }
                    else//받은 적 있따.
                    {
                        giveFree();
                    }
                }


            }

            @Override
            public void onFailure(Call<documentPage> call, Throwable t) {

            }
        });
    }

    void giveMeList()
    {
        TokenManager tm = TokenManager.getInstance();
        String token = tm.getToken(this);
/////////////여기바꿔시발
        RetrofitService networkService = RetrofitHelper.create();
        Log.e("흠",key+" "+token);
        networkService.documentPageone(key).enqueue(new Callback<List<documentPage2>>() {
            @Override
            public void onResponse(Call<List<documentPage2>> call, Response<List<documentPage2>> response) {


                if (response.isSuccessful()) {
                    for(int i=0;i<response.body().size();i++)
                    {
                        tv_my_major.setText(response.body().get(0).title);
                        tv_detailed_file_content.setText(response.body().get(0).content);
                        //review_ratingbar.setRating(response.body().get(0).get);
                        String name = response.body().get(0).uploadId;
                        name=name.substring(0,5);
                        name+="****";
                        tv_detailed_file_user_name.setText(name);
                        //tv_detailed_file_category.setText(response.body().get(0))
                        tv_detailed_file_date.setText(response.body().get(0).uploadDate);
                        tv_detailed_file_category.setText(response.body().get(0).majorName);
                        tv_detailed_file_type.setText(response.body().get(0).extension);
                        tv_detailed_file_name.setText(response.body().get(0).subjectName);
                    }

                }


            }

            @Override
            public void onFailure(Call<List<documentPage2>> call, Throwable t) {

            }
        });
    }

    void init()
    {
        review_ratingbar  = findViewById(R.id.review_ratingbar);
        review_ratingbar.setScrollable(false);
        review_ratingbar.setClickable(false);
        before_user_ratingbar  = findViewById(R.id.before_user_ratingbar);
        before_user_ratingbar.setScrollable(false);
        before_user_ratingbar.setClickable(false);
        tv_get_file = findViewById(R.id.tv_get_file);
        tv_detailed_file_user_name=findViewById(R.id.tv_detailed_file_user_name);
        tv_detailed_file_category=findViewById(R.id.tv_detailed_file_category);
        tv_detailed_file_name = findViewById(R.id.tv_detailed_file_name);
        tv_detailed_file_type=findViewById(R.id.tv_detailed_file_type);
        tv_detailed_file_date = findViewById(R.id.tv_detailed_file_date);
        tv_review_date=findViewById(R.id.tv_review_date);
        tv_review_name=findViewById(R.id.tv_review_name);
        tv_review_content=findViewById(R.id.tv_review_content);
        tv_my_detailed_file_review=findViewById(R.id.tv_my_detailed_file_review);
        tv_no_review=findViewById(R.id.tv_no_review);
        tv_my_major = findViewById(R.id.tv_my_major);
        tv_detailed_file_content=findViewById(R.id.tv_detailed_file_content);
        yes_review=findViewById(R.id.layout_yes_review);
        tv_more = findViewById(R.id.tv_more);
        btn_backpress=findViewById(R.id.btn_backpress);
        singo=findViewById(R.id.singo);

        edtv_review =findViewById(R.id.edtv_review);
        user_ratingbar=findViewById(R.id.user_ratingbar);
        tv_register=findViewById(R.id.tv_register);
    }

    @Override
    public void onClick(String value) {
        if(bottomSheetPlusPoint.isAdded())
        {
            bottomSheetPlusPoint.dismiss();
            flag=false;
        }
        else if(bottomSheetMinusPoint.isAdded())
        {
            bottomSheetMinusPoint.dismiss();
            flag=false;
        }
        else if(bottomSheetSparsePoint.isAdded())
        {
            bottomSheetSparsePoint.dismiss();
            flag=false;
        }
        else if(bottomSheetZeroPoint.isAdded())
        {
            bottomSheetZeroPoint.dismiss();
            flag=false;
        }
    }

    @Override
    public void onClick2(String value) {

    }

    void goDownload()
    {
        TokenManager tm = TokenManager.getInstance();
        String token = tm.getToken(this);
        if(token==null)
        {
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
        }
        else
        {

            RetrofitService networkService = RetrofitHelper.create();
            networkService.documentFile(key,token).enqueue(new Callback<List<documentFile>>() {
                @Override
                public void onResponse(Call<List<documentFile>> call, Response<List<documentFile>> response) {
                    if (response.isSuccessful()) {
                        if(response.body().get(0).getFileName()==null)
                        {
                            if(flag==false)
                            {
                                bottomSheetSparsePoint=new BottomSheetSparsePoint();
                                bottomSheetSparsePoint.show(getSupportFragmentManager(),"냐옹");
                                flag=true;
                            }

                        }
                        else
                        {
                            String file = response.body().get(0).getFileName();
                            RetrofitService networkService = RetrofitHelper.create();
                            networkService.sendFileExtension(key).enqueue(new Callback<List<sendFileExtension>>() {
                                @Override
                                public void onResponse(Call<List<sendFileExtension>> call, Response<List<sendFileExtension>> response) {
                                    if (response.isSuccessful()) {

                                        Log.e("읭",response.body().get(0).getExtension()+"");
                                        hi=response.body().get(0).getExtension()+"";
                                        extension.setValue(response.body().get(0).getExtension()+"");
                                        giveFile(file);

                                    }

                                }

                                @Override
                                public void onFailure(Call<List<sendFileExtension>> call, Throwable t) {
                                    Intent intent = new Intent(getApplicationContext(), ServerFailActivity.class);
                                    startActivity(intent);
                                }
                            });


                        }

                        extension.observe(lifecycleOwner, new Observer<String>() {
                            @Override
                            public void onChanged(String s) {

                            }
                        });


                    }

                }

                @Override
                public void onFailure(Call<List<documentFile>> call, Throwable t) {

                    Log.e("실패",t.getMessage()+"");
                }
            });
        }
    }


    void giveFile2(String file)
    {
        Log.e("흠흠",extension.getValue()+"");
        Date today = new Date();
        String bye=today.getTime()+"";
        String hi = extension.getValue();
        sub_url=file+"";
        url = "http://117.16.231.66:7001/document/send/documentFile";
        url+="/"+sub_url;
        String dirPath = "/storage/emulated/0/Share U";
        String fileName = tv_my_major.getText()+bye+"."+hi;

        PRDownloader.download(url, dirPath, fileName)
                .build()
                .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                    @Override
                    public void onStartOrResume() {
                        Log.e("onStartOrResume()","");
                    }
                })
                .setOnPauseListener(new OnPauseListener() {
                    @Override
                    public void onPause() {
                        Log.e("onPause()","");
                    }
                })
                .setOnCancelListener(new OnCancelListener() {
                    @Override
                    public void onCancel() {
                        Log.e("onCancel()","");
                    }
                })
                .setOnProgressListener(new OnProgressListener() {
                    @Override
                    public void onProgress(Progress progress) {
                        Log.e("onProgress()","");
                    }
                })
                .start(new OnDownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        bottomSheetZeroPoint=new BottomSheetZeroPoint();
                        bottomSheetZeroPoint.show(getSupportFragmentManager(),"냐옹");
                        flag=true;


                    }

                    @Override
                    public void onError(Error error) {
                        Log.e("onError()","");
                    }

                });

    }
    void giveFile(String file)
    {




        Log.e("흠흠",extension.getValue()+"");
        Date today = new Date();
        String bye=today.getTime()+"";
        sub_url=file+"";
        String hi = extension.getValue();
        url = "http://117.16.231.66:7001/document/send/documentFile";
        url+="/"+sub_url;
        String dirPath = "/storage/emulated/0/Share U";
        String fileName = tv_my_major.getText()+bye+"."+hi;

        PRDownloader.download(url, dirPath, fileName)
                .build()
                .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                    @Override
                    public void onStartOrResume() {
                        Log.e("onStartOrResume()","");
                    }
                })
                .setOnPauseListener(new OnPauseListener() {
                    @Override
                    public void onPause() {
                        Log.e("onPause()","");
                    }
                })
                .setOnCancelListener(new OnCancelListener() {
                    @Override
                    public void onCancel() {
                        Log.e("onCancel()","");
                    }
                })
                .setOnProgressListener(new OnProgressListener() {
                    @Override
                    public void onProgress(Progress progress) {
                        Log.e("onProgress()","");
                    }
                })
                .start(new OnDownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        bottomSheetMinusPoint=new BottomSheetMinusPoint();
                        bottomSheetMinusPoint.show(getSupportFragmentManager(),"냐옹");
                        flag=true;


                    }

                    @Override
                    public void onError(Error error) {
                        Log.e("onError()","");
                    }

                });

    }
    void giveFree()
    {
        TokenManager tm = TokenManager.getInstance();
        String token = tm.getToken(this);
        if(token==null)
        {
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
        }
        else
        {

            RetrofitService networkService = RetrofitHelper.create();
            networkService.reDownload(key).enqueue(new Callback<List<documentFile>>() {
                @Override
                public void onResponse(Call<List<documentFile>> call, Response<List<documentFile>> response) {
                    if (response.isSuccessful()) {
                        if(response.body().get(0).getFileName()==null)
                        {

                        }
                        else
                        {
                            String file_name = response.body().get(0).getFileName();
                            RetrofitService networkService = RetrofitHelper.create();
                            networkService.sendFileExtension(key).enqueue(new Callback<List<sendFileExtension>>() {
                                @Override
                                public void onResponse(Call<List<sendFileExtension>> call, Response<List<sendFileExtension>> response) {
                                    if (response.isSuccessful()) {

                                        Log.e("읭",response.body().get(0).getExtension()+"");
                                        hi=response.body().get(0).getExtension()+"";
                                        extension.setValue(response.body().get(0).getExtension()+"");
                                        giveFile2(file_name);
                                    }

                                }

                                @Override
                                public void onFailure(Call<List<sendFileExtension>> call, Throwable t) {
                                    /*Intent intent = new Intent(getApplicationContext(), ServerFailActivity.class);
                                    startActivity(intent);*/
                                }
                            });


                        }

                        extension.observe(lifecycleOwner, new Observer<String>() {
                            @Override
                            public void onChanged(String s) {


                                /*Log.e("변신!",extension.getValue());
                                Date today = new Date();
                                String bye=today.getTime()+"";
                                sub_url=response.body().get(0).getFileName();
                                url = "http://117.16.231.66:7001/document/send/documentFile";
                                url+="/"+sub_url;
                                String dirPath = "/storage/emulated/0/Share U";
                                String fileName = tv_my_major.getText()+bye+"."+hi;

                                PRDownloader.download(url, dirPath, fileName)
                                        .build()
                                        .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                                            @Override
                                            public void onStartOrResume() {
                                                Log.e("onStartOrResume()","");
                                            }
                                        })
                                        .setOnPauseListener(new OnPauseListener() {
                                            @Override
                                            public void onPause() {
                                                Log.e("onPause()","");
                                            }
                                        })
                                        .setOnCancelListener(new OnCancelListener() {
                                            @Override
                                            public void onCancel() {
                                                Log.e("onCancel()","");
                                            }
                                        })
                                        .setOnProgressListener(new OnProgressListener() {
                                            @Override
                                            public void onProgress(Progress progress) {
                                                Log.e("onProgress()","");
                                            }
                                        })
                                        .start(new OnDownloadListener() {
                                            @Override
                                            public void onDownloadComplete() {
                                                if(flag==false)
                                                {
                                                    bottomSheetZeroPoint = new BottomSheetZeroPoint();
                                                    bottomSheetZeroPoint.show(getSupportFragmentManager(),"냐옹");
                                                    flag=true;
                                                }


                                            }

                                            @Override
                                            public void onError(Error error) {
                                                Log.e("onError()","");
                                            }

                                        });*/

                            }
                        });


                    }

                }

                @Override
                public void onFailure(Call<List<documentFile>> call, Throwable t) {

                    Log.e("실패",t.getMessage()+"");
                }
            });
        }

    }
    void check() {
        //Log.e("여기 와..?","흠");
        TokenManager tm = TokenManager.getInstance();
        String token = tm.getToken(this);
        if (token == null)
        {
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
        }

        else if(edtv_review.getText().length()<5)
        {
            snackbar = TSnackbar.make(findViewById(android.R.id.content),"리뷰를 5글자 이상 남겨주세요!",TSnackbar.LENGTH_SHORT);
            snackbar.setActionTextColor(Color.WHITE);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.parseColor("#574FBA"));
            TextView textView = (TextView)snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();
        }
        else if(user_ratingbar.getRating()<1)
        {
            snackbar = TSnackbar.make(findViewById(android.R.id.content),"별점을 1점 이상 채워주세요!",TSnackbar.LENGTH_SHORT);
            snackbar.setActionTextColor(Color.WHITE);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.parseColor("#574FBA"));
            TextView textView = (TextView)snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();
        }
        else
        {
            //리뷰 날리기
            RetrofitService networkService = RetrofitHelper.create();
            networkService.uploadreview(edtv_review.getText()+"",user_ratingbar.getRating(),key,token).enqueue(new Callback<BooleanFuck>() {
                @Override
                public void onResponse(Call<BooleanFuck> call, Response<BooleanFuck> response) {
                    if (response.isSuccessful()) {
                        Log.e("시발",response.body().getAns()+"");
                        if(response.body().getAns())
                        {
                            if(flag==false)
                            {
                                bottomSheetPlusPoint = new BottomSheetPlusPoint();
                                bottomSheetPlusPoint.show(getSupportFragmentManager(),"냐아옹");
                                flag=true;
                            }
                            giveMeStar();
                            giveMeReview();
                        }
                        else
                        {
                            snackbar = TSnackbar.make(findViewById(android.R.id.content),"자료를 받지 않았거나 이미 리뷰를 등록하셨습니다.",TSnackbar.LENGTH_SHORT);
                            snackbar.setActionTextColor(Color.WHITE);
                            View snackbarView = snackbar.getView();
                            snackbarView.setBackgroundColor(Color.parseColor("#574FBA"));
                            TextView textView = (TextView)snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
                            textView.setTextColor(Color.WHITE);
                            snackbar.show();
                        }

                    }

                }

                @Override
                public void onFailure(Call<BooleanFuck> call, Throwable t) {
                    Intent intent = new Intent(getApplicationContext(), ServerFailActivity.class);
                    startActivity(intent);
                }
            });

        }

    }
}
