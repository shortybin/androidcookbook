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

    companion object {
        private lateinit var application: MainApplication
        fun getInstance(): Application {
            return application
        }
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        HttpClient.instance
            .addInterceptor(BusinessErrorInterceptor())
            .addInterceptor(BaseRequestInterceptor())
            .build("http://10.0.2.2:8080")
    }
}