package com.wuhuabin.common.utils

import android.app.Activity
import android.view.View
import androidx.core.view.ViewCompat

class StatusBarUtil {
    companion object {
        fun setStatusBarTextColorWhite(activity: Activity) {
            val windowInsetsController =
                ViewCompat.getWindowInsetsController(activity.window.decorView)
            windowInsetsController?.isAppearanceLightStatusBars = true
        }
    }
}