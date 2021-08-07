package com.daya.shared.taha.data.auth


import com.google.common.truth.Truth.assertThat
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class AuthRepositoryTest {

    private val authDataSource: FireBaseAuthDataSource = mock()
    private val credential : AuthCredential = mock()
    lateinit var authRepository : AuthRepository

    @Before
    fun setUp() {
        authRepository = AuthRepository(authDataSource)
    }

    @Test
    fun signInWithCredential() = runBlocking {
        whenever(authDataSource.signInWithCredential(credential)).thenReturn("user")

        val actualResult = authRepository.signInWithCredential(credential)

        assertThat(actualResult).isEqualTo("user")
    }

    @Test
    fun isUserLoggedIn() {
        whenever(authDataSource.isUserLoggedIn()).thenReturn(true)

        val actualResult = authRepository.isUserLoggedIn()

        assertThat(actualResult).isEqualTo(true)
    }

    @Test
    fun logginOutCurrentUser() {
        authRepository.logginOutCurrentUser()
        verify(authDataSource).loggingOutCurrentUSer()
    }
}