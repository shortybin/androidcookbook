package com.wuhuabin.cookbook.viewmodel

import androidx.lifecycle.viewModelScope
import com.wuhuabin.common.base.ListViewModel
import com.wuhuabin.cookbook.api.CookBookAPi
import com.wuhuabin.cookbook.bean.DishDetailsBean
import com.wuhuabin.net.ApiResponse
import com.wuhuabin.net.ApiResult
import kotlinx.coroutines.launch

class CategoryDishListViewModel : ListViewModel<List<DishDetailsBean>>() {

    fun getCategoryDishList(categoryId: Int, page: Int) {
        viewModelScope.launch {
            when (val result =
                CookBookAPi.create().getCategoryDishList(categoryId, 2, "", page, 20)) {
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
}