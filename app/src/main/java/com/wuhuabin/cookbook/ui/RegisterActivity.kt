package com.wuhuabin.cookbook.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.dylanc.viewbinding.binding
import com.wuhuabin.common.base.BaseActivity
import com.wuhuabin.cookbook.databinding.ActivityRegisterBinding
import com.wuhuabin.cookbook.utils.ToastUtils
import com.wuhuabin.cookbook.viewmodel.RegisterViewModel

class RegisterActivity : BaseActivity() {
    private val binding: ActivityRegisterBinding by binding()
    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.titleView.setTitleText("注册")
        binding.titleView.setLeftOnClickListener {
            finish()
        }

        binding.registerText.setOnClickListener {
            if (binding.userNameEdit.text.toString().isNotEmpty()) {
                if (binding.passwordEdit.text.toString().isNotEmpty()) {
                    registerViewModel.register(
                        binding.userNameEdit.text.toString(),
                        binding.passwordEdit.text.toString()
                    )
                } else {
                    ToastUtils.showCenter("请输入密码")
                }
            } else {
                ToastUtils.showCenter("请输入用户名")
            }
        }

        registerViewModel.loadingState.observe(this) {
            if (it.isLoading) {
                loadingDialog.setText(it.loadingText)
                loadingDialog.show()
            } else {
                loadingDialog.dismiss()
            }
        }

        registerViewModel.toastMessage.observe(this) {
            ToastUtils.showCenter(it)
        }

        registerViewModel.registerSuccess.observe(this) {
            ToastUtils.showCenter(it)
            finish()
        }
    }
}