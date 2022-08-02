package com.wuhuabin.cookbook.utils

import com.wuhuabin.common.utils.GsonUtils
import com.wuhuabin.cookbook.bean.UserBean

class UserInfoUtils {
    companion object {
        private const val USER_INFO_CACHE = "user_info_cache"
        private const val USER_INFO = "user_info"

        var sharedPreferencesUtils: SharedPreferencesUtils =
            SharedPreferencesUtils(USER_INFO_CACHE)

        fun putUser(userBean: UserBean) {
            sharedPreferencesUtils.put(USER_INFO, GsonUtils.toJson(userBean))
        }

        fun getUser(): UserBean? {
            val user = sharedPreferencesUtils.getString(USER_INFO)
            return if (user != null) {
                GsonUtils.getObjectByStr(user, UserBean::class.java)
            } else {
                null
            }
        }

        fun isLogin(): Boolean {
            return getUser() != null
        }

        fun logout() {
            sharedPreferencesUtils.clear()
        }
    }
}