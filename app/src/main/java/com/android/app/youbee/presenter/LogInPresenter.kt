package com.android.app.youbee.presenter

import android.provider.ContactsContract
import android.util.Log
import com.android.app.youbee.entity.GorestUserResult
import com.android.app.youbee.repository.UserRepository
import com.android.app.youbee.repository.impl.UserRepositoryImpl
import com.android.app.youbee.view.LogInView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogInPresenter(private var view: LogInView?, private val repository: UserRepository) : Callback<GorestUserResult> {
    private val TAG = "LogInPresenter"

    fun validateUser(email: String){
        repository.getUserByEmail(this,email)
    }
    override fun onFailure(call: Call<GorestUserResult>, t: Throwable) {
        Log.d(TAG, "${t.message}")
        view?.displayMessage(t.message.toString())
    }

    override fun onResponse(call: Call<GorestUserResult>, response: Response<GorestUserResult>) {
        if (!response.isSuccessful) {
            Log.d(TAG, response.code().toString())
            view?.displayMessage(response.message())
            return
        }
        if (response.body() != null) {
            Log.d(TAG, response.body().toString())
            view?.checkUser(response.body()!!.result)
        }
    }

    fun onDestroy() {
        view = null
    }
}