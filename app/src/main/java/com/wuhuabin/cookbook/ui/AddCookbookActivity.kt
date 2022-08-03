package com.wuhuabin.cookbook.ui

import android.net.Uri
import android.os.Bundle
import android.os.ParcelFileDescriptor
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.dylanc.viewbinding.binding
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.config.SelectModeConfig
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnResultCallbackListener
import com.luck.picture.lib.language.LanguageConfig
import com.wuhuabin.common.base.BaseActivity
import com.wuhuabin.common.utils.GsonUtils
import com.wuhuabin.cookbook.bean.DishUploadBean
import com.wuhuabin.cookbook.bean.DishIngredientBean
import com.wuhuabin.cookbook.bean.DishStepBean
import com.wuhuabin.cookbook.databinding.ActivityAddCookbookBinding
import com.wuhuabin.cookbook.utils.GlideEngine
import com.wuhuabin.cookbook.utils.ToastUtils
import com.wuhuabin.cookbook.utils.UserInfoUtils
import com.wuhuabin.cookbook.viewmodel.AddCookbookViewModel
import com.wuhuabin.cookbook.views.MaterialItemView
import com.wuhuabin.cookbook.views.PracticeItemView
import java.io.FileInputStream


class AddCookbookActivity : BaseActivity() {
    private val binding: ActivityAddCookbookBinding by binding()
    private val addCookbookViewModel: AddCookbookViewModel by viewModels()
    private val image = mutableListOf<LocalMedia>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.titleView.setTitleText("创建菜谱")
        binding.titleView.setLeftOnClickListener {
            finish()
        }

        binding.addMaterial.setOnClickListener {
            binding.materialLayout.addView(MaterialItemView(this, null))
        }

        binding.addPractice.setOnClickListener {
            val practiceItemView = PracticeItemView(this, null)
            practiceItemView.setPracticeTitle("步骤${binding.practiceLayout.childCount + 1}")
            binding.practiceLayout.addView(practiceItemView)
        }

        binding.cookbookImage.setOnClickListener {
            PictureSelector.create(this).openGallery(SelectMimeType.ofImage())
                .setImageEngine(GlideEngine.createGlideEngine())
                .setSelectionMode(SelectModeConfig.SINGLE)
                .isDisplayCamera(false)
                .setLanguage(LanguageConfig.CHINESE)
                .forResult(object : OnResultCallbackListener<LocalMedia> {
                    override fun onResult(result: ArrayList<LocalMedia>) {
                        image.clear()
                        Glide.with(this@AddCookbookActivity).load(result[0].realPath)
                            .into(binding.cookbookImage)
                        image.addAll(result)
                    }

                    override fun onCancel() {

                    }
                })
        }

        binding.releaseCookbook.setOnClickListener {
            if (parameterRegular()) {
                uploadImage()
            }
        }

        addCookbookViewModel.toastMessage.observe(this) {
            ToastUtils.showCenter(it)
        }

        addCookbookViewModel.loadingState.observe(this) {
            if (it.isLoading) {
                loadingDialog.setText(it.loadingText)
                loadingDialog.show()
            } else {
                loadingDialog.dismiss()
            }
        }

        addCookbookViewModel.uploadImageSuccess.observe(this) {
            val dishUploadBean = DishUploadBean(
                binding.cookbookName.text.toString(),
                it,
                binding.cookbookInfo.text.toString(),
                UserInfoUtils.getUser()!!.userid
            )
            addCookbookViewModel.addDish(
                GsonUtils.toJson(dishUploadBean),
                GsonUtils.toJson(getIngredient()),
                GsonUtils.toJson(getStep())
            )
        }

        addCookbookViewModel.addDishSuccess.observe(this) {

        }
    }

    private fun getIngredient(): List<DishIngredientBean> {
        val list = mutableListOf<DishIngredientBean>()
        for (i in 0 until binding.materialLayout.childCount) {
            val materialItemView = binding.materialLayout.getChildAt(i) as MaterialItemView
            if (materialItemView.getIngredients().isNotEmpty() &&
                materialItemView.getDosage().isNotEmpty()
            ) {
                list.add(
                    DishIngredientBean(
                        materialItemView.getIngredients(),
                        materialItemView.getDosage()
                    )
                )
            }
        }
        return list
    }

    private fun getStep(): List<DishStepBean> {
        val list = mutableListOf<DishStepBean>()
        for (i in 0 until binding.practiceLayout.childCount) {
            val practiceItemView = binding.practiceLayout.getChildAt(i) as PracticeItemView
            if (practiceItemView.getPracticeContent().isNotEmpty()) {
                list.add(DishStepBean(practiceItemView.getPracticeContent()))
            }
        }
        return list
    }

    private fun parameterRegular(): Boolean {
        if (image.isNotEmpty()) {
            if (binding.cookbookName.text.isNotEmpty()) {
                if (binding.cookbookInfo.text.isNotEmpty()) {
                    return if (getIngredient().isNotEmpty()) {
                        if (getStep().isNotEmpty()) {
                            true
                        } else {
                            ToastUtils.showCenter("请输入菜谱步骤")
                            false
                        }
                    } else {
                        ToastUtils.showCenter("请输入菜谱用料")
                        false
                    }
                } else {
                    ToastUtils.showCenter("请输入这道美食背后的故事")
                    return false
                }
            } else {
                ToastUtils.showCenter("请输入菜谱名称")
                return false
            }
        } else {
            ToastUtils.showCenter("请上传菜谱图片")
            return false
        }
    }

    private fun uploadImage() {
        val pfd: ParcelFileDescriptor? =
            contentResolver.openFileDescriptor(Uri.parse(image[0].path), "r")
        if (pfd != null) {
            addCookbookViewModel.uploadImage(FileInputStream(pfd.fileDescriptor))
        }
    }
}