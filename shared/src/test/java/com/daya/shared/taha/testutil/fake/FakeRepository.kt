package com.daya.shared.taha.testutil.fake

import com.daya.shared.taha.domain.model.TopicNet
import com.daya.shared.taha.domain.repository.IAuthRepository
import com.daya.shared.taha.domain.repository.ITopicRepository


val unSuccesAuthRepository = object : IAuthRepository {
    override suspend fun signInWithCredential(idToken : String): String {
        throw Exception("Error!")
    }

    override fun isUserLoggedIn(): Boolean {
        throw Exception("Error!")
    }

    override fun logginOutCurrentUser() {
        throw Exception("Error!")
    }
}

val unsuccesfulTopicRepository = object : ITopicRepository {
    override suspend fun getDefaultTopic(): List<TopicNet> {
        throw Exception("Error!")
    }

    override suspend fun subscribeToTopic(topic: TopicNet): Boolean {
        throw Exception("Error!")
    }

    override suspend fun subscribeUserToDefaultTopic() {
        throw Exception("Error!")
    }

    override suspend fun unsubscribeToTopic(topic: TopicNet): Boolean {
        throw Exception("Error!")
    }

    override suspend fun getSubscribedTopic(): List<String> {
        throw Exception("Error!")
    }

}