package com.daya.shared.taha.domain.usecase

import com.daya.shared.taha.data.topic.TopicRepository
import com.daya.shared.taha.di.coroutine.IoDispatcher
import com.daya.shared.taha.domain.model.Topic
import com.daya.shared.taha.domain.model.TopicNet
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SubscribeToTopicUseCase
@Inject
constructor(
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
    private val topicRepository: TopicRepository
) : UseCase<Topic, Boolean>(coroutineDispatcher) {

    override suspend fun execute(param: Topic): Boolean {
        val topicNet = TopicNet(
            topicId = param.topicId,
            topicName = param.topicName,
            isUserSubscribed = param.isUserSubscribe,
            isUnsubscribeAble = param.isUnsubscribeAble
        )
        return topicRepository.subscribeToTopic(topicNet)
    }
}