package com.wuhuabin.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wuhuabin.common.utils.StatusBarUtil
import com.wuhuabin.common.view.LoadingDialog

open class BaseActivity : AppCompatActivity() {
    lateinit var loadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setStatusBarTextColorWhite(this)
        loadingDialog = LoadingDialog(this)
    }
}