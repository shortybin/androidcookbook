package com.wuhuabin.cookbook.utils

import android.text.TextUtils
import android.view.Gravity
import android.widget.Toast
import com.wuhuabin.cookbook.MainApplication

class ToastUtils {
    companion object {
        fun show(message: String, duration: Int, gravity: Int) {
            if (TextUtils.isEmpty(message)) {
                return
            }
            val toast = Toast.makeText(MainApplication.getInstance(), null, duration)
            toast.setText(message)
            toast.setGravity(gravity, 0, 0)
            toast.show()
        }

        fun showCenter(message: String) {
            show(message, Toast.LENGTH_SHORT, Gravity.CENTER)
        }
    }
}