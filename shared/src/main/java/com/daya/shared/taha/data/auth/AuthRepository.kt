package com.daya.shared.taha.data.auth

import com.daya.shared.taha.domain.repository.IAuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository
@Inject constructor(
    private val authDataSource: AuthDataSource
) : IAuthRepository {

    override suspend fun signInWithCredential(idToken: String): String {
        return authDataSource.signInWithCredential(idToken)
    }

    override fun isUserLoggedIn(): Boolean {
        return  authDataSource.isUserLoggedIn()
    }

    override fun logginOutCurrentUser() {
        authDataSource.loggingOutCurrentUSer()
    }
}