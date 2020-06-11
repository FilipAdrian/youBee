package com.android.app.youbee.integrationTests

import com.android.app.youbee.entity.GorestUserResult
import com.android.app.youbee.entity.User
import com.android.app.youbee.presenter.LogInPresenter
import com.android.app.youbee.repository.UserRepository
import com.android.app.youbee.view.LogInView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val FAKE_EMAIL = "fakeEmail@host.com"
private const val ERROR_MESSAGE = "Email or Password is not correct"

class LogInPresenterTest {
    @Mock
    private val repository: UserRepository = Mockito.mock(UserRepository::class.java)

    @Mock
    private val view: LogInView = Mockito.mock(LogInView::class.java)

    private lateinit var presenter: LogInPresenter
    private lateinit var mockedCall: Call<GorestUserResult>

    @Before
    fun setUp() {
        presenter = LogInPresenter(view, repository)
        mockedCall = Mockito.mock(Call::class.java) as Call<GorestUserResult>
    }

    @Test
    fun testLogInWithExistingUser() {
        val user = User("name", "email", "pswd", "gender")
        Mockito.`when`(
            repository.getUserByEmail(
                presenter,
                FAKE_EMAIL
            )
        ).thenAnswer {
            (it.arguments[0] as Callback<GorestUserResult>).onResponse(
                mockedCall, Response.success(
                    GorestUserResult(listOf(user))
                )
            )
        }
        presenter.validateUser(FAKE_EMAIL)
        Mockito.verify(view).checkUser(listOf(user))
    }

    @Test
    fun handleNoUserFound() {
        Mockito.`when`(
            repository.getUserByEmail(
                presenter,
                FAKE_EMAIL
            )
        ).thenAnswer {
            (it.arguments[0] as Callback<GorestUserResult>).onFailure(
                mockedCall,
                IllegalArgumentException("No record Found")
            )
        }
        presenter.validateUser(FAKE_EMAIL)
        Mockito.verify(view).displayMessage("No record Found")
    }
}