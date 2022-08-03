package com.wuhuabin.cookbook.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.dylanc.viewbinding.binding
import com.wuhuabin.common.base.BaseActivity
import com.wuhuabin.cookbook.Constant
import com.wuhuabin.cookbook.databinding.ActivityDetailsBinding
import com.wuhuabin.cookbook.viewmodel.DetailsViewModel

class DetailsActivity : BaseActivity() {
    private val binding: ActivityDetailsBinding by binding()
    private val detailsViewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        detailsViewModel.dishDetails.observe(this) {
            binding.cookbookName.text = it.name
            Glide.with(this).load(it.image).into(binding.cookbookImage)
        }

        detailsViewModel.getDetails(intent.getIntExtra(Constant.DISH_ID, 0))
    }
}