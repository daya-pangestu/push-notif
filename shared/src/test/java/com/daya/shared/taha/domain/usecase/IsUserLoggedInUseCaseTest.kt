package com.daya.shared.taha.domain.usecase


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.daya.shared.taha.data.Resource
import com.daya.shared.taha.data.auth.AuthRepository
import com.daya.shared.taha.testutil.fake.FakeAuthDataSource
import com.daya.shared.taha.domain.repository.IAuthRepository
import com.daya.shared.taha.testutil.MainCoroutineRule
import com.daya.shared.taha.testutil.runBlockingTest
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class IsUserLoggedInUseCaseTest {

    @get:Rule
    val instantRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @Test
    fun IsUserLoggedInSuccesfully() = coroutineRule.runBlockingTest {
        val useCase = IsUserLoggedInUseCase(coroutineRule.testDispatcher,succesAuthRepository)
        val result = useCase(Unit)
        assertTrue(result is Resource.Success)
    }

    @Test
    fun IsUserLoggedInUnSuccesfully() = coroutineRule.runBlockingTest {
        val useCase = IsUserLoggedInUseCase(coroutineRule.testDispatcher,unsuccesAuthRepository)
        val result = useCase(Unit)

        assertTrue(result is Resource.Error)
    }

    private val succesAuthRepository = AuthRepository(
        FakeAuthDataSource()
    )

    private val unsuccesAuthRepository = object : IAuthRepository {
        override suspend fun signInWithCredential(idToken : String): String {
            throw Exception("Error!")
        }

        override fun isUserLoggedIn(): Boolean {
            throw Exception("Error!")
        }

        override fun logginOutCurrentUser() {
            throw Exception("Error!")
        }
    }
}