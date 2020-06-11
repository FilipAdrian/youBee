package com.android.app.youbee

import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.runner.AndroidJUnit4
import com.android.app.youbee.view.MainActivity
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.isOneOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LogInTest {

    @get:Rule
    var intentsRule: IntentsTestRule<MainActivity> = IntentsTestRule(MainActivity::class.java)

    @Test
    fun checkLogin() {
        onView(
            allOf(
                isDescendantOfA(withId(R.id.emailPlainTxt)),
                isAssignableFrom(EditText::class.java)
            )
        )
            .perform(
                typeText("youBee@gmai.com"), ViewActions.closeSoftKeyboard()
            )

        onView(
            allOf(
                isDescendantOfA(withId(R.id.pswdPlainText)),
                isAssignableFrom(EditText::class.java)
            )
        )
            .perform(typeText("password"), ViewActions.closeSoftKeyboard())

        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        onView(withId(R.id.logInButton)).perform(click())
    }
}