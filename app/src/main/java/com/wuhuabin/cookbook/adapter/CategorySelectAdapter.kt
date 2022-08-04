package com.wuhuabin.cookbook.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.dylanc.viewbinding.brvah.getBinding
import com.wuhuabin.cookbook.R
import com.wuhuabin.cookbook.bean.CategoryBean
import com.wuhuabin.cookbook.databinding.CategorySelectItemBinding

class CategorySelectAdapter :
    BaseQuickAdapter<CategoryBean, BaseViewHolder>(R.layout.category_select_item) {
    override fun convert(holder: BaseViewHolder, item: CategoryBean) {
        val binding = holder.getBinding(CategorySelectItemBinding::bind)

        binding.categoryName.text = item.name
        if (item.select) {
            binding.categoryItemLayout.setBackgroundResource(R.drawable.category_select_bg)
            binding.categoryName.setTextColor(context.resources.getColor(R.color.red_500))
        } else {
            binding.categoryItemLayout.setBackgroundResource(R.drawable.category_normal_bg)
            binding.categoryName.setTextColor(context.resources.getColor(R.color.black))
        }

        binding.categoryItemLayout.setOnClickListener {
            item.select = !item.select
            notifyDataSetChanged()
        }
    }

    fun isSelect(): Boolean {
        for (item in data) {
            if (item.select) {
                return true
            }
        }
        return false
    }

    fun getSelectString(): String {
        val stringBuilder = StringBuilder()
        for (item in data) {
            if (stringBuilder.isEmpty()) {
                stringBuilder.append(item.id)
            } else {
                stringBuilder.append(",${item.id}")
            }
        }
        return stringBuilder.toString()
    }
}