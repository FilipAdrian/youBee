package com.android.app.youbee.repository

import com.android.app.youbee.entity.GorestUserResult
import com.android.app.youbee.entity.User
import com.google.gson.JsonElement
import retrofit2.Callback

interface UserRepository {
    fun getUserByEmail(cb: Callback<GorestUserResult>, email: String)
    fun createUser(cb: Callback<JsonElement>, user: User)
}