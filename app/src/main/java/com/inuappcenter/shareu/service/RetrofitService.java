package com.inuappcenter.shareu.service;

import com.inuappcenter.shareu.model.Major;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {
    @GET("/search/all")
    Call<List<Major> > getList();
}
