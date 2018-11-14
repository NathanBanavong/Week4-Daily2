package com.example.consultants.week4_daily2.client;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpHelper {

    private static final String TAG = OkHttpHelper.class.getSimpleName() + "_TAG";

    OkHttpClient client;
    private Request request;

    public OkHttpHelper() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);


        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("github.com")
                .addPathSegment("api")
                .addQueryParameter("results", "10")
                .addQueryParameter("gender", "male")
                .build();

        client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();


        request = new Request.Builder()
                .url(url)
                .build();

    }

}
