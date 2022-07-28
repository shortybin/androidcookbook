package com.wuhuabin.cookbook.ui

import android.os.Bundle
import com.dylanc.viewbinding.binding
import com.wuhuabin.common.base.BaseActivity
import com.wuhuabin.cookbook.databinding.ActivityChangePasswordBinding

class ChangePasswordActivity : BaseActivity() {
    private val binding: ActivityChangePasswordBinding by binding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.titleView.setTitleText("修改密码")
    }
}