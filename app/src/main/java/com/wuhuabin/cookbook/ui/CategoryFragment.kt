package com.wuhuabin.cookbook.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.dylanc.viewbinding.binding
import com.wuhuabin.common.GridSpacingItemDecoration
import com.wuhuabin.common.dp2px
import com.wuhuabin.cookbook.R
import com.wuhuabin.cookbook.adapter.CategoryAdapter
import com.wuhuabin.cookbook.databinding.CategoryFragmentBinding
import com.wuhuabin.cookbook.databinding.HomeFragmentBinding

class CategoryFragment : Fragment(R.layout.category_fragment) {

    private val binding: CategoryFragmentBinding by binding()
    private val categoryAdapter = CategoryAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.recyclerView.addItemDecoration(GridSpacingItemDecoration(3, 10f.dp2px(), false))
        binding.recyclerView.adapter = categoryAdapter


        val list = mutableListOf<String>()
        for (i in 1..100) {
            list.add(i.toString())
        }

        categoryAdapter.data.addAll(list)
    }
}