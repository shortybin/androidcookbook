package com.wuhuabin.cookbook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wuhuabin.common.base.BaseViewModel
import com.wuhuabin.cookbook.api.CookBookAPi
import com.wuhuabin.cookbook.bean.CategoryBean
import com.wuhuabin.net.ApiResponse
import com.wuhuabin.net.ApiResult
import kotlinx.coroutines.launch

class CategoryViewModel : BaseViewModel() {
    val categoryList = MutableLiveData<List<CategoryBean>>()

    fun getCategory() {
        viewModelScope.launch {
            when (val result = CookBookAPi.create().getCategory()) {
                is ApiResult.Success<ApiResponse<List<CategoryBean>>> -> {
                    categoryList.value = result.bean.data
                }
                is ApiResult.Failure -> {
                    toastMessage.value = result.errorMsg
                }
            }
        }
    }
}