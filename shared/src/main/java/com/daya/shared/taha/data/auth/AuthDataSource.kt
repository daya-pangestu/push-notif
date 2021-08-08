package com.daya.shared.taha.data.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

interface AuthDataSource{
    suspend fun signInWithCredential(idToken : String) : String
    fun isUserLoggedIn() : Boolean
    fun loggingOutCurrentUSer()
}

class FireBaseAuthDataSource
@Inject
constructor(
    private val auth: FirebaseAuth
) : AuthDataSource {

    override suspend fun signInWithCredential(idToken :String) :String = suspendCancellableCoroutine { continuation ->
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnSuccessListener {
                val name = it.user?.displayName ?: return@addOnSuccessListener continuation.resume("user")
                Timber.i("signin with credential succesfull")
                continuation.resume(name)
            }.addOnFailureListener {
                Timber.i("signin with credential failed : ${it.message}")
                continuation.resumeWithException(it)
            }
    }

    override fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    override fun loggingOutCurrentUSer(){
        auth.signOut()
    }
}