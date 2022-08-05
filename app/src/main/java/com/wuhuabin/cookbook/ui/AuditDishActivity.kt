package com.wuhuabin.cookbook.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dylanc.viewbinding.binding
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
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

    private var page = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.titleView.setTitleText("待审核的菜谱")
        binding.titleView.setLeftOnClickListener {
            finish()
        }
        auditDishAdapter = AuditDishAdapter(auditDishViewModel)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = auditDishAdapter
        binding.smartRefreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                page = 1
                auditDishViewModel.getAuditDishList(page)
            }

            override fun onLoadMore(refreshLayout: RefreshLayout) {
                auditDishViewModel.getAuditDishList(page)
            }
        })

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

        auditDishViewModel.listSetData.observe(this) {
            binding.smartRefreshLayout.finishRefresh()
            auditDishAdapter.setList(it)
        }

        auditDishViewModel.listAddData.observe(this) {
            binding.smartRefreshLayout.finishLoadMore()
            auditDishAdapter.setList(it)
        }

        auditDishViewModel.pageIsNextPage.observe(this) {
            if (it) {
                page++
                binding.smartRefreshLayout.setEnableLoadMore(true)
            } else {
                binding.smartRefreshLayout.setEnableLoadMore(false)
            }
        }

        auditDishViewModel.listSuccess.observe(this) {
            if (!it) {
                if (page == 1) {
                    binding.smartRefreshLayout.finishRefresh()
                } else {
                    binding.smartRefreshLayout.finishLoadMore()
                }
            }
        }

        auditDishViewModel.dishStatusChange.observe(this) {
            auditDishAdapter.removeDishId(it)
        }

        binding.smartRefreshLayout.autoRefresh()
    }
}