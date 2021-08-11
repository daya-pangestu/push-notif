package com.daya.taha.presentation.login


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.daya.shared.taha.data.Resource
import com.daya.shared.taha.domain.usecase.LoginWithCredentialUseCase
import com.daya.shared.taha.domain.usecase.SubscribeUserToDefaultTopicUseCase
import com.daya.taha.utils.MainCoroutineRule
import com.daya.taha.utils.getOrAwaitValue
import com.daya.taha.utils.runBlockingTest
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class LoginViewModelTest {

    @get:Rule
    val instantRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private val loginWithCredentialUseCase = mock<LoginWithCredentialUseCase>()
    private val subscribeUserToDefaultTopicUseCase = mock<SubscribeUserToDefaultTopicUseCase>()
    private lateinit var loginViewModel: LoginViewModel

    private val dummyIdToken = "0878"
    private val dummyDisplayName = "agni"
    private val dummyResSucces = Resource.success(dummyDisplayName)

    @Before
    fun setUp() {
        loginViewModel = LoginViewModel(loginWithCredentialUseCase,subscribeUserToDefaultTopicUseCase)
    }

    @Test
    fun getLoginStatus() = coroutineRule.runBlockingTest {
        whenever(loginWithCredentialUseCase.invoke(dummyIdToken)).thenReturn(dummyResSucces)
        loginViewModel.login(dummyIdToken)
        assertThat(loginViewModel.loginStatus.getOrAwaitValue()).isEqualTo(dummyResSucces)
    }
}