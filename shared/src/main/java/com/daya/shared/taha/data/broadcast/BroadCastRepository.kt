package com.daya.shared.taha.data.broadcast

import com.daya.shared.taha.domain.model.NewsNet
import com.daya.shared.taha.domain.repository.IbroadCastRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.StorageReference
import java.io.InputStream
import javax.inject.Inject

class BroadCastRepository
@Inject
constructor(
    private val firebaseBroadCastDataSource: BroadCastDataSource
): IbroadCastRepository {

    override suspend fun broadCastNews(newsNet: NewsNet): Void? {
        return firebaseBroadCastDataSource.broadCastNews(newsNet)
    }

    override fun addChild(filename: String): StorageReference {
        return firebaseBroadCastDataSource.addChild(filename)
    }

    override fun readFile(uriImage: String): InputStream? {
        return firebaseBroadCastDataSource.readFile(uriImage)
    }

    override suspend fun broadCastNewsWithImage(newsNet: NewsNet): Task<Void> {
        return firebaseBroadCastDataSource.broadCastNewsWithImage(newsNet)
    }
}