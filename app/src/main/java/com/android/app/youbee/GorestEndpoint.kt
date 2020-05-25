package com.android.app.youbee

import retrofit2.Call
import retrofit2.http.*

interface GorestEndpoint {
    @GET("public-api/users")
    fun getUserByEmail(
        @Query("first_name") email: String,
        @Header("Authorization") token: String
    ): Call<GorestResult>

    @POST("public-api/users")
    fun createUser(@Body user: User, @Header("Authorization") token: String): Call<User>
}