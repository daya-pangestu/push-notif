package com.daya.taha.presentation.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.daya.shared.taha.data.Resource
import com.daya.shared.taha.domain.usecase.IsUserLoggedInUseCase
import com.daya.taha.utils.MainCoroutineRule
import com.daya.taha.utils.getOrAwaitValue
import com.daya.taha.utils.runBlockingTest
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class SplashViewModelTest {

    @get:Rule
    val instantRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private val isUserLoggedInUseCase: IsUserLoggedInUseCase = mock()
    lateinit var splashViewModel: SplashViewModel

    private val dummyResSucces = Resource.success(true)

    @Before
    fun setUp() {
        splashViewModel = SplashViewModel(isUserLoggedInUseCase)
    }

    @Test
    fun isUserLoggedIn() = coroutineRule.runBlockingTest {
        whenever(isUserLoggedInUseCase.invoke(Unit)).thenReturn(dummyResSucces)
        splashViewModel.isUserLoggedIn.observeForever {
            assertThat(it).isEqualTo(dummyResSucces)
        }
    }
}