package com.daya.shared.taha.domain

import com.daya.shared.taha.data.auth.AuthDataSource
import com.google.firebase.auth.AuthCredential

class TestAuthDataSource(private val displayName : String = "") : AuthDataSource {
    override suspend fun signInWithCredential(idToken: String): String {
        return displayName
    }

    override fun isUserLoggedIn(): Boolean {
        return true
    }

    override fun loggingOutCurrentUSer() {
        return Unit
    }
}