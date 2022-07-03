package com.wuhuabin.cookbook.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wuhuabin.common.base.BaseViewModel
import com.wuhuabin.cookbook.api.CookBookAPi
import com.wuhuabin.cookbook.bean.UserBean
import com.wuhuabin.net.ApiResponse
import com.wuhuabin.net.ApiResult
import kotlinx.coroutines.launch

/**
 * Created by shortybin
 * on 2022/7/2
 */
class UserViewModel : BaseViewModel() {

    fun login() {
        viewModelScope.launch {
            when (val result = CookBookAPi.create().userLogin("测试", "123456")) {
                is ApiResult.Success<ApiResponse<UserBean>> -> {
                    Log.d("test", result.bean.data.toString())
                }
                is ApiResult.Failure -> {

                }
            }
        }
    }
}