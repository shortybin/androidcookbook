package com.wuhuabin.cookbook.views

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import com.dylanc.viewbinding.inflate
import com.wuhuabin.cookbook.databinding.TitleLayoutBinding

class TitleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet
) : FrameLayout(context, attrs) {

    private val binding = inflate<TitleLayoutBinding>()

    fun setTitleText(text: String) {
        binding.titleText.text = text
    }

    fun setRightText(text: String) {
        binding.rightText.text = text
    }

    fun setRightTextColor(color: String) {
        binding.rightText.setTextColor(Color.parseColor(color))
    }

    fun setLeftOnClickListener(onClickListener: OnClickListener) {
        binding.leftImage.setOnClickListener(onClickListener)
    }

    fun setRightOnClickListener(onClickListener: OnClickListener) {
        binding.rightText.setOnClickListener(onClickListener)
    }
}