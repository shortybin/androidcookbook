package com.wuhuabin.cookbook.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.dylanc.viewbinding.brvah.getBinding
import com.wuhuabin.cookbook.R
import com.wuhuabin.cookbook.databinding.HomeListItemBinding

class HomeListAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.home_list_item) {

    override fun convert(holder: BaseViewHolder, item: String) {
        val binding = holder.getBinding(HomeListItemBinding::bind)
        binding.cookbookName.text = item
    }
}