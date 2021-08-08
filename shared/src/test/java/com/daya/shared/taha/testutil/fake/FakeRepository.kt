package com.daya.shared.taha.testutil.fake

import com.daya.shared.taha.domain.repository.IAuthRepository


val unSuccesAuthRepository = object : IAuthRepository {
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