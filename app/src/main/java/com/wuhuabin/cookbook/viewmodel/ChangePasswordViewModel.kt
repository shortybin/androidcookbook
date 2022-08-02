package com.wuhuabin.cookbook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wuhuabin.common.base.BaseViewModel
import com.wuhuabin.common.bean.LoadingState
import com.wuhuabin.cookbook.api.CookBookAPi
import com.wuhuabin.cookbook.utils.UserInfoUtils
import com.wuhuabin.net.ApiResponse
import com.wuhuabin.net.ApiResult
import kotlinx.coroutines.launch

class ChangePasswordViewModel : BaseViewModel() {
    val passwordChangeSuccess = MutableLiveData<String>()

    fun changePassword(password: String, confirmPassword: String) {
        loadingState.value = LoadingState(true, "修改密码中...")
        viewModelScope.launch {
            when (val result = CookBookAPi.create()
                .changePassword(UserInfoUtils.getUser()!!.userid, password, confirmPassword)) {
                is ApiResult.Success<ApiResponse<String>> -> {
                    loadingState.value = LoadingState(false)
                    passwordChangeSuccess.value = result.bean.data
                    UserInfoUtils.logout()
                }
                is ApiResult.Failure -> {
                    toastMessage.value = result.errorMsg
                    loadingState.value = LoadingState(false)
                }
            }
        }
    }
}