package com.daya.shared.taha.data.auth

import com.daya.shared.taha.domain.repository.IAuthRepository
import com.google.firebase.auth.AuthCredential
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository
@Inject constructor(
    private val authDataSource: FireBaseAuthDataSource
) : IAuthRepository {

    override suspend fun signInWithCredential(credential: AuthCredential): String {
        return authDataSource.signInWithCredential(credential)
    }

    override fun isUserLoggedIn(): Boolean {
        return  authDataSource.isUserLoggedIn()
    }
}