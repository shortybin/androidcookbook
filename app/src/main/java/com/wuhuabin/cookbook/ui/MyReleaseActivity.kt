package com.wuhuabin.cookbook.ui

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.dylanc.viewbinding.binding
import com.wuhuabin.common.GridSpacingItemDecoration
import com.wuhuabin.common.base.BaseActivity
import com.wuhuabin.common.dp2px
import com.wuhuabin.cookbook.adapter.HomeListAdapter
import com.wuhuabin.cookbook.databinding.ActivityMyReleaseBinding

class MyReleaseActivity : BaseActivity() {
    private val binding: ActivityMyReleaseBinding by binding()
    private val homeListAdapter = HomeListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.titleView.setTitleText("我发布的菜谱")
        binding.titleView.setLeftOnClickListener {
            finish()
        }
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.addItemDecoration(GridSpacingItemDecoration(2, 10f.dp2px(), false))
        binding.recyclerView.adapter = homeListAdapter

        val list = mutableListOf<String>()
        for (i in 1..100) {
            list.add(i.toString())
        }

        homeListAdapter.data.addAll(list)
    }
}