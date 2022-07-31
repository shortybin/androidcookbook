package com.wuhuabin.cookbook.views

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.dylanc.viewbinding.inflate
import com.wuhuabin.cookbook.databinding.PracticeItemLayoutBinding

/**
 * Created by shortybin
 * on 2022/7/30
 */
class PracticeItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet?
) : FrameLayout(context, attrs) {
    private val binding = inflate<PracticeItemLayoutBinding>()

    fun setPracticeTitle(title: String) {
        binding.practiceTitle.text = title
    }

    fun getPracticeContent(): String {
        return binding.practiceContent.text.toString()
    }
}