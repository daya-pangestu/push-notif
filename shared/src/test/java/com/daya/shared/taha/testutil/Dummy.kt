package com.daya.shared.taha.testutil

import com.daya.shared.taha.domain.model.TopicNet
import io.github.serpro69.kfaker.Faker


object Dummy{

val topicNet: List<TopicNet>
    get() = (1..3).map {
            TopicNet(
                topicId = faker.phoneNumber.unique.countryCode(),
                topicName = faker.book.title(),
                isUnsubscribeAble = false,
                isUserSubscribed = true
            )
        }.toList()



private val faker = Faker()
}
