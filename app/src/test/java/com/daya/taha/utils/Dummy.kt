package com.daya.taha.utils

import com.daya.shared.taha.domain.model.Topic
import com.daya.shared.taha.domain.model.TopicNet
import io.github.serpro69.kfaker.Faker


object Dummy{

val topic: List<Topic>
    get() = (1..3).map {
        Topic(
            topicId = faker.phoneNumber.countryCode(),
            topicName = faker.book.title(),
            isUnsubscribeAble = false,
            isUserSubscribe = true
        )
        }.toList()

    val faker = Faker()
}
