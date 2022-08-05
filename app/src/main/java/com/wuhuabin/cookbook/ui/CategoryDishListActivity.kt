package com.wuhuabin.cookbook.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.dylanc.viewbinding.binding
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.wuhuabin.common.GridSpacingItemDecoration
import com.wuhuabin.common.base.BaseActivity
import com.wuhuabin.common.dp2px
import com.wuhuabin.cookbook.Constant
import com.wuhuabin.cookbook.R
import com.wuhuabin.cookbook.adapter.HomeListAdapter
import com.wuhuabin.cookbook.databinding.ActivityCategoryDishListBinding
import com.wuhuabin.cookbook.databinding.ActivityDetailsBinding
import com.wuhuabin.cookbook.viewmodel.CategoryDishListViewModel

class CategoryDishListActivity : BaseActivity() {
    private val binding: ActivityCategoryDishListBinding by binding()
    private val categoryDishListViewModel: CategoryDishListViewModel by viewModels()
    private val homeListAdapter = HomeListAdapter()

    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.titleView.setTitleText(intent.getStringExtra(Constant.CATEGORY_NAME)!!)
        binding.titleView.setLeftOnClickListener {
            finish()
        }
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.adapter = homeListAdapter
        binding.recyclerView.addItemDecoration(GridSpacingItemDecoration(2, 10f.dp2px(), false))

        binding.smartRefreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                page = 1
                categoryDishListViewModel.getCategoryDishList(
                    intent.getIntExtra(Constant.CATEGORY_ID, 0),
                    page
                )
            }

            override fun onLoadMore(refreshLayout: RefreshLayout) {
                categoryDishListViewModel.getCategoryDishList(
                    intent.getIntExtra(Constant.CATEGORY_ID, 0),
                    page
                )
            }
        })

        categoryDishListViewModel.listSetData.observe(this) {
            binding.smartRefreshLayout.finishRefresh()
            homeListAdapter.setList(it)
        }

        categoryDishListViewModel.listAddData.observe(this) {
            binding.smartRefreshLayout.finishLoadMore()
            homeListAdapter.setList(it)
        }

        categoryDishListViewModel.pageIsNextPage.observe(this) {
            if (it) {
                page++
                binding.smartRefreshLayout.setEnableLoadMore(true)
            } else {
                binding.smartRefreshLayout.setEnableLoadMore(false)
            }
        }

        categoryDishListViewModel.listSuccess.observe(this) {
            if (!it) {
                if (page == 1) {
                    binding.smartRefreshLayout.finishRefresh()
                } else {
                    binding.smartRefreshLayout.finishLoadMore()
                }
            }
        }

        binding.smartRefreshLayout.autoRefresh()
    }
}