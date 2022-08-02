package com.wuhuabin.cookbook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wuhuabin.common.base.BaseViewModel
import com.wuhuabin.common.bean.LoadingState
import com.wuhuabin.cookbook.api.CookBookAPi
import com.wuhuabin.cookbook.bean.UserBean
import com.wuhuabin.cookbook.utils.UserInfoUtils
import com.wuhuabin.net.ApiResponse
import com.wuhuabin.net.ApiResult
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {
    val loginSuccess = MutableLiveData<UserBean>()

    fun login(userName: String, password: String) {
        loadingState.value = LoadingState(true, "登录中...")
        viewModelScope.launch {
            when (val result = CookBookAPi.create().userLogin(userName, password)) {
                is ApiResult.Success<ApiResponse<UserBean>> -> {
                    loadingState.value = LoadingState(false)
                    UserInfoUtils.putUser(result.bean.data)
                    loginSuccess.value = result.bean.data
                }
                is ApiResult.Failure -> {
                    toastMessage.value = result.errorMsg
                    loadingState.value = LoadingState(false)
                }
            }
        }
    }
}