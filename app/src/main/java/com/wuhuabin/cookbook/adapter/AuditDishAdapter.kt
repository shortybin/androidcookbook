package com.wuhuabin.cookbook.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.dylanc.viewbinding.brvah.getBinding
import com.wuhuabin.cookbook.R
import com.wuhuabin.cookbook.bean.DishDetailsBean
import com.wuhuabin.cookbook.databinding.AuditDishItemBinding
import com.wuhuabin.cookbook.databinding.CategoryItemBinding
import com.wuhuabin.cookbook.viewmodel.AuditDishViewModel

class AuditDishAdapter(private val auditDishViewModel: AuditDishViewModel) :
    BaseQuickAdapter<DishDetailsBean, BaseViewHolder>(R.layout.audit_dish_item) {

    fun removeDishId(dishId: Int) {
        for (item in data) {
            if (item.id == dishId) {
                remove(item)
                notifyDataSetChanged()
                return
            }
        }
    }

    override fun convert(holder: BaseViewHolder, item: DishDetailsBean) {
        val binding = holder.getBinding(AuditDishItemBinding::bind)

        Glide.with(context).load(item.image).into(binding.dishImage)
        binding.dishTitle.text = item.name
        binding.dishInfo.text = item.detail
        binding.auditButton.setOnClickListener {
            auditDishViewModel.setDishStatus(item.id)
        }
    }
}