package com.daya.shared.taha.domain

import com.daya.shared.taha.data.auth.AuthDataSource
import com.google.firebase.auth.AuthCredential

object TestAuthDataSource : AuthDataSource {
    override suspend fun signInWithCredential(credential: AuthCredential): String {
        TODO("Not yet implemented")
    }

    override fun isUserLoggedIn(): Boolean {
        return true
    }

    override fun loggingOutCurrentUSer() {
        TODO("Not yet implemented")
    }
}