package com.android.app.youbee.unitTests

import com.android.app.youbee.Constant
import com.android.app.youbee.repository.EdamamEndpoint
import com.android.app.youbee.repository.ServiceBuilder
import org.hamcrest.core.StringContains
import org.junit.Assert
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertThat
import org.junit.Test

private const val WRONG_URL = "Fake URL"

class ServiceBuilderTest {

    @Test
    fun checkNotNullServiceBuilderForEdamamApi() {
        val request =
            ServiceBuilder.buildService(EdamamEndpoint::class.java,
                Constant.EDAMAM_BASE_URL
            )

        assertNotNull("Service Builder is Null",request)

        Assert.assertEquals(
            "URL is not correct for the GET Joke endpoint",
            "https://api.edamam.com/search?q=honey&app_key=75da0e15b97bc087c67e6abfb3cf275f&app_id=74ac0216",
            request.getRecipes(
                Constant.EDAMAM_API_KEY,
                Constant.EDAMAM_API_ID
            ).request().url.toString()
        )
    }

    @Test
    fun handleErrorWhenInvalidHostIsPassed() {
        try {
            ServiceBuilder.buildService(EdamamEndpoint::class.java,
                WRONG_URL
            )
        } catch (e: IllegalArgumentException) {
            assertThat(e.message, StringContains("Expected URL scheme"))
        }
    }
}