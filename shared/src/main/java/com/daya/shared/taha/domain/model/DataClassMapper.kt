package com.daya.shared.taha.domain.model


fun List<TopicNet>.mapNetToGeneral(): List<Topic> {
    return this.asSequence()
        .map {
            Topic(
                topicId = it.topicId,
                topicName = it.topicName,
                isUserSubscribe =it.isUserSubscribed,
                isUnsubscribeAble = it.isUnsubscribeAble
            )
        }
        .toList()
}

fun List<Topic>.mapGeneralToNet(): List<TopicNet> {
    return this.asSequence()
        .map {
            TopicNet(
                topicId = it.topicId,
                topicName = it.topicName,
                isUserSubscribed  =it.isUserSubscribe,
                isUnsubscribeAble = it.isUnsubscribeAble
            )
        }
        .toList()
}