package com.daya.shared.taha.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.daya.shared.taha.data.Resource
import com.daya.shared.taha.data.topic.TopicRepository
import com.daya.shared.taha.domain.TestTopicDataSource
import com.daya.shared.taha.domain.model.TopicNet
import com.daya.shared.taha.domain.repository.ITopicRepository
import com.daya.shared.taha.testutil.Dummy
import com.daya.shared.taha.testutil.MainCoroutineRule
import com.daya.shared.taha.testutil.runBlockingTest
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertTrue
import org.junit.Rule

import org.junit.Test

class GetDefaultTopicUseCaseTest {

    @get:Rule
    val instantRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private val dummyTopicNet = Dummy.topicNet

    @Test
    fun GetDefaultTopicSuccesfully() = coroutineRule.runBlockingTest {
        val useCase = GetDefaultTopicUseCase(coroutineRule.testDispatcher,succesTopicRepository)
        val result = useCase(Unit)
        assertTrue(result is Resource.Success)
        assertThat((result as Resource.Success).data.size).isEqualTo(dummyTopicNet.size)
    }

    @Test
    fun GetDefaultTopicUnSuccesfully() = coroutineRule.runBlockingTest {
        val useCase = GetDefaultTopicUseCase(coroutineRule.testDispatcher,unsuccesfulTopicRepository)
        val result = useCase(Unit)

        assertTrue(result is Resource.Error)
    }

    private val succesTopicRepository = TopicRepository(
        TestTopicDataSource(dummyTopicNet)
    )

    private val unsuccesfulTopicRepository = object : ITopicRepository{
        override suspend fun getDefaultTopic(): List<TopicNet> {
            throw Exception("Error!")
        }

        override suspend fun subscribeToTopic(topic: TopicNet): Boolean {
            throw Exception("Error!")
        }

        override suspend fun unsubscribeToTopic(topic: TopicNet): Boolean {
            throw Exception("Error!")
        }

        override suspend fun getSubscribedTopic(): List<String> {
            throw Exception("Error!")
        }
    }
}