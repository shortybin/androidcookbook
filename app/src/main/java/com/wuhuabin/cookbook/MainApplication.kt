package com.wuhuabin.cookbook

import android.app.Application
import com.wuhuabin.cookbook.api.BaseRequestInterceptor
import com.wuhuabin.cookbook.api.BusinessErrorInterceptor
import com.wuhuabin.net.HttpClient

/**
 * Created by shortybin
 * on 2022/7/2
 */
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        HttpClient.getInstance()
            .addInterceptor(BusinessErrorInterceptor())
            .addInterceptor(BaseRequestInterceptor())
            .build("http://10.0.2.2:8080")
    }
}