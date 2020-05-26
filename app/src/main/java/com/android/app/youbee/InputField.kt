package com.android.app.youbee

import android.util.Patterns
import com.google.android.material.textfield.TextInputLayout

class InputField {
    companion object {
        fun validateEmail(emailPlainTxt: TextInputLayout): Boolean {

            if (emailPlainTxt.editText != null) {
                val email = emailPlainTxt.editText!!.text.trim()
                if (email.isEmpty()) {
                    emailPlainTxt.error = "Field can't be empty"
                    return false
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailPlainTxt.error = "Email address is not valid"
                    return false
                } else {
                    emailPlainTxt.error = null
                    return true
                }

            }
            return false
        }

        fun validateNonEmpty(plainText: TextInputLayout): Boolean {
            if (plainText.editText != null) {
                val password = plainText.editText!!.text.trim()
                if (password.isEmpty()) {
                    plainText.error = "Field can't be empty"
                    return false
                } else {
                    plainText.error = null
                    return true
                }
            }
            return false
        }

        fun clearTextInputLayout(fields: Array<TextInputLayout>) {
            fields.forEach {
                it.editText?.text?.clear()
                it.error = null
            }
        }

    }
}