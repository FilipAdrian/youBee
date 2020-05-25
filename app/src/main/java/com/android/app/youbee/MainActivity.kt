package com.android.app.youbee

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log.d
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.app.youbee.R.layout.activity_main
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
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
            }
        }, 0, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        signIn.text = spannableString
        signIn.movementMethod = LinkMovementMethod.getInstance()

    }

    private fun validateInput() {
        if (!validateEmail() or !validatePassword()) {
            return
        }
        emailPlainTxt.error = null
        pswdPlainText.error = null
        val email = emailPlainTxt.editText!!.text.toString().trim()
        getUserByEmail(email)

    }

    private fun validateEmail(): Boolean {

        if (emailPlainTxt.editText != null) {
            val email = emailPlainTxt.editText!!.text.trim()
            if (email.isEmpty()) {
                emailPlainTxt.error = "Field can't be empty"
                return false
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailPlainTxt.error = "Email address is not valid"
                return false
            } else {
                emailPlainTxt.error = null;
                return true
            }

        }
        return false
    }

    private fun validatePassword(): Boolean {
        if (pswdPlainText.editText != null) {
            val password = pswdPlainText.editText!!.text.trim()
            if (password.isEmpty()) {
                pswdPlainText.error = "Field can't be empty"
                return false
            } else {
                pswdPlainText.error = null
                return true
            }
        }
        return false
    }

    private fun getUserByEmail(email: String) {
        val request =
            ServiceBuilder.buildService(GorestEndpoint::class.java, Constant.GOREST_BASE_URL)
        val cal = request.getUserByEmail(email, "Bearer " + Constant.GOREST_TOKEN)
        cal.enqueue(object : Callback<GorestResult> {
            override fun onFailure(call: Call<GorestResult>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT)
                    .show()
                d(TAG, "${t.message}")
            }

            override fun onResponse(
                call: Call<GorestResult>,
                response: Response<GorestResult>
            ) {
                if (!response.isSuccessful) {
                    d(TAG, response.code().toString())
                    return
                }
                if (response.body() != null) {
                    d(TAG, response.body().toString())
                    if (response.body()!!.result.isNotEmpty()) {
                        val intent = Intent(this@MainActivity, IntroductionActivity::class.java)
                        intent.putExtra("user", response.body()!!.result[0])
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
            }

        })
    }
}
