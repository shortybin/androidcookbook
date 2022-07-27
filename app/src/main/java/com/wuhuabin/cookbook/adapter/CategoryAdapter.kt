package com.wuhuabin.cookbook.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.dylanc.viewbinding.brvah.getBinding
import com.wuhuabin.cookbook.R
import com.wuhuabin.cookbook.databinding.CategoryItemBinding

class CategoryAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.category_item) {

    override fun convert(holder: BaseViewHolder, item: String) {
        val binding = holder.getBinding(CategoryItemBinding::bind)
        binding.categoryText.text = item
    }
}