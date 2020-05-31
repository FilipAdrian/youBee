package com.android.app.youbee.view

import com.android.app.youbee.entity.Recipe

interface RecipeActivityView {
    fun displayContent(recipes: List<Recipe>)

}
