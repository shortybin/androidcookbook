package com.wuhuabin.net;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shortybin
 * on 2020/4/10
 */
public class HttpClient {
    private Retrofit mRetrofit;
    private static HttpClient mHttpClient;
    private OkHttpClient mMOkHttpClient;
    private final OkHttpClient.Builder mBuilder;

    private HttpClient() {
        mBuilder = new OkHttpClient()
                .newBuilder();
    }

    public HttpClient addInterceptor(Interceptor interceptor) {
        mBuilder.addInterceptor(interceptor);
        return mHttpClient;
    }

    public void build(String url) {
        mMOkHttpClient = mBuilder
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .connectTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        mRetrofit = new Retrofit.Builder()
                .addCallAdapterFactory(new ApiCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url).client(mMOkHttpClient).build();
    }


    public static HttpClient getInstance() {
        if (mHttpClient == null) {
            mHttpClient = new HttpClient();
        }
        return mHttpClient;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }
}
