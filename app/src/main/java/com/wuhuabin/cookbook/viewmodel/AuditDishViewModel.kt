package com.wuhuabin.cookbook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wuhuabin.common.base.BaseViewModel
import com.wuhuabin.common.base.ListViewModel
import com.wuhuabin.common.bean.LoadingState
import com.wuhuabin.cookbook.api.CookBookAPi
import com.wuhuabin.cookbook.bean.DishDetailsBean
import com.wuhuabin.net.ApiResponse
import com.wuhuabin.net.ApiResult
import kotlinx.coroutines.launch

class AuditDishViewModel : ListViewModel<List<DishDetailsBean>>() {
    val dishStatusChange = MutableLiveData<Int>()

    fun getAuditDishList(page: Int) {
        viewModelScope.launch {
            when (val result =
                CookBookAPi.create().getCategoryDishList(0, 1, "", page, 20)) {
                is ApiResult.Success<ApiResponse<List<DishDetailsBean>>> -> {
                    if (page == 1) {
                        listSetData.value = result.bean.data
                    } else {
                        listAddData.value = result.bean.data
                    }
                    pageIsNextPage.value = result.bean.data.size >= 20

                    listSuccess.value = true
                }
                is ApiResult.Failure -> {
                    toastMessage.value = result.errorMsg
                    listSuccess.value = false
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