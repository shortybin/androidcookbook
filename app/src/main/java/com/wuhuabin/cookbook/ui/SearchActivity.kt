package com.wuhuabin.cookbook.ui

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.dylanc.viewbinding.binding
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
                searchViewModel.search(binding.searchEdit.text.toString(), 1)
                true
            } else {
                false
            }
        }

        searchViewModel.dishList.observe(this) {
            homeListAdapter.setList(it)
        }
    }
}