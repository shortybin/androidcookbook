package com.wuhuabin.cookbook.api

import com.wuhuabin.cookbook.bean.UploadImageBean
import com.wuhuabin.cookbook.bean.UserBean
import com.wuhuabin.net.ApiResponse
import com.wuhuabin.net.ApiResult
import com.wuhuabin.net.HttpClient
import okhttp3.MultipartBody
import retrofit2.http.*

/**
 * Created by shortybin
 * on 2022/7/2
 */
interface CookBookAPi {
    companion object {
        fun create(): CookBookAPi {
            return HttpClient.instance.retrofit.create(CookBookAPi::class.java)
        }
    }

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("/login")
    suspend fun userLogin(
        @Field("userName") userName: String,
        @Field("password") password: String
    ): ApiResult<ApiResponse<UserBean>>

    @POST("/upload/file")
    suspend fun uploadImage(@Body multipartBody: MultipartBody): ApiResult<ApiResponse<String>>
}