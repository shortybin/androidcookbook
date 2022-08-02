package com.wuhuabin.cookbook.ui

import android.net.Uri
import android.os.Bundle
import android.os.ParcelFileDescriptor
import androidx.activity.viewModels
import com.dylanc.viewbinding.binding
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.config.SelectModeConfig
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnResultCallbackListener
import com.luck.picture.lib.language.LanguageConfig
import com.wuhuabin.common.base.BaseActivity
import com.wuhuabin.cookbook.databinding.ActivityAddCookbookBinding
import com.wuhuabin.cookbook.utils.GlideEngine
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
                        image.addAll(result)
                    }

                    override fun onCancel() {

                    }
                })
        }

        binding.releaseCookbook.setOnClickListener {
            val pfd: ParcelFileDescriptor? =
                contentResolver.openFileDescriptor(Uri.parse(image[0].path), "r")
            if (pfd != null) {
                addCookbookViewModel.uploadImage(FileInputStream(pfd.fileDescriptor))
            }
        }
    }
}