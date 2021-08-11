package com.daya.shared.taha.data.broadcast

import com.daya.shared.taha.domain.model.NewsNet
import com.daya.shared.taha.domain.repository.IbroadCastRepository
import javax.inject.Inject

class BroadCastRepository
@Inject
constructor(
    private val firebaseBroadCastDataSource: BroadCastDataSource
): IbroadCastRepository {

    override suspend fun broadCastNews(newsNet: NewsNet): Void? {
        return firebaseBroadCastDataSource.broadCastNews(newsNet)
    }
}