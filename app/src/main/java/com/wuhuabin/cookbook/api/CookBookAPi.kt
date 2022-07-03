package com.wuhuabin.cookbook.api

import com.wuhuabin.cookbook.bean.UserBean
import com.wuhuabin.net.ApiResponse
import com.wuhuabin.net.ApiResult
import com.wuhuabin.net.HttpClient
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by shortybin
 * on 2022/7/2
 */
interface CookBookAPi {
    companion object {
        fun create(): CookBookAPi {
            return HttpClient.getInstance().retrofit.create(CookBookAPi::class.java)
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
}