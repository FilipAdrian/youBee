package com.android.app.youbee.view

import com.android.app.youbee.entity.User

interface SignInView {
    fun validateInput()
    fun signIn(isCreated: Boolean)
}