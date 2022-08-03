package com.wuhuabin.cookbook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wuhuabin.common.base.BaseViewModel
import com.wuhuabin.cookbook.api.CookBookAPi
import com.wuhuabin.cookbook.bean.DishDetailsBean
import com.wuhuabin.net.ApiResponse
import com.wuhuabin.net.ApiResult
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {
    val homeDishList = MutableLiveData<List<DishDetailsBean>>()

    fun homeList(page: Int) {
        viewModelScope.launch {
            when (val result = CookBookAPi.create().homeDishList(page, 20)) {
                is ApiResult.Success<ApiResponse<List<DishDetailsBean>>> -> {
                    homeDishList.value = result.bean.data
                }
                is ApiResult.Failure -> {
                    toastMessage.value = result.errorMsg
                }
            }
        }
    }
}