package com.android.app.youbee

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log.d
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.app.youbee.R.layout.activity_main
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)


        val email = findViewById<EditText>(R.id.emailPlainTxt)
        val pswd = findViewById<EditText>(R.id.pswdPlainText)
        val logInButton: Button = findViewById(R.id.logInButton)
        logInButton.setOnClickListener {
            d("email", "${email.text}")
            d("password", "${pswd.text}")
//                startActivity(Intent (this,IntroductionActivity::class.java))
            startActivity(Intent(this, RecipeActivity::class.java))
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
}
