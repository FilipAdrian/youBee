package com.android.app.youbee.repository

import com.android.app.youbee.entity.GorestUserResult
import com.android.app.youbee.entity.User
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.*

interface GorestEndpoint {
    @GET("public-api/users")
    fun getUserByEmail(
        @Query("first_name") email: String,
        @Header("Authorization") token: String
    ): Call<GorestUserResult>

    @POST("public-api/users")
    fun createUser(@Body user: User, @Header("Authorization") token: String): Call<JsonElement>
}