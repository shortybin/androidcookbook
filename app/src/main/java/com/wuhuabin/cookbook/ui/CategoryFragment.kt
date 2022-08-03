package com.wuhuabin.cookbook.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.dylanc.viewbinding.binding
import com.wuhuabin.common.GridSpacingItemDecoration
import com.wuhuabin.common.dp2px
import com.wuhuabin.cookbook.R
import com.wuhuabin.cookbook.adapter.CategoryAdapter
import com.wuhuabin.cookbook.databinding.CategoryFragmentBinding
import com.wuhuabin.cookbook.databinding.HomeFragmentBinding
import com.wuhuabin.cookbook.viewmodel.CategoryViewModel

class CategoryFragment : Fragment(R.layout.category_fragment) {

    private val binding: CategoryFragmentBinding by binding()
    private val categoryViewModel: CategoryViewModel by viewModels()
    private val categoryAdapter = CategoryAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.recyclerView.addItemDecoration(GridSpacingItemDecoration(3, 10f.dp2px(), false))
        binding.recyclerView.adapter = categoryAdapter

        binding.searchLayout.setOnClickListener {
            startActivity(Intent(context, SearchActivity::class.java))
        }

        categoryViewModel.categoryList.observe(viewLifecycleOwner) {
            categoryAdapter.setList(it)
        }

        categoryViewModel.getCategory()
    }
}