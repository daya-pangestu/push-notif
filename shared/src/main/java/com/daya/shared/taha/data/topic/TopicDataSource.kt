package com.daya.shared.taha.data.topic

import com.daya.shared.taha.di.FirebaseApiService
import com.daya.shared.taha.domain.model.TopicNet
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


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
    private val messaging : FirebaseMessaging,
    private val firebaseService: FirebaseApiService
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

    override suspend fun getSubScribedTopic(): List<String> =
        suspendCancellableCoroutine { continuation ->
            var client: Call<String>? = null
            messaging.token
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val token = it.result!!
                        client = firebaseService.getlistSubscribedTopic(token)
                        client?.enqueue(object : Callback<String> {
                            override fun onResponse(
                                call: Call<String>,
                                response: Response<String>
                            ) {
                                val body = response.body()!!
                                val listTopicString = splitTextToTopics(body)
                                continuation.resume(listTopicString)
                            }

                            override fun onFailure(call: Call<String>, t: Throwable) {
                                continuation.resumeWithException(t)
                            }
                        })
                    } else {
                        continuation.resumeWithException(it.exception!!)
                    }
                }
            continuation.invokeOnCancellation {
                client?.cancel()
            }
        }

    private fun splitTextToTopics(text: String): List<String> {
        val listTopicString = text
            .trim()
            .substringAfter("\"rel\":{\"topics\":{")
            .substringBefore("}},\"appSigner\":")
            .replace("\"","")
            .split("},")
            .asSequence()
            .map{
                it.substringBefore(":{")
            }
        return listTopicString.toList()
    }
}

