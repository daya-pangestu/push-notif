package com.daya.shared.taha.data.broadcast

import com.daya.shared.taha.domain.model.NewsNet
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

interface BroadCastDataSource {
    suspend fun broadCastNews(newsNet : NewsNet) : Void?
}

class FirebaseBroadCastDataSource
@Inject
constructor(
    private val firestore: FirebaseFirestore
)