package com.daya.shared.taha.data.auth


import com.daya.shared.taha.testutil.Dummy
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class AuthRepositoryTest {

    private val authDataSource: FireBaseAuthDataSource = mock()
    lateinit var authRepository : AuthRepository

    val dummyIdToken = Dummy.faker.idNumber.invalid()
    val dummyDIsplayName = Dummy.faker.name.name()

    @Before
    fun setUp() {
        authRepository = AuthRepository(authDataSource)
    }

    @Test
    fun signInWithCredential() = runBlocking {
        whenever(authDataSource.signInWithCredential(dummyIdToken)).thenReturn(dummyDIsplayName)

        val actualResult = authRepository.signInWithCredential(dummyIdToken)

        assertThat(actualResult).isEqualTo(dummyDIsplayName)
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