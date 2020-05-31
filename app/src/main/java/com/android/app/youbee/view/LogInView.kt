package com.android.app.youbee.view

import com.android.app.youbee.entity.User

interface LogInView  {
    fun validateInput()
    fun checkUser(users: List<User>)
}