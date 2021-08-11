package com.daya.shared.taha.domain.usecase

import com.daya.shared.taha.data.Resource
import com.daya.shared.taha.di.coroutine.IoDispatcher
import com.daya.shared.taha.domain.model.News
import com.daya.shared.taha.domain.model.NewsNet
import com.daya.shared.taha.domain.repository.IbroadCastRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class BroadCastNewsUseCase
@Inject
constructor(
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
    private val broadCastRepository : IbroadCastRepository

) : FlowUseCase<News, Void?>(coroutineDispatcher) {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun execute(param: News): Flow<Resource<Void?>> = callbackFlow<Resource<Void?>>  {
        val newsNet = NewsNet(
            senderId = param.senderId,
            title = param.title,
            urlAccess = param.urlAccess,
            description = param.description,
            urlImage = param.urlImage,
            status = param.status,
            topics = param.topics.map { it.topicName },
            broadcastRequested = param.broadcastRequested
        )

        trySend(
            Resource.loading(
               "BroadCasting news"
            )
        )

        var casted = broadCastRepository.broadCastNews(newsNet)
        val resCasted = Resource.Success(casted)
        trySend(resCasted)

        awaitClose {
            casted = null
        }
    }



}