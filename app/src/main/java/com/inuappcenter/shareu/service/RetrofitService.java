package com.inuappcenter.shareu.service;

import com.inuappcenter.shareu.model.Major;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("/hi")
    Call<List<Major> > getMajorList();
    @GET("/bye")
    Call<List<Major> > getGyoyangList();
    @GET("/si")
    Call<List<Major> > getDetailedMajorList(@Query("Major") String Major);
    @GET("/bal")
    Call<List<Major> > getDetailedGyoyangList(@Query("Gyoyang") String Gyoyang);
}
