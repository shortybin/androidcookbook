package com.wuhuabin.common.base

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.WindowManager

/**
 * Created by shortybin
 * on 2022/8/1
 */
open class BaseDialog @JvmOverloads constructor(
    context: Context, theme: Int,
    private val isFullScreen: Boolean = false,
    private val position: Position = Position.DEFAULT
) : Dialog(context, theme) {

    override fun setContentView(view: View) {
        super.setContentView(view)
        if (isFullScreen) {
            val window = this.window
            val params = window!!.attributes
            params.flags = params.flags or WindowManager.LayoutParams.FLAG_FULLSCREEN
            params.width = WindowManager.LayoutParams.MATCH_PARENT
            window.attributes = params
        }
        if (position == Position.BOTTOM) {
            val window = this.window
            window!!.setGravity(Gravity.CENTER or Gravity.BOTTOM)
        } else if (position == Position.TOP) {
            val window = this.window
            window!!.setGravity(Gravity.CENTER or Gravity.TOP)
        }
    }


    enum class Position {
        //内容显示的位置，默认gravity是中间
        DEFAULT, BOTTOM, TOP
    }
}