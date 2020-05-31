package com.android.app.youbee.presenter

import android.util.Log.d
import com.android.app.youbee.entity.User
import com.android.app.youbee.repository.impl.UserRepositoryImpl
import com.android.app.youbee.view.SignInView
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInPresenter(private var view: SignInView?, private val repository: UserRepositoryImpl) :
    Callback<JsonElement> {
    private val TAG = "SignInPresenter"
    fun createUser(user: User) {
        repository.createUser(this, user)
    }

    override fun onFailure(call: Call<JsonElement>, t: Throwable) {
        d(TAG, "${t.message}")
    }

    override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
        if (!response.isSuccessful) {

            d(TAG, response.code().toString())
            return
        }
        val result = response.body()
        if (result != null) {
            val isSuccess =
                result.asJsonObject.getAsJsonObject("_meta").get("success").asBoolean
            if (!isSuccess) {
                view?.signIn(false)
                return
            }
            view?.signIn(true)
        }

    }

    fun onDestroy() {
        view = null
    }
}