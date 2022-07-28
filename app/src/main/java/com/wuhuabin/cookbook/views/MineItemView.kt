package com.wuhuabin.cookbook.views

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.dylanc.viewbinding.inflate
import com.wuhuabin.cookbook.databinding.MineItemLayoutBinding

class MineItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet
) : FrameLayout(context, attrs) {

    private val binding = inflate<MineItemLayoutBinding>()

    fun setItemText(text: String) {
        binding.itemText.text = text
    }
}