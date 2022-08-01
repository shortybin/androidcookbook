package com.wuhuabin.cookbook.views

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.dylanc.viewbinding.inflate
import com.wuhuabin.cookbook.databinding.MaterialShowLayoutBinding

class MaterialShowView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet?
) : FrameLayout(context, attrs) {
    private val binding = inflate<MaterialShowLayoutBinding>()

    fun setIngredients(ingredients: String) {
        binding.ingredientsEdit.text = ingredients
    }

    fun setDosage(dosage: String) {
        binding.dosageEdit.text = dosage
    }
}