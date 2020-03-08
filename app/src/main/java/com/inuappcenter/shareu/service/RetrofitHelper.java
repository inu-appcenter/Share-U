package com.inuappcenter.shareu.service;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    public static RetrofitService create()
    {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://117.16.231.66:7001").addConverterFactory(GsonConverterFactory.create()).build();

        return retrofit.create(RetrofitService.class);
    }
}
