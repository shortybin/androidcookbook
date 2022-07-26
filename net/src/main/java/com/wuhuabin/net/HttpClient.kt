package com.wuhuabin.net

import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import com.wuhuabin.net.ApiCallAdapterFactory
import okhttp3.Interceptor
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by shortybin
 * on 2020/4/10
 */
class HttpClient private constructor() {
    lateinit var retrofit: Retrofit
    private val mBuilder: OkHttpClient.Builder = OkHttpClient().newBuilder()

    fun addInterceptor(interceptor: Interceptor): HttpClient {
        mBuilder.addInterceptor(interceptor)
        return instance
    }

    fun build(url: String) {
        var mOkHttpClient = mBuilder
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(2, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
        retrofit = Retrofit.Builder()
            .addCallAdapterFactory(ApiCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url).client(mOkHttpClient).build()
    }

    companion object {
        val instance: HttpClient by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            HttpClient()
        }
    }

}