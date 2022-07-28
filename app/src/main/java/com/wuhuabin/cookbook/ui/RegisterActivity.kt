package com.wuhuabin.cookbook.ui

import android.os.Bundle
import com.dylanc.viewbinding.binding
import com.wuhuabin.common.base.BaseActivity
import com.wuhuabin.cookbook.R
import com.wuhuabin.cookbook.databinding.ActivityLoginBinding
import com.wuhuabin.cookbook.databinding.ActivityRegisterBinding

class RegisterActivity : BaseActivity() {
    private val binding: ActivityRegisterBinding by binding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.titleView.setTitleText("注册")
        binding.titleView.setLeftOnClickListener {
            finish()
        }
    }
}