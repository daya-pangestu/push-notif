package com.daya.shared.taha.testutil.fake

import com.daya.shared.taha.data.auth.AuthDataSource
import com.daya.shared.taha.data.topic.TopicDataSource
import com.daya.shared.taha.domain.model.TopicNet
import com.google.firebase.auth.AuthCredential

class FakeAuthDataSource(private val displayName : String = "") : AuthDataSource {
    override suspend fun signInWithCredential(idToken: String): String {
        return displayName
    }

    override fun isUserLoggedIn(): Boolean {
        return true
    }

    override fun loggingOutCurrentUSer() {
        return Unit
    }
}

class FakeTopicDataSource(private val defaultTopic : List<TopicNet> = emptyList(),private val subscribedTopic : List<String> = emptyList()) : TopicDataSource {
    override suspend fun getDefaultTopic(): List<TopicNet> = defaultTopic
    override suspend fun subscribeingUserToDefaultTopic()= Unit
    override suspend fun subscribeTopic(topic: TopicNet): Boolean = true
    override suspend fun unSubscribeTopic(topic: TopicNet): Boolean = true
    override suspend fun getSubScribedTopic(): List<String> = subscribedTopic
}