package com.daya.shared.taha.domain.usecase

import com.daya.shared.taha.data.Resource
import com.daya.shared.taha.di.coroutine.IoDispatcher
import com.daya.shared.taha.domain.model.News
import com.daya.shared.taha.domain.model.NewsNet
import com.daya.shared.taha.domain.repository.IbroadCastRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class BroadCastNewsUseCase
@Inject
constructor(
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
    private val broadCastRepository : IbroadCastRepository

) : FlowUseCase<News, Unit>(coroutineDispatcher) {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun execute(param: News): Flow<Resource<Unit>> = callbackFlow<Resource<Unit>>  {
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

        val casted = broadCastRepository.broadCastNews(newsNet)
        val resCasted = casted!!.let {
            Resource.Success(Unit)
        }
        trySend(resCasted)
    }



}