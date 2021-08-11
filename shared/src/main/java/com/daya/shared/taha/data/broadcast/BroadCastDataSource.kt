package com.daya.shared.taha.data.broadcast

import com.daya.shared.taha.domain.model.NewsNet
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

interface BroadCastDataSource {
    suspend fun broadCastNews(newsNet : NewsNet) : Void?
}

class FirebaseBroadCastDataSource
@Inject
constructor(
    private val firestore: FirebaseFirestore
) : BroadCastDataSource{

    override suspend fun broadCastNews(newsNet: NewsNet): Void? = suspendCancellableCoroutine { continuation ->
        firestore.collection("messages").document().set(newsNet, SetOptions.merge())
            .addOnSuccessListener {
               continuation.resume(it)
            }
            .addOnFailureListener {
                continuation.resumeWithException(it)
            }
    }
}