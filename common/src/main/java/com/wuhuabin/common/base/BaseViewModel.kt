package com.wuhuabin.common.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wuhuabin.common.bean.LoadingState

open class BaseViewModel : ViewModel() {
    val loadingState = MutableLiveData<LoadingState>()
    val toastMessage = MutableLiveData<String>()
}