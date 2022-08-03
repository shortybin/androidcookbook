package com.wuhuabin.cookbook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wuhuabin.common.base.BaseViewModel
import com.wuhuabin.cookbook.api.CookBookAPi
import com.wuhuabin.cookbook.bean.DishDetailsBean
import com.wuhuabin.net.ApiResponse
import com.wuhuabin.net.ApiResult
import kotlinx.coroutines.launch

class CategoryDishListViewModel : BaseViewModel() {
    val dishList = MutableLiveData<List<DishDetailsBean>>()

    fun getCategoryDishList(categoryId: Int, pageNum: Int) {
        viewModelScope.launch {
            when (val result = CookBookAPi.create().getCategoryDishList(categoryId, pageNum, 20)) {
                is ApiResult.Success<ApiResponse<List<DishDetailsBean>>> -> {
                    dishList.value = result.bean.data
                }
                is ApiResult.Failure -> {
                    toastMessage.value = result.errorMsg
                }
            }
        }
    }
}