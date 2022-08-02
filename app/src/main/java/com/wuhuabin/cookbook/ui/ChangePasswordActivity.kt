package com.wuhuabin.cookbook.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.dylanc.viewbinding.binding
import com.wuhuabin.common.base.BaseActivity
import com.wuhuabin.cookbook.databinding.ActivityChangePasswordBinding
import com.wuhuabin.cookbook.utils.ToastUtils
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

        binding.changeText.setOnClickListener {
            if (binding.passwordEdit.text.toString().isNotEmpty()) {
                if (binding.confirmPasswordEdit.text.toString().isNotEmpty()) {
                    changePasswordViewModel.changePassword(
                        binding.passwordEdit.text.toString(),
                        binding.confirmPasswordEdit.text.toString()
                    )
                } else {
                    ToastUtils.showCenter("请输入二次确认密码")
                }
            } else {
                ToastUtils.showCenter("请输入新密码")
            }
        }

        changePasswordViewModel.loadingState.observe(this) {
            if (it.isLoading) {
                loadingDialog.setText(it.loadingText)
                loadingDialog.show()
            } else {
                loadingDialog.dismiss()
            }
        }

        changePasswordViewModel.toastMessage.observe(this) {
            ToastUtils.showCenter(it)
        }

        changePasswordViewModel.passwordChangeSuccess.observe(this) {
            ToastUtils.showCenter(it)
            finish()
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}