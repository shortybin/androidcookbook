package com.wuhuabin.cookbook.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dylanc.viewbinding.binding
import com.wuhuabin.cookbook.R
import com.wuhuabin.cookbook.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    private val binding: ActivityAboutBinding by binding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.titleView.setTitleText("关于我们")
        binding.titleView.setLeftOnClickListener {
            finish()
        }
    }
}