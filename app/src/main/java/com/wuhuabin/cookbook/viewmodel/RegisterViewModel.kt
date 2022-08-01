package com.wuhuabin.cookbook.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.wuhuabin.common.base.BaseViewModel
import com.wuhuabin.common.bean.LoadingState
import com.wuhuabin.cookbook.api.CookBookAPi
import com.wuhuabin.cookbook.bean.UserBean
import com.wuhuabin.net.ApiResponse
import com.wuhuabin.net.ApiResult
import kotlinx.coroutines.launch

class RegisterViewModel : BaseViewModel() {

    fun register(userName: String, password: String) {
        loadingState.value = LoadingState(true, "注册中...")
        viewModelScope.launch {
            when (val result = CookBookAPi.create().userRegister(userName, password)) {
                is ApiResult.Success<ApiResponse<UserBean>> -> {
                    //loadingState.value = LoadingState(false)
                    Log.d("test", result.bean.data.toString())
                }
                is ApiResult.Failure -> {
                    //loadingState.value = LoadingState(false)
                }
            }
        }
    }
}