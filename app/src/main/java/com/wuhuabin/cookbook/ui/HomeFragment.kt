package com.wuhuabin.cookbook.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.dylanc.viewbinding.binding
import com.wuhuabin.common.GridSpacingItemDecoration
import com.wuhuabin.cookbook.R
import com.wuhuabin.cookbook.adapter.HomeListAdapter
import com.wuhuabin.cookbook.databinding.HomeFragmentBinding
import com.wuhuabin.common.dp2px
import com.wuhuabin.cookbook.viewmodel.HomeViewModel

class HomeFragment : Fragment(R.layout.home_fragment) {
    private val binding: HomeFragmentBinding by binding()
    private val homeViewModel: HomeViewModel by viewModels()
    private val homeListAdapter = HomeListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.adapter = homeListAdapter
        binding.recyclerView.addItemDecoration(GridSpacingItemDecoration(2, 10f.dp2px(), false))

        val list = mutableListOf<String>()
        for (i in 1..100) {
            list.add(i.toString())
        }

        homeListAdapter.data.addAll(list)

        binding.searchLayout.setOnClickListener {
            startActivity(Intent(context, SearchActivity::class.java))
        }

        binding.homeAddMenu.setOnClickListener {
            startActivity(Intent(context, AddCookbookActivity::class.java))
        }
    }
}