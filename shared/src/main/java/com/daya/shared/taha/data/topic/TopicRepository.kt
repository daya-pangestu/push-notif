package com.daya.shared.taha.data.topic

import com.daya.shared.taha.domain.model.TopicNet
import com.daya.shared.taha.domain.repository.ITopicRepository
import javax.inject.Inject

class TopicRepository
@Inject
constructor(
    private val topicDataSource: TopicDataSource
) : ITopicRepository {
    override suspend fun getDefaultTopic(): List<TopicNet> {
        return topicDataSource.getDefaultTopic()
    }

    override suspend fun subscribeToTopic(topic: TopicNet): Boolean {
       return topicDataSource.subscribeTopic(topic)
    }

    override suspend fun unsubscribeToTopic(topic: TopicNet): Boolean {
        return topicDataSource.unSubscribeTopic(topic)
    }

    override suspend fun getSubscribedTopic(): List<String> {
        TODO("Not yet implemented")
    }
}