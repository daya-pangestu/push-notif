package com.daya.shared.taha.domain.repository

interface IAuthRepository {
    suspend fun signInWithCredential(idToken : String) : String
    fun isUserLoggedIn() : Boolean
    fun logginOutCurrentUser()

}