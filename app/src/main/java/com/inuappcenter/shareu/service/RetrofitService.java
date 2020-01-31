package com.inuappcenter.shareu.service;

import com.inuappcenter.shareu.model.Major;
import com.inuappcenter.shareu.model.Notice;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("/search/all/majorList")
    Call<List<Major>> getMajorList();

    @GET("/search/all/subjectList")
    Call<List<Major> > getDetailedMajorList(@Query("majorName") String Major);

    @GET("/search/all/cultureList")
    Call<List<Major> > getDetailedGyoyangList();

    @GET("/notice/noticeAllList")
    Call<List<Notice> > getNotice();

    @GET("/notice/noticeOne")
    Call<List<Notice> > getDetailedNotice(@Query("noticeKey") int key);
}
