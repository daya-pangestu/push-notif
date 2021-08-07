package com.daya.shared.taha.domain

import com.daya.shared.taha.data.topic.TopicDataSource
import com.daya.shared.taha.domain.model.TopicNet

class TestTopicDataSource(private val list : List<TopicNet>) : TopicDataSource {

    override suspend fun getDefaultTopic(): List<TopicNet> {
        return list
    }
}