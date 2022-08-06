package com.wuhuabin.cookbook.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.dylanc.viewbinding.binding
import com.wuhuabin.common.base.BaseActivity
import com.wuhuabin.cookbook.databinding.ActivityPersonalInfoBinding
import com.wuhuabin.cookbook.utils.ToastUtils
import com.wuhuabin.cookbook.utils.UserInfoUtils
import com.wuhuabin.cookbook.viewmodel.PersonalInfoViewModel

class PersonalInfoActivity : BaseActivity() {
    private val binding: ActivityPersonalInfoBinding by binding()
    private val personalInfoViewModel: PersonalInfoViewModel by viewModels()

    //1 正常状态 2 修改状态
    private var status = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.titleView.setTitleText("个人信息")
        binding.titleView.setRightText("修改")
        binding.titleView.setRightTextColor("#3F51B5")
        binding.userNameText.text = UserInfoUtils.getUser()!!.username

        binding.titleView.setLeftOnClickListener {
            finish()
        }

        binding.titleView.setRightOnClickListener {
            if (status == 1) {
                binding.titleView.setRightText("完成")
                binding.userNameText.visibility = View.GONE
                binding.userNameEdit.visibility = View.VISIBLE
                binding.userNameEdit.setText(UserInfoUtils.getUser()!!.username)
                status = 2
            } else if (status == 2) {
                personalInfoViewModel.changeUserName(binding.userNameEdit.text.toString())
            }
        }

        personalInfoViewModel.loadingState.observe(this) {
            if (it.isLoading) {
                loadingDialog.setText(it.loadingText)
                loadingDialog.show()
            } else {
                loadingDialog.dismiss()
            }
        }

        personalInfoViewModel.toastMessage.observe(this) {
            ToastUtils.showCenter(it)
        }

        personalInfoViewModel.userNameChangeSuccess.observe(this) {
            binding.userNameText.text = binding.userNameEdit.text.toString()
            binding.titleView.setRightText("修改")
            binding.userNameText.visibility = View.VISIBLE
            binding.userNameEdit.visibility = View.GONE

            status = 1
        }
    }
}