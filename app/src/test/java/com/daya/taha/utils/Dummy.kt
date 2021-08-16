package com.daya.taha.utils

import com.daya.shared.taha.domain.model.News
import com.daya.shared.taha.domain.model.Topic
import com.daya.shared.taha.domain.model.TopicNet
import io.github.serpro69.kfaker.Faker


object Dummy{
    val faker = Faker()

    val topic: List<Topic>
        get() = (1..3).map {
            Topic(
                topicId = "id$it",
                topicName = "name$it",
                isUnsubscribeAble = false,
                isUserSubscribe = true
            )
        }.toList()

    val topicNet :List<TopicNet>
        get() = (1..3).map {
            TopicNet(
                topicId = "id$it",
                topicName = "name$it",
                isUnsubscribeAble = false,
                isUserSubscribed = true
            )
        }.toList()

    val news: List<News>
        get() = (1..3).map {
            News(
                senderId = faker.phoneNumber.cellPhone(),
                title = faker.book.title(),
                description = faker.book.publisher(),
                urlAccess = faker.address.cityName(),
                urlImage = faker.color.name(),
            )
        }.toList()
}
