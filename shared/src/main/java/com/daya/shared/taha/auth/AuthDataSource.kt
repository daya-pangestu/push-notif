package com.daya.shared.taha.auth

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

interface AuthDataSource{
    suspend fun signInWithCredential(credential : AuthCredential) : String
}

class FireBaseAuthDataSource
@Inject
constructor(
    private val fireStore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : AuthDataSource {

    override suspend fun signInWithCredential(credential: AuthCredential) :String = suspendCancellableCoroutine { continuation ->
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

}