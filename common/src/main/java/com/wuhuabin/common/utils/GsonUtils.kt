package com.wuhuabin.common.utils

import com.google.gson.GsonBuilder

class GsonUtils {
    companion object {
        fun toJson(any: Any): String {
            val gson = GsonBuilder().serializeNulls().create()
            return gson.toJson(any)
        }

        fun <T> getObjectByStr(value: String?, clss: Class<T>): T {
            val gson = GsonBuilder().create()
            return gson.fromJson(value, clss)
        }
    }
}