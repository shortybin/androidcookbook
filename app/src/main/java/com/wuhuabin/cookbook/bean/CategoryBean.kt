package com.wuhuabin.cookbook.bean

data class CategoryBean(
    val id: Int,
    val name: String,
    val url: String,
    var select: Boolean
) {
    constructor(
        id: Int,
        name: String,
        url: String,
    ) : this(
        id,
        name,
        url,
        false
    )
}