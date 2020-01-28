package com.inuappcenter.shareu.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    public static RetrofitService create()
    {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://1.243.185.194:80").addConverterFactory(GsonConverterFactory.create()).build();

        return retrofit.create(RetrofitService.class);
    }
}
