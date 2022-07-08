package com.wuhuabin.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wuhuabin.common.utils.StatusBarUtil

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setStatusBarTextColorWhite(this)
    }
}