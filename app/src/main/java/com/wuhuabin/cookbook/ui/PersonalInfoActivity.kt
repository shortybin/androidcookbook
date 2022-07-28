package com.wuhuabin.cookbook.ui

import android.os.Bundle
import com.dylanc.viewbinding.binding
import com.wuhuabin.common.base.BaseActivity
import com.wuhuabin.cookbook.databinding.ActivityPersonalInfoBinding

class PersonalInfoActivity : BaseActivity() {
    private val binding: ActivityPersonalInfoBinding by binding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.titleView.setTitleText("个人信息")
        binding.titleView.setRightText("修改")
        binding.titleView.setRightTextColor("#3F51B5")

        binding.titleView.setRightOnClickListener {

        }
    }
}