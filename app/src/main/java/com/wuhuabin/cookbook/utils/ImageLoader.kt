package com.wuhuabin.cookbook.utils

import com.bumptech.glide.request.RequestOptions
import com.wuhuabin.cookbook.R

/**
 * Created by shortybin
 * on 2022/8/6
 */
class ImageLoader {
    companion object {
        fun defaultOption(): RequestOptions {
            return RequestOptions().placeholder(R.drawable.ic_default).error(R.drawable.ic_default)
        }
    }
}