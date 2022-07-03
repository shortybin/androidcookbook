package com.wuhuabin.net

sealed class ApiResult<out T>() {
    data class Success<out T>(val bean: T) : ApiResult<T>()
    data class Failure(val errorCode: Int, val errorMsg: String) : ApiResult<Nothing>()
}

data class ApiResponse<out T>(var status: Int, var error: String, val data: T)
