package com.android.app.youbee

import android.widget.EditText
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.runner.AndroidJUnit4
import com.android.app.youbee.view.CreateUserActivity
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SignInTest {

    @get:Rule
    var intentsRule: IntentsTestRule<CreateUserActivity> = IntentsTestRule(CreateUserActivity::class.java)

    @Test
    fun registerNewUser(){
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.isDescendantOfA(ViewMatchers.withId(R.id.create_email)),
                ViewMatchers.isAssignableFrom(EditText::class.java)
            )
        )
            .perform(
                ViewActions.typeText("youBee@gmai.com"), ViewActions.closeSoftKeyboard()
            )

        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.isDescendantOfA(ViewMatchers.withId(R.id.create_password)),
                ViewMatchers.isAssignableFrom(EditText::class.java)
            )
        )
            .perform(ViewActions.typeText("password"), ViewActions.closeSoftKeyboard())

        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.isDescendantOfA(ViewMatchers.withId(R.id.create_gender)),
                ViewMatchers.isAssignableFrom(EditText::class.java)
            )
        )
            .perform(ViewActions.typeText("male"), ViewActions.closeSoftKeyboard())

        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.isDescendantOfA(ViewMatchers.withId(R.id.create_username)),
                ViewMatchers.isAssignableFrom(EditText::class.java)
            )
        )
            .perform(ViewActions.typeText("username"), ViewActions.closeSoftKeyboard())

        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        Espresso.onView(ViewMatchers.withId(R.id.signInButton)).perform(ViewActions.click())
    }
}