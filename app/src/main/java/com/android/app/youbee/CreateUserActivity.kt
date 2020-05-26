package com.android.app.youbee

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.create_user_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateUserActivity : AppCompatActivity() {
    private val TAG = "CreateUserActivity"
    private val genders: Array<String> = arrayOf("male", "female")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.create_user_activity)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, genders)
        auto_complete_gender.setAdapter(adapter)

        signInButton.setOnClickListener {
            validateInput()
        }
    }

    private fun validateInput() {
        if (!InputField.validateEmail(create_email) or !InputField.validateNonEmpty(create_password)
            or !InputField.validateNonEmpty(create_username) or !InputField.validateNonEmpty(
                create_gender
            )
        ) {
            return
        }
        val user = User(
            create_email.editText?.text.toString(),
            create_username.editText?.text.toString(),
            create_email.editText?.text.toString(),
            create_gender.editText?.text.toString()
        )
        createUser(user)
        InputField.clearTextInputLayout(
            arrayOf(
                create_username,
                create_email,
                create_password,
                create_gender
            )
        )
    }

    private fun createUser(user: User) {
        val request =
            ServiceBuilder.buildService(GorestEndpoint::class.java, Constant.GOREST_BASE_URL)
        val call = request.createUser(user, "Bearer " + Constant.GOREST_TOKEN)
        call.enqueue(object : Callback<JsonElement> {

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Toast.makeText(this@CreateUserActivity, "${t.message}", Toast.LENGTH_SHORT)
                    .show()
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
                        Toast.makeText(
                            this@CreateUserActivity,
                            "Something went wrong",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        return
                    }
                    val intent = Intent(this@CreateUserActivity, IntroductionActivity::class.java)
                    intent.putExtra("user", user)
                    startActivity(intent)
                }


            }


        })
    }

}