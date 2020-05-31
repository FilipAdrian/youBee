package com.android.app.youbee.entity

data class EdamamResponseModel(val hits: List<Hits>) {
    data class Hits(val recipe: Recipe)

    fun getAllRecipes(): List<Recipe> {
        val recipes = ArrayList<Recipe>()
        for (hit: Hits in hits) {
            recipes += hit.recipe
        }
        return recipes
    }


}
