package com.wuhuabin.cookbook.adapter

import android.content.Intent
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.dylanc.viewbinding.brvah.getBinding
import com.wuhuabin.cookbook.Constant
import com.wuhuabin.cookbook.R
import com.wuhuabin.cookbook.bean.CategoryBean
import com.wuhuabin.cookbook.databinding.CategoryItemBinding
import com.wuhuabin.cookbook.ui.CategoryDishListActivity
import com.wuhuabin.cookbook.utils.ImageLoader
import com.wuhuabin.cookbook.utils.addHostLoad

class CategoryAdapter : BaseQuickAdapter<CategoryBean, BaseViewHolder>(R.layout.category_item) {

    override fun convert(holder: BaseViewHolder, categoryBean: CategoryBean) {
        val binding = holder.getBinding(CategoryItemBinding::bind)
        binding.categoryText.text = categoryBean.name
        Glide.with(holder.itemView).addHostLoad(categoryBean.image)
            .apply(ImageLoader.defaultOption())
            .into(binding.categoryImage)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, CategoryDishListActivity::class.java)
            intent.putExtra(Constant.CATEGORY_ID, categoryBean.id)
            intent.putExtra(Constant.CATEGORY_NAME, categoryBean.name)
            context.startActivity(intent)
        }
    }
}