package com.android.app.youbee.repository.impl

import com.android.app.youbee.Constant
import com.android.app.youbee.entity.GorestUserResult
import com.android.app.youbee.entity.User
import com.android.app.youbee.repository.GorestEndpoint
import com.android.app.youbee.repository.ServiceBuilder
import com.android.app.youbee.repository.UserRepository
import com.google.gson.JsonElement
import retrofit2.Callback

class UserRepositoryImpl : UserRepository {
    override fun getUserByEmail(cb: Callback<GorestUserResult>, email: String) {
        val request =
            ServiceBuilder.buildService(GorestEndpoint::class.java, Constant.GOREST_BASE_URL)
        val call = request.getUserByEmail(email, "Bearer " + Constant.GOREST_TOKEN)
        call.enqueue(cb)
    }

    override fun createUser(cb: Callback<JsonElement>, user: User) {
        val request =
            ServiceBuilder.buildService(GorestEndpoint::class.java, Constant.GOREST_BASE_URL)
        val call = request.createUser(user, "Bearer " + Constant.GOREST_TOKEN)
        call.enqueue(cb)
    }

}
