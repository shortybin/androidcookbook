package com.wuhuabin.cookbook.bean

data class CategoryBean(
    val id: Int,
    val name: String,
    val image: String,
    var select: Boolean
) {
    constructor(
        id: Int,
        name: String,
        image: String,
    ) : this(
        id,
        name,
        image,
        false
    )
}