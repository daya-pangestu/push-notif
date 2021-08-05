package com.daya.shared.taha.domain.repository

import com.google.firebase.auth.AuthCredential

interface IAuthRepository {
    suspend fun signInWithCredential(credential: AuthCredential) : Boolean
}