package com.inuappcenter.shareu.service;

import com.inuappcenter.shareu.my_class.BooleanFuck;
import com.inuappcenter.shareu.my_class.Fuck;
import com.inuappcenter.shareu.my_class.Login;
import com.inuappcenter.shareu.my_class.Major;
import com.inuappcenter.shareu.my_class.MyInform;
import com.inuappcenter.shareu.my_class.MyPage;
import com.inuappcenter.shareu.my_class.Notice;
import com.inuappcenter.shareu.my_class.SumPoint;
import com.inuappcenter.shareu.my_class.categorySubject;
import com.inuappcenter.shareu.my_class.profName;
import com.inuappcenter.shareu.my_class.subjectName;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("/search/all/majorList")
    Call<List<Major> > getMajorList();

    @GET("/search/all/subjectList")
    Call<List<Major> > getDetailedMajorList(@Query("majorName") String Major);

    @GET("/search/all/cultureList")
    Call<List<Major> > getDetailedGyoyangList();

    @GET("/notice/noticeAllList")
    Call<List<Notice> > getNotice();

    @GET("/notice/noticeOne")
    Call<List<Notice> > getDetailedNotice(@Query("noticeKey") int key);

    //전공리스트에서 전공이름 검색
    @GET("/search/bar/subjectChoice")
    Call<List<subjectName> > getSubjectName(@Query("subjectName") String subj);

    //전공선택후 과목이름 검색
    @GET("/search/bar/profChoice")
    Call<List<profName> > getProfName(@Query("subjectName")String subj,@Query("profName") String prof);

    @Multipart
    @POST("/document/store/upload")
    Call<ResponseBody> uploadImage(@Part("title") RequestBody title, @Part("subjectName") RequestBody subjectName,
                                   @Part("profName") RequestBody profName, @Part("content") RequestBody content, @Part MultipartBody.Part filePart);

    //로그인
    @FormUrlEncoded
    @POST("/account/signIn")
    Call<Login> login(@Field("id")String id, @Field("passwd")String passwd);

    //마이페이지
    @FormUrlEncoded
    @POST("/account/myPage")
    Call<MyPage> mypage(@Field("token")String token);

    //사용자 포인트 총합 전송
    @FormUrlEncoded
    @POST("/document/user_doc/userPoint")
    Call<List<SumPoint> > sumpoint(@Field("token")String token);

    //회원정보 수정 페이지에서 뜨는 기본 값
    @FormUrlEncoded
    @POST("/account/changeBefore")
    Call<MyInform >changeBefore(@Field("token")String token);

    //회원정보수정
    @FormUrlEncoded
    @POST("/account/changeInfo")
    Call<Fuck>changeInfo(@Field("id")String id,@Field("passwd")String passwd
                         ,@Field("tel")String tel,@Field("major")String major,@Field("name")String name
            ,@Field("newPasswd")String newPasswd,@Field("token")String token);

    //비밀번호찾기
    @FormUrlEncoded
    @POST("/account/tmpPasswd")
    Call<Fuck>tmpPasswd(@Field("id")String id, @Field("name")String name);

    //사용자 회원가입시 10포인트 선물 받는다 이미받은적있으면 false
    @FormUrlEncoded
    @POST("/document/user_doc/giftPoint")
    Call<BooleanFuck>giftPoint(@Field("token")String token);

    //사용자 회원가입시 10포인트 받은적 유/무
    @FormUrlEncoded
    @POST("/document/user_doc/checkGiftPoint")
    Call<BooleanFuck>checkGiftPoint(@Field("token")String token);

    //전공리스트에서 전공이름 검색
    @GET("/search/bar/categoryMajor")
    Call<List<Major> >categoryMajor(@Query("majorName")String majorName);

    //전공선택후 과목이름 검색
    @GET("/search/bar/categorySubject")
    Call<List<categorySubject>>categorySubject
    (@Query("majorName")String majorName,@Query("subjectName")String subjectName);

}
