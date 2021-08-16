package com.daya.shared.taha.testutil

import android.net.Uri
import com.daya.shared.taha.domain.model.NewsNet
import com.daya.shared.taha.domain.model.TopicNet
import com.google.firebase.firestore.ServerTimestamp
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import io.github.serpro69.kfaker.Faker
import org.mockito.kotlin.any
import java.util.*


object Dummy{
    val faker = Faker()

    val topicNet: List<TopicNet>
        get() = (1..3).map {
            TopicNet(
                topicId = faker.phoneNumber.countryCode(),
                topicName = faker.book.title(),
                isUnsubscribeAble = false,
                isUserSubscribed = true
            )
        }.toList()

    val newsNet: List<NewsNet>
        get() = (1..3).map {
            NewsNet(
                senderId = faker.idNumber.invalid(),
                title = faker.book.title(),
                description = faker.book.publisher(),
                urlAccess = faker.verbs.pastParticiple(),
                urlImage = faker.show.play(),
                status = "broadcasted",
                topics = listOf("foo","baz"),
            )
        }

}
