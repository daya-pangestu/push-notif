package com.daya.shared.taha.domain.usecase

import com.daya.shared.taha.di.coroutine.IoDispatcher
import com.daya.shared.taha.domain.model.Topic
import com.daya.shared.taha.domain.repository.ITopicRepository
import kotlinx.coroutines.CoroutineDispatcher
import timber.log.Timber
import javax.inject.Inject

class GetTopicWithSubscribeStatusUseCase
@Inject
constructor(
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
    private val repository: ITopicRepository
) : UseCase<Unit, List<Topic>>(coroutineDispatcher) {

    override suspend fun execute(param: Unit): List<Topic> {
        val defaultTopics = repository.getDefaultTopic()
        val topicWithSubscribeStatus = repository.getSubscribedTopic()
        val commonTopic = defaultTopics
            .map {
                val isCommon = it.topicName in topicWithSubscribeStatus
                Topic(
                    topicId = it.topicId,
                    topicName = it.topicName,
                    isUserSubscribe = isCommon,
                    isUnsubscribeAble = it.isUnsubscribeAble
                )
            }
        Timber.i("comonTopic $commonTopic")
        return commonTopic
    }
}