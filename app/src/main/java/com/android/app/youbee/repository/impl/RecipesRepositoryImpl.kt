package com.android.app.youbee.repository.impl

import com.android.app.youbee.Constant
import com.android.app.youbee.repository.EdamamEndpoint
import com.android.app.youbee.repository.ServiceBuilder
import com.android.app.youbee.entity.EdamamResponseModel
import com.android.app.youbee.repository.RecipesRepository
import retrofit2.Callback

class RecipesRepositoryImpl : RecipesRepository {
    override fun getRecipes(cb: Callback<EdamamResponseModel>) {
        val request =
            ServiceBuilder.buildService(
                EdamamEndpoint::class.java, Constant.EDAMAM_BASE_URL)
        val call = request.getRecipes(Constant.EDAMAM_API_KEY, Constant.EDAMAM_API_ID)
        call.enqueue(cb)
    }

}