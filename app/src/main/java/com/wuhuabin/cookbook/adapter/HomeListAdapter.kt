package com.wuhuabin.cookbook.adapter

import android.content.Intent
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.dylanc.viewbinding.brvah.getBinding
import com.wuhuabin.cookbook.Constant
import com.wuhuabin.cookbook.R
import com.wuhuabin.cookbook.bean.DishDetailsBean
import com.wuhuabin.cookbook.databinding.HomeListItemBinding
import com.wuhuabin.cookbook.ui.DetailsActivity
import com.wuhuabin.cookbook.utils.ImageLoader

class HomeListAdapter : BaseQuickAdapter<DishDetailsBean, BaseViewHolder>(R.layout.home_list_item) {

    override fun convert(holder: BaseViewHolder, detailsBean: DishDetailsBean) {
        val binding = holder.getBinding(HomeListItemBinding::bind)
        binding.cookbookName.text = detailsBean.name
        Glide.with(holder.itemView).load(detailsBean.image).apply(ImageLoader.defaultOption())
            .into(binding.cookbookImage)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(Constant.DISH_ID, detailsBean.id)
            context.startActivity(intent)
        }
    }
}