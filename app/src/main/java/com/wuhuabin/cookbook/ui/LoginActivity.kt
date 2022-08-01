package com.wuhuabin.cookbook.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import com.dylanc.viewbinding.binding
import com.wuhuabin.common.base.BaseActivity
import com.wuhuabin.cookbook.R
import com.wuhuabin.cookbook.databinding.ActivityLoginBinding
import com.wuhuabin.cookbook.viewmodel.LoginViewModel

class LoginActivity : BaseActivity() {
    private val binding: ActivityLoginBinding by binding()
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.titleView.setTitleText("登录")
        binding.titleView.setRightText("注册")
        binding.titleView.setRightTextColor("#3F51B5")
        binding.titleView.setLeftOnClickListener {
            finish()
        }
        binding.titleView.setRightOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}