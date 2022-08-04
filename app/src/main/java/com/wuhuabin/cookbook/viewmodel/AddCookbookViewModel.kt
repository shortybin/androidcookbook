package com.wuhuabin.cookbook.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wuhuabin.common.base.BaseViewModel
import com.wuhuabin.common.bean.LoadingState
import com.wuhuabin.cookbook.api.CookBookAPi
import com.wuhuabin.cookbook.bean.CategoryBean
import com.wuhuabin.cookbook.bean.UploadImageBean
import com.wuhuabin.cookbook.bean.UserBean
import com.wuhuabin.net.ApiResponse
import com.wuhuabin.net.ApiResult
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody

import okhttp3.RequestBody
import java.io.File
import java.io.InputStream


/**
 * Created by shortybin
 * on 2022/7/30
 */
class AddCookbookViewModel : BaseViewModel() {
    val uploadImageSuccess = MutableLiveData<String>()
    val addDishSuccess = MutableLiveData<String>()
    val categoryList = MutableLiveData<List<CategoryBean>>()

    fun uploadImage(inputStream: InputStream) {
        loadingState.value = LoadingState(true, "添加菜谱中...")
        val builder = MultipartBody.Builder()
        val requestBody: RequestBody =
            RequestBody.create(MediaType.parse("image/png"), inputStream.readBytes())
        builder.addFormDataPart("file", "app.png", requestBody)
        builder.setType(MultipartBody.FORM)
        val multipartBody = builder.build()

        viewModelScope.launch {
            when (val result = CookBookAPi.create().uploadImage(multipartBody)) {
                is ApiResult.Success<ApiResponse<String>> -> {
                    uploadImageSuccess.value = result.bean.data
                }
                is ApiResult.Failure -> {
                    loadingState.value = LoadingState(false)
                    toastMessage.value = result.errorMsg
                }
            }
        }
    }

    fun addDish(dishJson: String, dishIngredientJson: String, dishStepJson: String) {
        viewModelScope.launch {
            when (val result =
                CookBookAPi.create().addDish(dishJson, dishIngredientJson, dishStepJson)) {
                is ApiResult.Success<ApiResponse<String>> -> {
                    loadingState.value = LoadingState(false)
                    addDishSuccess.value = result.bean.data
                }
                is ApiResult.Failure -> {
                    loadingState.value = LoadingState(false)
                    toastMessage.value = result.errorMsg
                }
            }
        }
    }

    fun getCategory() {
        loadingState.value = LoadingState(true, "获取菜谱分类...")
        viewModelScope.launch {
            when (val result = CookBookAPi.create().getCategory()) {
                is ApiResult.Success<ApiResponse<List<CategoryBean>>> -> {
                    loadingState.value = LoadingState(false)
                    categoryList.value = result.bean.data
                }
                is ApiResult.Failure -> {
                    loadingState.value = LoadingState(false)
                    toastMessage.value = result.errorMsg
                }
            }
        }
    }
}