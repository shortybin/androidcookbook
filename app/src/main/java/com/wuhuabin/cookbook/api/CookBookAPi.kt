package com.wuhuabin.cookbook.api

import com.wuhuabin.cookbook.bean.CategoryBean
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
    @POST("/user/login")
    suspend fun userLogin(
        @Field("userName") userName: String,
        @Field("password") password: String
    ): ApiResult<ApiResponse<UserBean>>

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("/user/register")
    suspend fun userRegister(
        @Field("userName") userName: String,
        @Field("password") password: String
    ): ApiResult<ApiResponse<String>>

    /**
     * 修改密码
     */
    @FormUrlEncoded
    @POST("/user/changePassword")
    suspend fun changePassword(
        @Field("userId") userId: Int,
        @Field("password") userName: String,
        @Field("confirmPassword") password: String
    ): ApiResult<ApiResponse<String>>

    /**
     * 获取菜谱分类
     */
    @GET("/category/getCategoryList")
    suspend fun getCategory(): ApiResult<ApiResponse<List<CategoryBean>>>

    /**
     * 上传图片
     */
    @POST("/dish/uploadFile")
    suspend fun uploadImage(@Body multipartBody: MultipartBody): ApiResult<ApiResponse<String>>
}