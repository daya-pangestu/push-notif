package com.daya.shared.taha.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.daya.shared.taha.data.Resource
import com.daya.shared.taha.data.auth.AuthRepository
import com.daya.shared.taha.domain.TestAuthDataSource
import com.daya.shared.taha.domain.repository.IAuthRepository
import com.daya.shared.taha.testutil.Dummy
import com.daya.shared.taha.testutil.MainCoroutineRule
import com.daya.shared.taha.testutil.runBlockingTest
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class LoginWIthCredentialUseCase {

    @get:Rule
    val instantRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    val dummyIdToken = Dummy.faker.idNumber.invalid()
    val dummyDIsplayName = Dummy.faker.name.name()

    @Test
    fun loginWithCredentialSuccesfully() = coroutineRule.runBlockingTest {
        val useCase = LoginWithCredentialUseCase(coroutineRule.testDispatcher, succesAuthRepository)

        val result = useCase(dummyIdToken)
        assertThat(result).isEqualTo(Resource.Success(dummyDIsplayName))
    }

    @Test
    fun loginWithCredentialUnSuccesfully() = coroutineRule.runBlockingTest {
        val useCase = LoginWithCredentialUseCase(coroutineRule.testDispatcher, unSuccesAuthRepository)

        val result = useCase(dummyIdToken)
        assertTrue(result is Resource.Error)
    }

    private val succesAuthRepository = AuthRepository(
        TestAuthDataSource(dummyDIsplayName)
    )

    private val unSuccesAuthRepository = object : IAuthRepository {
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