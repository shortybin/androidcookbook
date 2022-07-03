package com.wuhuabin.cookbook.api

import okhttp3.Interceptor
import okhttp3.Response

class BaseRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var token = ""

        val build = chain.request().newBuilder()
            .addHeader("token", token)
            .build()

        return chain.proceed(build)
    }
}