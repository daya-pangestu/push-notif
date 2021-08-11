package com.daya.shared.taha.data.broadcast

import android.content.Context
import androidx.core.net.toUri
import com.daya.shared.taha.domain.model.NewsNet
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.StorageReference
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.InputStream
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

interface BroadCastDataSource {
    suspend fun broadCastNews(newsNet : NewsNet) : Void?

    fun addChild(filename : String) : StorageReference
    fun readFile(uriImage : String) : InputStream?
    suspend fun broadCastNewsWithImage(newsNet: NewsNet): Task<Void>
}

class FirebaseBroadCastDataSource
@Inject
constructor(
    private val fireStore: FirebaseFirestore,
    private val imageRef : StorageReference,
    @ApplicationContext private val context: Context
) : BroadCastDataSource{

    override suspend fun broadCastNews(newsNet: NewsNet): Void? = suspendCancellableCoroutine { continuation ->
        fireStore.collection("messages").document().set(newsNet, SetOptions.merge())
            .addOnSuccessListener {
               continuation.resume(it)
            }
            .addOnFailureListener {
                continuation.resumeWithException(it)
            }
    }

    override fun addChild(filename: String): StorageReference {
        return imageRef.child(filename)
    }

    override fun readFile(uriImage: String): InputStream? {
        return context.contentResolver.openInputStream(uriImage.toUri())
    }

    override suspend fun broadCastNewsWithImage(newsNet: NewsNet): Task<Void> {
        return fireStore.collection("messages").document().set(newsNet, SetOptions.merge())
    }
}