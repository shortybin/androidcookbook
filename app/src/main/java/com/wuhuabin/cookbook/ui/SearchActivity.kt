package com.wuhuabin.cookbook.ui

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.dylanc.viewbinding.binding
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.wuhuabin.common.GridSpacingItemDecoration
import com.wuhuabin.common.base.BaseActivity
import com.wuhuabin.common.dp2px
import com.wuhuabin.cookbook.adapter.HomeListAdapter
import com.wuhuabin.cookbook.databinding.ActivitySearchBinding
import com.wuhuabin.cookbook.viewmodel.SearchViewModel

class SearchActivity : BaseActivity() {
    private val binding: ActivitySearchBinding by binding()
    private val searchViewModel: SearchViewModel by viewModels()
    private val homeListAdapter = HomeListAdapter()

    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.leftImage.setOnClickListener {
            finish()
        }

        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.adapter = homeListAdapter
        binding.recyclerView.addItemDecoration(GridSpacingItemDecoration(2, 10f.dp2px(), false))

        binding.searchEdit.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                if (binding.searchEdit.text.isNotEmpty()) {
                    page = 1
                    searchViewModel.search(binding.searchEdit.text.toString(), page)
                }
                true
            } else {
                false
            }
        }

        binding.smartRefreshLayout.setOnLoadMoreListener {
            searchViewModel.search(binding.searchEdit.text.toString(), page)
        }

        searchViewModel.listSetData.observe(this) {
            homeListAdapter.setList(it)
        }

        searchViewModel.listAddData.observe(this) {
            binding.smartRefreshLayout.finishLoadMore()
            homeListAdapter.setList(it)
        }

        searchViewModel.pageIsNextPage.observe(this) {
            if (it) {
                page++
                binding.smartRefreshLayout.setEnableLoadMore(true)
            } else {
                binding.smartRefreshLayout.setEnableLoadMore(false)
            }
        }

        searchViewModel.listSuccess.observe(this) {
            if (!it) {
                if (page == 1) {
                    binding.smartRefreshLayout.finishRefresh()
                } else {
                    binding.smartRefreshLayout.finishLoadMore()
                }
            }
        }
    }
}