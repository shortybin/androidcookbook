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

class PersonalInfoViewModel : BaseViewModel() {
    val userNameChangeSuccess = MutableLiveData<String>()

    fun changeUserName(userName: String) {
        loadingState.value = LoadingState(true, "修改用户名中...")
        viewModelScope.launch {
            when (val result =
                CookBookAPi.create().changeUserName(UserInfoUtils.getUser()!!.userid, userName)) {
                is ApiResult.Success<ApiResponse<String>> -> {
                    loadingState.value = LoadingState(false)
                    val user = UserInfoUtils.getUser()
                    user!!.username = userName
                    UserInfoUtils.putUser(user)
                    userNameChangeSuccess.value = result.bean.data
                }
                is ApiResult.Failure -> {
                    toastMessage.value = result.errorMsg
                    loadingState.value = LoadingState(false)
                }
            }
        }
    }
}