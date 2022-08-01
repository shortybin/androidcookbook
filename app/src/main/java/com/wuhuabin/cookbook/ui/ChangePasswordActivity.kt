package com.wuhuabin.cookbook.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.dylanc.viewbinding.binding
import com.wuhuabin.common.base.BaseActivity
import com.wuhuabin.cookbook.databinding.ActivityChangePasswordBinding
import com.wuhuabin.cookbook.viewmodel.ChangePasswordViewModel

class ChangePasswordActivity : BaseActivity() {
    private val binding: ActivityChangePasswordBinding by binding()
    private val changePasswordViewModel: ChangePasswordViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.titleView.setTitleText("修改密码")
        binding.titleView.setLeftOnClickListener {
            finish()
        }
    }
}