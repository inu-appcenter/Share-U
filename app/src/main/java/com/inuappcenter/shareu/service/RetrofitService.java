package com.inuappcenter.shareu.service;

import com.inuappcenter.shareu.model.File;
import com.inuappcenter.shareu.model.Major;
import com.inuappcenter.shareu.model.Notice;
import com.inuappcenter.shareu.model.profName;
import com.inuappcenter.shareu.model.subjectName;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
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

    @GET("/document/send/subjectChoice")
    Call<List<File> > getSubjectName(@Query("subjectName") String subj);

    @GET("/document/send/profChoice")
    Call<List<File> > getProfName(@Query("profName") String prof);

    @Multipart
    @POST("/document/store/upload")
    Call<ResponseBody> uploadImage(@Part("title") RequestBody title, @Part("subjectName") RequestBody subjectName,
                                   @Part("profName") RequestBody profName, @Part("content") RequestBody content, @Part MultipartBody.Part filePart);

}
