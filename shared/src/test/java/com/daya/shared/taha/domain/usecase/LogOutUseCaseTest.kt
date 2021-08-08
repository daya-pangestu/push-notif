package com.daya.shared.taha.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.daya.shared.taha.data.Resource
import com.daya.shared.taha.data.auth.AuthRepository
import com.daya.shared.taha.testutil.fake.FakeAuthDataSource
import com.daya.shared.taha.testutil.MainCoroutineRule
import com.daya.shared.taha.testutil.fake.unSuccesAuthRepository
import com.daya.shared.taha.testutil.runBlockingTest
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test


class LogOutUseCaseTest{

    @get:Rule
    val instantRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @Test
    fun logoutSuccesfully() = coroutineRule.runBlockingTest {
        val useCase = LogOutUseCase(coroutineRule.testDispatcher, succesAuthRepository)

        val result = useCase(Unit)
        assertThat(result is Resource.Success)
    }

    @Test
    fun logoutUnSuccesfully() = coroutineRule.runBlockingTest {
        val useCase = LogOutUseCase(coroutineRule.testDispatcher, unSuccesAuthRepository)

        val result = useCase(Unit)
        assertThat(result is Resource.Error)
    }

    private val succesAuthRepository = AuthRepository(
        FakeAuthDataSource()
    )
}