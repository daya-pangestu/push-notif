package com.daya.taha.presentation.setting


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.daya.shared.taha.data.Resource
import com.daya.shared.taha.domain.usecase.GetTopicWithSubscribeStatusUseCase
import com.daya.shared.taha.domain.usecase.SubscribeToTopicUseCase
import com.daya.shared.taha.domain.usecase.UnsubScribeToTopicUseCase
import com.daya.taha.presentation.login.LoginViewModel
import com.daya.taha.utils.Dummy
import com.daya.taha.utils.MainCoroutineRule
import com.daya.taha.utils.getOrAwaitValue
import com.daya.taha.utils.observeForTesting
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class SettingsViewModelTest {

    @get:Rule
    val instantRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private val getTopicWithSubscribeStatusUseCase: GetTopicWithSubscribeStatusUseCase = mock()
    private val subscribeToTopicUseCase: SubscribeToTopicUseCase = mock()
    private val unsubScribeToTopicUseCase: UnsubScribeToTopicUseCase= mock()

    lateinit var settingsViewModel: SettingsViewModel

    private val dummyResSucces = Resource.success(Dummy.topic)


    @Before
    fun setUp() {
        settingsViewModel = SettingsViewModel(getTopicWithSubscribeStatusUseCase,subscribeToTopicUseCase,unsubScribeToTopicUseCase)
    }

    @Test
    fun getGetTopicWithSubscribedStatusLiveData() = runBlocking {
        whenever(getTopicWithSubscribeStatusUseCase(Unit)).thenReturn(dummyResSucces)

        val initialResult = settingsViewModel.getTopicWithSubscribedStatusLiveData.getOrAwaitValue()
        assertThat(initialResult is Resource.Loading)

        //endResult
        settingsViewModel.getTopicWithSubscribedStatusLiveData.observeForTesting {
            val endResult = settingsViewModel.getTopicWithSubscribedStatusLiveData.value
            assertThat(endResult).isEqualTo(dummyResSucces)
        }
    }

}