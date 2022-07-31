package com.wuhuabin.cookbook.views

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.dylanc.viewbinding.inflate
import com.wuhuabin.cookbook.databinding.MaterialItemLayoutBinding

/**
 * Created by shortybin
 * on 2022/7/30
 */
class MaterialItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet?
) : FrameLayout(context, attrs) {
    private val binding = inflate<MaterialItemLayoutBinding>()

    fun getIngredients(): String {
        return binding.ingredientsEdit.text.toString()
    }

    fun getDosage(): String {
        return binding.dosageEdit.text.toString()
    }

}