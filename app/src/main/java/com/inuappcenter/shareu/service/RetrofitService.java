package com.inuappcenter.shareu.service;

import com.inuappcenter.shareu.my_class.BooleanFuck;
import com.inuappcenter.shareu.my_class.Document;
import com.inuappcenter.shareu.my_class.Fuck;
import com.inuappcenter.shareu.my_class.Login;
import com.inuappcenter.shareu.my_class.Major;
import com.inuappcenter.shareu.my_class.MyInform;
import com.inuappcenter.shareu.my_class.MyPage;
import com.inuappcenter.shareu.my_class.Notice;
import com.inuappcenter.shareu.my_class.SumPoint;
import com.inuappcenter.shareu.my_class.SuperiorLecture;
import com.inuappcenter.shareu.my_class.categoryCulture;
import com.inuappcenter.shareu.my_class.categoryResend;
import com.inuappcenter.shareu.my_class.documentPage;
import com.inuappcenter.shareu.my_class.profName;
import com.inuappcenter.shareu.my_class.reviewList;
import com.inuappcenter.shareu.my_class.score;
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
    @POST("/document/upload/uploadfile")
    Call<ResponseBody> uploadImage(@Part("title") RequestBody  title, @Part("content") RequestBody content,
                                   @Part("subjectName") RequestBody subjectName,
                                   @Part("profName") RequestBody profName,
                                   @Part("token")RequestBody token,@Part MultipartBody.Part filePart);


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
    Call<List<categoryCulture>>categorySubject
    (@Query("majorName")String majorName,@Query("subjectName")String subjectName);

    //카테고리에서 교양 과목 검색
    @GET("/search/bar/categoryCulture")
    Call<List<categoryCulture>>categoryCulture
    (@Query("subjectName")String subjectName);

    //우수자료 선정 -> 검색바에서 검색할 시 전송
    @GET("/document/send/documentTop5ScoreList")
    Call<List<SuperiorLecture>>documentTop5ScoreList(@Query("title")String title,@Query("subjectName")String subjectName,
                                                     @Query("profName")String profName);

    //최신자료 5개 선정 -> 검색바에서 검색할 시 전송
    @GET("/document/send/documentTop5DateList")
    Call<List<Document>>documentTop5DateList(@Query("title")String title,@Query("subjectName")String subjectName,
                                             @Query("profName")String profName);

    //더보기 -> 검색바에서 과목이름 검색시
    @GET("/document/send/more")
    Call<List<Document>>more(@Query("subjectName")String subjectName,@Query("profName")String profName,
    @Query("title")String title);

    //카테고리 과목이름과 교수이름을 받은 후 과목이름과 교수이름 해당 학과 이름까지 재전송
    @GET("/document/send/categoryResend")
    Call<List<categoryResend>>categoryResend(@Query("subjectName")String subjectName, @Query("profName")String profName);

    //자료상세페이지
    @GET("/document/send/documentPage")
    Call<List<documentPage>>documentPage(@Query("documentKey")int key);

    //자료 리뷰 리스트 전송
    @GET("/document/send/reviewList")
    Call<List<reviewList>>reviewList(@Query("documentKey")int key);

    //자료 평균 별점 전송
    @GET("/document/send/score")
    Call<List<score>>score(@Query("documentKey")int key);

    //자료 신고
    @FormUrlEncoded
    @POST("/document/report")
    Call<Fuck>report(@Field("documentKey")int key,@Field("reportContent")String reportContent);

    //main 페이지 우수자료 전송

    @GET("/document/send/mainTop5ScoreList")
    Call<List<SuperiorLecture>>mainTop5ScoreList();

}
