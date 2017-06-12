package com.test.google.googleplacesapplication.common.util;


import com.test.google.googleplacesapplication.common.io.APIInterface;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class APIServiceUtil {

    private final String ACCEPT_KEY = "Accept";
    private final String ACCEPT_VALUE = "application/json";
    private final String AUTHORIZATION = "Authorization";
    private final String CONTENT_TYPE = "Content-Type";

    private Retrofit mRetrofit;
    private static final APIServiceUtil INSTANCE = new APIServiceUtil();

    public static APIServiceUtil getInstance() {
        return INSTANCE;
    }

    private APIServiceUtil() {
        OkHttpClient httpClient = new OkHttpClient.Builder().build();

        mRetrofit = new Retrofit.Builder()
                .client(httpClient)
                .baseUrl("https://maps.googleapis.com/maps/")
                .addConverterFactory(GsonConverterFactory.create()).build();

    }

    public APIInterface getApiInterface() {
        return mRetrofit.create(APIInterface.class);
    }

}
