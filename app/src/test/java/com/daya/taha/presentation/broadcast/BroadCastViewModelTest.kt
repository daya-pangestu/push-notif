package com.daya.taha.presentation.broadcast

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.daya.shared.taha.data.Resource
import com.daya.shared.taha.domain.usecase.BroadCastNewsUseCase
import com.daya.shared.taha.domain.usecase.GetDefaultTopicUseCase
import com.daya.taha.utils.*
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.*


class BroadCastViewModelTest {

    @get:Rule
    val instantRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private val getDefaultTopicUseCase: GetDefaultTopicUseCase = mock()
    private val broadCastNewsUseCase : BroadCastNewsUseCase = mock()
    lateinit var broadCastViewModel: BroadCastViewModel

    private val dummyResSucces = Resource.success(Dummy.topic)

    @Before
    fun setUp() {
        broadCastViewModel = BroadCastViewModel(getDefaultTopicUseCase,broadCastNewsUseCase)
    }

    @Test
    fun getTopic() = coroutineRule.runBlockingTest {
        whenever(getDefaultTopicUseCase(Unit)).thenReturn(dummyResSucces)
        val initialResult = broadCastViewModel.getTopic().getOrAwaitValue()
        assertThat(initialResult is Resource.Loading)

        //endResult
        broadCastViewModel.getTopic().observeForTesting {
            val endResult = broadCastViewModel.getTopic().value
            assertThat(endResult).isEqualTo(dummyResSucces)
        }
    }

    @Test
    fun `delete UriImage`() {
        broadCastViewModel.deleteUriImage()
        assertThat(broadCastViewModel.getUriImage().getOrAwaitValue()).isEqualTo(null)
    }
}
