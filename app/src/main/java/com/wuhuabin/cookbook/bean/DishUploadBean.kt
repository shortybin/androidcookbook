package com.wuhuabin.cookbook.bean

data class DishUploadBean(
    val name: String,
    val image: String,
    val detail: String,
    val create_id: Int,
    val categoryIds: String
)