package com.android.app.youbee

data class Recipe(
    val image: String,
    val label: String,
    val yield: Float,
    val url: String,
    val ingredientLines: List<String>
)