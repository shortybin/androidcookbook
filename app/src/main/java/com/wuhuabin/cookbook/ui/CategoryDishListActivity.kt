package com.wuhuabin.cookbook.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.dylanc.viewbinding.binding
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

        categoryDishListViewModel.dishList.observe(this) {
            homeListAdapter.setList(it)
        }

        categoryDishListViewModel.getCategoryDishList(
            intent.getIntExtra(Constant.CATEGORY_ID, 0),
            1
        )
    }
}