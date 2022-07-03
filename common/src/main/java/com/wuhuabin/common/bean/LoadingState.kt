package com.wuhuabin.common.bean

data class LoadingState(
    val isLoading: Boolean,
    val loadingText: String
) {
    constructor(isLoading: Boolean) : this(isLoading, "")
}