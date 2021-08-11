package com.daya.shared.taha.domain.repository

import com.daya.shared.taha.domain.model.NewsNet
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.StorageReference
import java.io.InputStream

interface IbroadCastRepository {
    suspend fun broadCastNews(newsNet: NewsNet): Void?

    fun addChild(filename : String) : StorageReference
    fun readFile(uriImage : String) : InputStream?
    suspend fun broadCastNewsWithImage(newsNet: NewsNet): Task<Void>
}