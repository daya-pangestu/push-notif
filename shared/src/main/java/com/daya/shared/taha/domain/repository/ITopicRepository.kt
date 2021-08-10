package com.daya.shared.taha.domain.repository

import com.daya.shared.taha.domain.model.TopicNet

interface ITopicRepository {
    suspend fun getDefaultTopic(): List<TopicNet>
    suspend fun subscribeToTopic(topic: TopicNet) : Boolean
    suspend fun subscribeUserToDefaultTopic()
    suspend fun unsubscribeToTopic(topic: TopicNet) : Boolean
    suspend fun getSubscribedTopic(): List<String>
}