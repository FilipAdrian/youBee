package com.android.app.youbee.repository

import com.android.app.youbee.entity.EdamamResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EdamamEndpoint {

    @GET("search?q=honey")
    fun getRecipes(
        @Query("app_key") key: String,
        @Query("app_id") id: String
    ): Call<EdamamResponseModel>
}