package com.daya.shared.taha.data.topic

import com.daya.shared.taha.domain.model.TopicNet
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


interface TopicDataSource {
    suspend fun getDefaultTopic() :List<TopicNet>
}

class FirebaseTopicDataSource
@Inject
constructor(
    private val firestore: FirebaseFirestore,
    private val messaging : FirebaseMessaging
) : TopicDataSource {

    override suspend fun getDefaultTopic(): List<TopicNet>{
        val querySnapshot = firestore
            .collection("topics")
            .get()
            .await()

        return querySnapshot.documents.asSequence().map {
            val topicId = it.id
            val name = it.data?.get("topicName").toString()
            val isUnsubscribeAble = it.data?.get("isUnsubscribeAble") as Boolean
            TopicNet(topicId = topicId, topicName = name, isUnsubscribeAble = isUnsubscribeAble)
        }.toList()
    }

}

