package com.daya.shared.taha.data.topic


import com.daya.shared.taha.testutil.Dummy
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class TopicRepositoryTest {

    private val firebaseTopicDataSource = mock<FirebaseTopicDataSource>()
    lateinit var topicRepository: TopicRepository

    private val dummyTopicNet = Dummy.topicNet

    @Before
    fun setUp() {
        topicRepository = TopicRepository(firebaseTopicDataSource)
    }

    @Test
    fun getDefaultTopic() = runBlocking {
        whenever(firebaseTopicDataSource.getDefaultTopic()).thenReturn(dummyTopicNet)

        val actual = topicRepository.getDefaultTopic()

        assertThat(actual).isEqualTo(dummyTopicNet)
    }

    @Test
    fun subscribeToTopic() = runBlocking {
        whenever(firebaseTopicDataSource.subscribeTopic(dummyTopicNet[0])).thenReturn(true)
        val actual = topicRepository.subscribeToTopic(dummyTopicNet[0])
        assertThat(actual).isTrue()
    }

    @Test
    fun subscribeUserToDefaultTopic() = runBlocking {
        topicRepository.subscribeUserToDefaultTopic()
        verify(firebaseTopicDataSource).subscribingUserToDefaultTopic()
    }

    @Test
    fun unsubscribeToTopic() = runBlocking {
        whenever(firebaseTopicDataSource.unSubscribeTopic(dummyTopicNet[0])).thenReturn(true)
        val actual = topicRepository.unsubscribeToTopic(dummyTopicNet[0])
        assertThat(actual).isTrue()
    }

    @Test
    fun getSubscribedTopic() = runBlocking {
        whenever(firebaseTopicDataSource.getSubScribedTopic()).thenReturn(listOf("foo","bar"))
        val actual = topicRepository.getSubscribedTopic()
        assertThat(actual).isEqualTo(listOf("foo","bar"))
    }
}