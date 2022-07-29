package com.wuhuabin.cookbook.ui

import android.os.Bundle
import com.dylanc.viewbinding.binding
import com.wuhuabin.common.base.BaseActivity
import com.wuhuabin.cookbook.databinding.ActivityConnectBinding

class ConnectActivity : BaseActivity() {

    private val binding: ActivityConnectBinding by binding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.titleView.setTitleText("联系我们")
        binding.titleView.setLeftOnClickListener {
            finish()
        }
    }
}