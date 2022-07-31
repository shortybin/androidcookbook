package com.wuhuabin.cookbook.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.wuhuabin.common.base.BaseViewModel
import com.wuhuabin.cookbook.api.CookBookAPi
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

    fun uploadImage(inputStream: InputStream) {
        val builder = MultipartBody.Builder()
        val requestBody: RequestBody =
            RequestBody.create(MediaType.parse("image/png"), inputStream.readBytes())
        builder.addFormDataPart("file", "app.png", requestBody)
        builder.setType(MultipartBody.FORM)
        val multipartBody = builder.build()

        viewModelScope.launch {
            when (val result = CookBookAPi.create().uploadImage(multipartBody)) {
                is ApiResult.Success<ApiResponse<String>> -> {
                    Log.d("test", result.bean.data)
                }
                is ApiResult.Failure -> {

                }
            }
        }
    }
}