package com.wuhuabin.common.view

import android.content.Context
import android.os.Bundle
import com.dylanc.viewbinding.binding
import com.wuhuabin.common.R
import com.wuhuabin.common.base.BaseDialog
import com.wuhuabin.common.databinding.LoadingDialogLayoutBinding

/**
 * Created by shortybin
 * on 2022/8/1
 */
class LoadingDialog(context: Context) : BaseDialog(context, R.style.loading_dialog) {
    private val binding: LoadingDialogLayoutBinding by binding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setCanceledOnTouchOutside(false)
    }

    fun setText(text: String) {
        binding.loadingText.text = text
    }

    override fun show() {
        super.show()
        binding.loadingLottie.playAnimation()
    }

    override fun dismiss() {
        super.dismiss()
        binding.loadingLottie.cancelAnimation()
    }
}