package com.android.app.youbee.repository

import com.android.app.youbee.entity.EdamamResponseModel
import retrofit2.Callback

interface RecipesRepository {
    fun getRecipes(cb: Callback<EdamamResponseModel>)
}