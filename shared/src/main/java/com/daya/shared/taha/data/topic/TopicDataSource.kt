package com.daya.shared.taha.data.topic

import com.daya.shared.taha.domain.model.TopicNet
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.resume


interface TopicDataSource {
    suspend fun getDefaultTopic() :List<TopicNet>
    suspend fun subscribeingUserToDefaultTopic()
    suspend fun subscribeTopic(topic: TopicNet) : Boolean
    suspend fun unSubscribeTopic(topic: TopicNet) : Boolean
    suspend fun getSubScribedTopic() :List<String>
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

    override suspend fun subscribeingUserToDefaultTopic() {
        return firestore
            .collection("topics")
            .get().await()
            .documents
            .asSequence()
            .map {
                val topicId = it.id
                val name = it.data?.get("topicName").toString()
               TopicNet(topicId,name)
            }
            .forEach {
                subscribeTopic(it)
            }
    }

    override suspend fun subscribeTopic(topic: TopicNet) : Boolean = suspendCancellableCoroutine {continuation ->
        messaging.subscribeToTopic(topic.topicName)
            .addOnCompleteListener { task ->
                val isSuccess = task.isSuccessful
                val msg = if (isSuccess) {
                    "subscribing to ${topic.topicName} success"
                } else {
                    "subscribing to ${topic.topicName} failed"
                }
                Timber.i(msg)
                continuation.resume(isSuccess)
            }
    }

    override suspend fun unSubscribeTopic(topic: TopicNet) : Boolean = suspendCancellableCoroutine { continuation ->
        messaging.unsubscribeFromTopic(topic.topicName)
            .addOnCompleteListener { task ->
                val isSuccess = task.isSuccessful
                val msg = if (task.isSuccessful) {
                    "unsubscribed to ${topic.topicName} success"
                } else {
                    "unsubscribed to ${topic.topicName} failed"
                }
                Timber.i(msg)
                continuation.resume(isSuccess)
            }
    }

    override suspend fun getSubScribedTopic(): List<String> {
        TODO("Not yet implemented")
    }

}

