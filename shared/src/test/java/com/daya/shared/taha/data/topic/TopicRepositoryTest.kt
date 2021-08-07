package com.daya.shared.taha.data.topic


import com.daya.shared.taha.testutil.Dummy
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
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
}