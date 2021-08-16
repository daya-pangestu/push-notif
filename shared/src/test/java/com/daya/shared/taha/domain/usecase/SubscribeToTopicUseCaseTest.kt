package com.daya.shared.taha.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.daya.shared.taha.data.Resource
import com.daya.shared.taha.data.topic.TopicRepository
import com.daya.shared.taha.domain.model.TopicNet
import com.daya.shared.taha.domain.model.mapNetToGeneral
import com.daya.shared.taha.domain.repository.ITopicRepository
import com.daya.shared.taha.testutil.Dummy
import com.daya.shared.taha.testutil.MainCoroutineRule
import com.daya.shared.taha.testutil.fake.FakeTopicDataSource
import com.daya.shared.taha.testutil.fake.unsuccesfulTopicRepository
import com.daya.shared.taha.testutil.runBlockingTest
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import junit.framework.Assert.assertTrue
import org.junit.Rule
import org.junit.Test


class SubscribeToTopicUseCaseTest{

    @get:Rule
    val instantRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private val dummyTopicNet = Dummy.topicNet
    private val dummyTopic = dummyTopicNet.mapNetToGeneral()[0]

    @Test
    fun subscribeToTopicSuccesfully() = coroutineRule.runBlockingTest {
        val useCase = SubscribeToTopicUseCase(coroutineRule.testDispatcher,succesTopicRepository)
        val result = useCase(dummyTopic)
        assertTrue(result is Resource.Success)
        assertThat((result as Resource.Success).data).isEqualTo(true)
    }

    @Test
    fun subscribeToTopicUnSuccesfully() = coroutineRule.runBlockingTest {
        val useCase = SubscribeToTopicUseCase(coroutineRule.testDispatcher,unsuccesfulTopicRepository)
        val result = useCase(dummyTopic)

        assertTrue(result is Resource.Error)
    }

    private val succesTopicRepository = object : ITopicRepository{
        override suspend fun getDefaultTopic(): List<TopicNet> = emptyList()
        override suspend fun subscribeToTopic(topic: TopicNet): Boolean = true
        override suspend fun subscribeUserToDefaultTopic() = Unit
        override suspend fun unsubscribeToTopic(topic: TopicNet): Boolean = true
        override suspend fun getSubscribedTopic(): List<String> = emptyList()
    }
}