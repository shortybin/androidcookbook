package com.wuhuabin.cookbook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wuhuabin.common.base.BaseViewModel
import com.wuhuabin.common.bean.LoadingState
import com.wuhuabin.cookbook.api.CookBookAPi
import com.wuhuabin.cookbook.bean.UserBean
import com.wuhuabin.net.ApiResponse
import com.wuhuabin.net.ApiResult
import kotlinx.coroutines.launch

class RegisterViewModel : BaseViewModel() {
    val registerSuccess = MutableLiveData<String>()

    fun register(userName: String, password: String) {
        loadingState.value = LoadingState(true, "注册中...")
        viewModelScope.launch {
            when (val result = CookBookAPi.create().userRegister(userName, password)) {
                is ApiResult.Success<ApiResponse<String>> -> {
                    registerSuccess.value = result.bean.data
                    loadingState.value = LoadingState(false)
                }
                is ApiResult.Failure -> {
                    toastMessage.value = result.errorMsg
                    loadingState.value = LoadingState(false)
                }
            }
        }
    }
}