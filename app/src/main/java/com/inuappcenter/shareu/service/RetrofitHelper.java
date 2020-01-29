package com.inuappcenter.shareu.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    public static RetrofitService create()
    {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://597c32fd-cab6-4d12-a719-2d99a04bd53f.mock.pstmn.io").addConverterFactory(GsonConverterFactory.create()).build();

        return retrofit.create(RetrofitService.class);
    }
}
