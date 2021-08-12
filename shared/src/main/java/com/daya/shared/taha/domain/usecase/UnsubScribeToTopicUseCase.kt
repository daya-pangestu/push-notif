package com.daya.shared.taha.domain.usecase

import com.daya.shared.taha.di.coroutine.IoDispatcher
import com.daya.shared.taha.domain.model.Topic
import com.daya.shared.taha.domain.model.TopicNet
import com.daya.shared.taha.domain.repository.ITopicRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class UnsubScribeToTopicUseCase
@Inject
constructor(
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
    private val topicRepository: ITopicRepository
) : UseCase<Topic, Boolean>(coroutineDispatcher) {

    override suspend fun execute(param: Topic): Boolean {
        val topicNet = TopicNet(
            topicId = param.topicId,
            topicName = param.topicName,
            isUserSubscribed = param.isUserSubscribe,
            isUnsubscribeAble = param.isUnsubscribeAble
        )
        return topicRepository.unsubscribeToTopic(topicNet)
    }
}