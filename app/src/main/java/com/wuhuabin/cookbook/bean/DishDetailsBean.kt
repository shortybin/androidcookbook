package com.wuhuabin.cookbook.bean

data class DishDetailsBean(
    val id: Int,
    val name: String,
    val image: String,
    val detail: String,
    val examine_status: Int,
    val create_id: Int,
    val dishIngredientList: List<DishIngredientBean>,
    val dishStepList: List<DishStepBean>
)