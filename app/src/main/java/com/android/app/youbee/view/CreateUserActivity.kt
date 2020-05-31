package com.android.app.youbee.view

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.app.youbee.R
import com.android.app.youbee.entity.User
import com.android.app.youbee.presenter.SignInPresenter
import com.android.app.youbee.repository.impl.UserRepositoryImpl
import kotlinx.android.synthetic.main.create_user_activity.*

class CreateUserActivity : AppCompatActivity(), SignInView {
    private val TAG = "CreateUserActivity"
    private val genders: Array<String> = arrayOf("male", "female")
    private val presenter = SignInPresenter(this, UserRepositoryImpl())
    private lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.create_user_activity)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, genders)
        auto_complete_gender.setAdapter(adapter)

        signInButton.setOnClickListener {
            validateInput()
        }
    }

    override fun validateInput() {
        if (!InputField.validateEmail(create_email) or !InputField.validateNonEmpty(create_password)
            or !InputField.validateNonEmpty(create_username) or !InputField.validateNonEmpty(
                create_gender
            )
        ) {
            return
        }
        user = User(
            create_email.editText?.text.toString(),
            create_username.editText?.text.toString(),
            create_email.editText?.text.toString(),
            create_gender.editText?.text.toString()
        )
        presenter.createUser(user)
        InputField.clearTextInputLayout(
            arrayOf(
                create_username,
                create_email,
                create_password,
                create_gender
            )
        )
    }

    override fun signIn(isCreated: Boolean) {
        if (isCreated) {
            val intent = Intent(this@CreateUserActivity, IntroductionActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        } else {
            Toast.makeText(
                this@CreateUserActivity,
                "Something went wrong",
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

}