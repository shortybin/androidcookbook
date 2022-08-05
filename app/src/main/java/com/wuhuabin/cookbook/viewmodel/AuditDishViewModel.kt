package com.wuhuabin.cookbook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wuhuabin.common.base.BaseViewModel
import com.wuhuabin.common.bean.LoadingState
import com.wuhuabin.cookbook.api.CookBookAPi
import com.wuhuabin.cookbook.bean.DishDetailsBean
import com.wuhuabin.net.ApiResponse
import com.wuhuabin.net.ApiResult
import kotlinx.coroutines.launch

class AuditDishViewModel : BaseViewModel() {
    val auditDishList = MutableLiveData<List<DishDetailsBean>>()
    val dishStatusChange = MutableLiveData<Int>()

    fun getAuditDishList(pageNum: Int) {
        viewModelScope.launch {
            when (val result =
                CookBookAPi.create().getCategoryDishList(0, 1, "", pageNum, 20)) {
                is ApiResult.Success<ApiResponse<List<DishDetailsBean>>> -> {
                    auditDishList.value = result.bean.data
                }
                is ApiResult.Failure -> {
                    toastMessage.value = result.errorMsg
                }
            }
        }
    }

    fun setDishStatus(dishId: Int) {
        loadingState.value = LoadingState(true, "审核中...")
        viewModelScope.launch {
            when (val result =
                CookBookAPi.create().setDishStatus(dishId, 2)) {
                is ApiResult.Success<ApiResponse<String>> -> {
                    loadingState.value = LoadingState(false)
                    dishStatusChange.value = dishId
                    toastMessage.value = "审核通过"
                }
                is ApiResult.Failure -> {
                    loadingState.value = LoadingState(false)
                    toastMessage.value = result.errorMsg
                }
            }
        }
    }
}