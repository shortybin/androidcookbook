package com.wuhuabin.cookbook.ui

import android.os.Bundle
import com.dylanc.viewbinding.binding
import com.wuhuabin.common.base.BaseActivity
import com.wuhuabin.cookbook.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity() {
    private val binding: ActivityLoginBinding by binding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.titleView.setTitleText("登录")
        binding.titleView.setLeftOnClickListener {
            finish()
        }
    }
}