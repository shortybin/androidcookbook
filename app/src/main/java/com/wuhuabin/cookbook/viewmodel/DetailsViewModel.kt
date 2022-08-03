package com.wuhuabin.cookbook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wuhuabin.common.base.BaseViewModel
import com.wuhuabin.cookbook.api.CookBookAPi
import com.wuhuabin.cookbook.bean.DishDetailsBean
import com.wuhuabin.net.ApiResponse
import com.wuhuabin.net.ApiResult
import kotlinx.coroutines.launch

class DetailsViewModel : BaseViewModel() {
    val dishDetails = MutableLiveData<DishDetailsBean>()

    fun getDetails(dishId: Int) {
        viewModelScope.launch {
            when (val result = CookBookAPi.create().getDishDetails(dishId)) {
                is ApiResult.Success<ApiResponse<DishDetailsBean>> -> {
                    dishDetails.value = result.bean.data
                }
                is ApiResult.Failure -> {
                    toastMessage.value = result.errorMsg
                }
            }
        }
    }
}