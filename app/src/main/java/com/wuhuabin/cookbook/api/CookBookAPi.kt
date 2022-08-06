package com.wuhuabin.cookbook.api

import com.wuhuabin.cookbook.bean.*
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
        @Field("password") password: String,
        @Field("confirmPassword") confirmPassword: String
    ): ApiResult<ApiResponse<String>>

    /**
     * 修改用户名
     */
    @FormUrlEncoded
    @POST("/user/changeUserName")
    suspend fun changeUserName(
        @Field("userId") userId: Int,
        @Field("userName") userName: String,
    ): ApiResult<ApiResponse<String>>

    /**
     * 获取菜谱分类
     */
    @GET("/category/getCategoryList")
    suspend fun getCategory(): ApiResult<ApiResponse<List<CategoryBean>>>

    /**
     * 添加菜谱
     */
    @FormUrlEncoded
    @POST("/dish/saveOrUpdateDish")
    suspend fun addDish(
        @Field("dishJson") dishJson: String,
        @Field("dishIngredientJson") dishIngredientJson: String,
        @Field("dishStepJson") dishStepJson: String
    ): ApiResult<ApiResponse<String>>

    /**
     * 获取首页菜谱列表
     */
    @FormUrlEncoded
    @POST("/dish/randomGetDishList")
    suspend fun homeDishList(
        @Field("pageNum") pageNum: Int,
        @Field("pageSize") pageSize: Int
    ): ApiResult<ApiResponse<List<DishDetailsBean>>>


    /**
     * 获取分类的菜谱
     */
    @FormUrlEncoded
    @POST("/dish/getDishList")
    suspend fun getCategoryDishList(
        @Field("categoryId") categoryId: Int,
        @Field("examineStatus") examineStatus: Int,
        @Field("content") content: String,
        @Field("pageNum") pageNum: Int,
        @Field("pageSize") pageSize: Int
    ): ApiResult<ApiResponse<List<DishDetailsBean>>>

    /**
     *获取菜谱详情
     */
    @FormUrlEncoded
    @POST("/dish/getDishDetail")
    suspend fun getDishDetails(
        @Field("dishId") dishId: Int,
    ): ApiResult<ApiResponse<DishDetailsBean>>

    /**
     *修改菜谱审核状态
     */
    @FormUrlEncoded
    @POST("/dish/updateExamineStatus")
    suspend fun setDishStatus(
        @Field("dishId") dishId: Int,
        @Field("examineStatus") examineStatus: Int
    ): ApiResult<ApiResponse<String>>

    /**
     * 上传图片
     */
    @POST("/dish/uploadFile")
    suspend fun uploadImage(@Body multipartBody: MultipartBody): ApiResult<ApiResponse<String>>
}