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
import com.wuhuabin.cookbook.utils.ToastUtils
import com.wuhuabin.cookbook.utils.UserInfoUtils

class MineFragment : Fragment(R.layout.mine_fragment) {

    private val binding: MineFragmentBinding by binding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.infoItem.setItemText("个人信息")
        binding.auditItem.setItemText("待审核的菜谱")
        binding.changePasswordItem.setItemText("修改密码")
        binding.connectItem.setItemText("联系我们")
        binding.aboutItem.setItemText("关于我们")

        binding.mineName.setOnClickListener {
            if (!UserInfoUtils.isLogin()) {
                startActivity(Intent(context, LoginActivity::class.java))
            }
        }

        binding.infoItem.setOnClickListener {
            if (UserInfoUtils.isLogin()) {
                startActivity(Intent(context, PersonalInfoActivity::class.java))
            } else {
                ToastUtils.showCenter("请先登录")
            }
        }

        binding.releaseItem.setOnClickListener {
            if (UserInfoUtils.isLogin()) {
                startActivity(Intent(context, MyReleaseActivity::class.java))
            } else {
                ToastUtils.showCenter("请先登录")
            }
        }

        binding.changePasswordItem.setOnClickListener {
            if (UserInfoUtils.isLogin()) {
                startActivity(Intent(context, ChangePasswordActivity::class.java))
            } else {
                ToastUtils.showCenter("请先登录")
            }
        }

        binding.connectItem.setOnClickListener {
            startActivity(Intent(context, ConnectActivity::class.java))
        }

        binding.aboutItem.setOnClickListener {
            startActivity(Intent(context, AboutActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        if (UserInfoUtils.isLogin()) {
            binding.mineName.text = UserInfoUtils.getUser()?.username
            binding.logoutText.visibility = View.VISIBLE
            binding.logoutText.setOnClickListener {
                UserInfoUtils.logout()
                startActivity(Intent(context, LoginActivity::class.java))
            }
            if (UserInfoUtils.getUser()?.role == 0) {
                binding.auditItem.visibility = View.VISIBLE
                binding.auditItem.setOnClickListener {
                    startActivity(Intent(context, AuditDishActivity::class.java))
                }
            } else {
                binding.auditItem.visibility = View.GONE
            }
        } else {
            binding.mineName.text = "登录/注册"
            binding.logoutText.visibility = View.GONE
            binding.auditItem.visibility = View.GONE
        }
    }
}