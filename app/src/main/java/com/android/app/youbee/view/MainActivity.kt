package com.android.app.youbee.view

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.app.youbee.R.layout.activity_main
import com.android.app.youbee.entity.User
import com.android.app.youbee.presenter.LogInPresenter
import com.android.app.youbee.repository.impl.UserRepositoryImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LogInView {
    private val TAG = "MainActivity"
    private val presenter = LogInPresenter(this, UserRepositoryImpl())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        logInButton.setOnClickListener {
            validateInput()
        }
        setClickableSignIn()
    }

    private fun setClickableSignIn() {
        val text = "Don't have account ? Sign In"
        val spannableString = SpannableString(text)
        spannableString.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(this@MainActivity, "Sign In", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@MainActivity, CreateUserActivity::class.java))
            }
        }, 0, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        signIn.text = spannableString
        signIn.movementMethod = LinkMovementMethod.getInstance()

    }

    override fun validateInput() {
        if (!InputField.validateEmail(emailPlainTxt) or !InputField.validateNonEmpty(pswdPlainText)) {
            return
        }
        val email = emailPlainTxt.editText!!.text.toString().trim()
        InputField.clearTextInputLayout(arrayOf(emailPlainTxt, pswdPlainText))
        presenter.validateUser(email)

    }

    override fun checkUser(users: List<User>) {
        if (users.isNotEmpty()) {
            val intent = Intent(this@MainActivity, IntroductionActivity::class.java)
            intent.putExtra("user", users[0])
            startActivity(intent)
        } else {
            Toast.makeText(
                this@MainActivity,
                "Email or Password is not correct",
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
