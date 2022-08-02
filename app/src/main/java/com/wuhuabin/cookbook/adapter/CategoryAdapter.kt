package com.wuhuabin.cookbook.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.dylanc.viewbinding.brvah.getBinding
import com.wuhuabin.cookbook.R
import com.wuhuabin.cookbook.bean.CategoryBean
import com.wuhuabin.cookbook.databinding.CategoryItemBinding

class CategoryAdapter : BaseQuickAdapter<CategoryBean, BaseViewHolder>(R.layout.category_item) {

    override fun convert(holder: BaseViewHolder, categoryBean: CategoryBean) {
        val binding = holder.getBinding(CategoryItemBinding::bind)
        binding.categoryText.text = categoryBean.name
    }
}