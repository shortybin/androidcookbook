package com.wuhuabin.common.base

import androidx.lifecycle.MutableLiveData

open class ListViewModel<T> : BaseViewModel() {
    //添加数据
    val listAddData = MutableLiveData<T>()

    //设置数据
    val listSetData = MutableLiveData<T>()

    //是否能翻页
    val pageIsNextPage = MutableLiveData<Boolean>()

    //是否加载成功
    val listSuccess = MutableLiveData<Boolean>()

    //是否是空页面
    val emptyData = MutableLiveData<Boolean>()
}