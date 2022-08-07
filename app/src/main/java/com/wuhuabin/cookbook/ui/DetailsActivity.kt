package com.wuhuabin.cookbook.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.dylanc.viewbinding.binding
import com.wuhuabin.common.base.BaseActivity
import com.wuhuabin.cookbook.Constant
import com.wuhuabin.cookbook.bean.DishStatusChangeEvent
import com.wuhuabin.cookbook.databinding.ActivityDetailsBinding
import com.wuhuabin.cookbook.utils.ImageLoader
import com.wuhuabin.cookbook.utils.ToastUtils
import com.wuhuabin.cookbook.utils.addHostLoad
import com.wuhuabin.cookbook.viewmodel.DetailsViewModel
import com.wuhuabin.cookbook.views.MaterialShowView
import com.wuhuabin.cookbook.views.PracticeShowView
import org.greenrobot.eventbus.EventBus

class DetailsActivity : BaseActivity() {
    private val binding: ActivityDetailsBinding by binding()
    private val detailsViewModel: DetailsViewModel by viewModels()

    private var dishId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        dishId = intent.getIntExtra(Constant.DISH_ID, 0)
        binding.titleView.setLeftOnClickListener {
            finish()
        }
        detailsViewModel.dishDetails.observe(this) {
            binding.titleView.setTitleText(it.name)
            Glide.with(this).addHostLoad(it.image).apply(ImageLoader.defaultOption())
                .into(binding.cookbookImage)
            binding.cookbookName.text = it.name
            binding.cookbookInfo.text = it.detail
            for (ingredient in it.dishIngredientList) {
                val materialShowView = MaterialShowView(this, null)
                materialShowView.setIngredients(ingredient.ingredient_name)
                materialShowView.setDosage(ingredient.ingredient_dosage)
                binding.materialLayout.addView(materialShowView)
            }

            for ((index, step) in it.dishStepList.withIndex()) {
                val practiceShowView = PracticeShowView(this, null)
                practiceShowView.setPracticeTitle("步骤${index + 1}")
                practiceShowView.setPracticeContent(step.content)
                binding.practiceLayout.addView(practiceShowView)
            }

            if (it.examine_status == 1) {
                binding.auditButton.visibility = View.VISIBLE
                binding.auditButton.setOnClickListener {
                    detailsViewModel.setDishStatus(dishId)
                }
            } else {
                binding.auditButton.visibility = View.GONE
            }
        }

        detailsViewModel.toastMessage.observe(this) {
            ToastUtils.showCenter(it)
        }

        detailsViewModel.loadingState.observe(this) {
            if (it.isLoading) {
                loadingDialog.setText(it.loadingText)
                loadingDialog.show()
            } else {
                loadingDialog.dismiss()
            }
        }

        detailsViewModel.dishStatusChange.observe(this) {
            EventBus.getDefault().post(DishStatusChangeEvent(it))
            binding.auditButton.visibility = View.GONE
        }

        detailsViewModel.getDetails(dishId)
    }
}