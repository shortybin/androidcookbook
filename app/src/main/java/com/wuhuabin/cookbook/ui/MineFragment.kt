package com.wuhuabin.cookbook.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dylanc.viewbinding.binding
import com.wuhuabin.cookbook.R
import com.wuhuabin.cookbook.databinding.CategoryFragmentBinding
import com.wuhuabin.cookbook.databinding.MineFragmentBinding

class MineFragment : Fragment(R.layout.mine_fragment) {

    private val binding: MineFragmentBinding by binding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.infoItem.setItemText("个人信息")
        binding.releaseItem.setItemText("我发布的菜谱")
        binding.changePasswordItem.setItemText("修改密码")
        binding.connectItem.setItemText("联系我们")
        binding.aboutItem.setItemText("关于我们")

        binding.mineName.setOnClickListener {
            startActivity(Intent(context, LoginActivity::class.java))
        }
    }
}