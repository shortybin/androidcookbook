package com.wuhuabin.cookbook.utils

import android.graphics.drawable.Drawable
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.wuhuabin.cookbook.api.CookBookAPi


fun RequestManager.addHostLoad(string: String?): RequestBuilder<Drawable> {
    return asDrawable().load(CookBookAPi.APIURL + string)
}
