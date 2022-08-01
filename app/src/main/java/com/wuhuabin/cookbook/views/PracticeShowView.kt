package com.wuhuabin.cookbook.views

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.dylanc.viewbinding.inflate
import com.wuhuabin.cookbook.databinding.PracticeShowLayoutBinding

class PracticeShowView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet?
) : FrameLayout(context, attrs) {
    private val binding = inflate<PracticeShowLayoutBinding>()

    fun setPracticeTitle(title: String) {
        binding.practiceTitle.text = title
    }

    fun setPracticeContent(content: String) {
        binding.practiceContent.text = content
    }
}