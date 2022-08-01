package com.wuhuabin.cookbook.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.dylanc.viewbinding.binding
import com.wuhuabin.common.base.BaseActivity
import com.wuhuabin.cookbook.databinding.ActivityDetailsBinding
import com.wuhuabin.cookbook.viewmodel.DetailsViewModel

class DetailsActivity : BaseActivity() {
    private val binding: ActivityDetailsBinding by binding()
    private val detailsViewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}