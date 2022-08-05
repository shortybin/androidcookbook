package com.wuhuabin.cookbook.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.dylanc.viewbinding.binding
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.wuhuabin.common.GridSpacingItemDecoration
import com.wuhuabin.cookbook.R
import com.wuhuabin.cookbook.adapter.HomeListAdapter
import com.wuhuabin.cookbook.databinding.HomeFragmentBinding
import com.wuhuabin.common.dp2px
import com.wuhuabin.cookbook.utils.ToastUtils
import com.wuhuabin.cookbook.utils.UserInfoUtils
import com.wuhuabin.cookbook.viewmodel.HomeViewModel

class HomeFragment : Fragment(R.layout.home_fragment) {
    private val binding: HomeFragmentBinding by binding()
    private val homeViewModel: HomeViewModel by viewModels()
    private val homeListAdapter = HomeListAdapter()

    private var page = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.adapter = homeListAdapter
        binding.recyclerView.addItemDecoration(GridSpacingItemDecoration(2, 10f.dp2px(), false))

        binding.smartRefreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                page = 1
                homeViewModel.homeList(page)
            }

            override fun onLoadMore(refreshLayout: RefreshLayout) {
                homeViewModel.homeList(page)
            }
        })

        binding.searchLayout.setOnClickListener {
            startActivity(Intent(context, SearchActivity::class.java))
        }

        binding.homeAddMenu.setOnClickListener {
            if (UserInfoUtils.isLogin()) {
                startActivity(Intent(context, AddCookbookActivity::class.java))
            } else {
                ToastUtils.showCenter("请先登录")
            }
        }

        homeViewModel.listSetData.observe(viewLifecycleOwner) {
            binding.smartRefreshLayout.finishRefresh()
            homeListAdapter.setList(it)
        }

        homeViewModel.listAddData.observe(viewLifecycleOwner) {
            binding.smartRefreshLayout.finishLoadMore()
            homeListAdapter.setList(it)
        }

        homeViewModel.pageIsNextPage.observe(viewLifecycleOwner) {
            if (it) {
                page++
                binding.smartRefreshLayout.setEnableLoadMore(true)
            } else {
                binding.smartRefreshLayout.setEnableLoadMore(false)
            }
        }

        binding.smartRefreshLayout.autoRefresh()
    }
}