package com.wuhuabin.cookbook.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dylanc.viewbinding.binding
import com.wuhuabin.common.GridSpacingItemDecoration
import com.wuhuabin.common.base.BaseActivity
import com.wuhuabin.common.dp2px
import com.wuhuabin.cookbook.Constant
import com.wuhuabin.cookbook.adapter.AuditDishAdapter
import com.wuhuabin.cookbook.adapter.HomeListAdapter
import com.wuhuabin.cookbook.databinding.ActivityAuditDishBinding
import com.wuhuabin.cookbook.utils.ToastUtils
import com.wuhuabin.cookbook.viewmodel.AuditDishViewModel

class AuditDishActivity : BaseActivity() {
    private val binding: ActivityAuditDishBinding by binding()
    private val auditDishViewModel: AuditDishViewModel by viewModels()
    private lateinit var auditDishAdapter: AuditDishAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.titleView.setTitleText("待审核的菜谱")
        auditDishAdapter = AuditDishAdapter(auditDishViewModel)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = auditDishAdapter

        auditDishViewModel.toastMessage.observe(this) {
            ToastUtils.showCenter(it)
        }

        auditDishViewModel.loadingState.observe(this) {
            if (it.isLoading) {
                loadingDialog.setText(it.loadingText)
                loadingDialog.show()
            } else {
                loadingDialog.dismiss()
            }
        }

        auditDishViewModel.auditDishList.observe(this) {
            auditDishAdapter.setList(it)
        }

        auditDishViewModel.dishStatusChange.observe(this) {
            auditDishAdapter.removeDishId(it)
        }

        auditDishViewModel.getAuditDishList(
            1
        )
    }
}