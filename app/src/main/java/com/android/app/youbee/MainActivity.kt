package com.android.app.youbee

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.android.app.youbee.R.layout.activity_main

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)


        val email = findViewById<EditText>(R.id.emailPlainTxt)
        val pswd = findViewById<EditText>(R.id.pswdPlainText)
        val logInButton: Button = findViewById(R.id.logInButton)
            logInButton.setOnClickListener{
                d("email", "${email.text}")
                d("password", "${pswd.text}")
//                startActivity(Intent (this,IntroductionActivity::class.java))
                startActivity(Intent(this,RecipeActivity::class.java))
            }
    }
}
