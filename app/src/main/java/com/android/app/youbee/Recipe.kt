package com.android.app.youbee

data class Recipe(
    val image: String,
    val label: String,
    val yield: Float,
    val url: String,
    val ingredientLines: List<String>
) {
    fun ingredientsToString(): String {
        var str = ""
        for (ing: String in ingredientLines) str += "$ing\n"
        return str
    }
}