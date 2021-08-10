package com.daya.taha.presentation.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.daya.shared.taha.data.Resource
import com.daya.shared.taha.domain.model.Topic
import com.daya.shared.taha.domain.usecase.GetTopicWithSubscribeStatusUseCase
import com.daya.shared.taha.domain.usecase.SubscribeToTopicUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel
@Inject
constructor(
    private val getTopicWithSubscribeStatusUseCase: GetTopicWithSubscribeStatusUseCase,
    private val subscribeToTopicUseCase: SubscribeToTopicUseCase,
) : ViewModel() {

    private val _getTopicWithSubscribeStatus = liveData {
        emit(Resource.loading())
        val topics = getTopicWithSubscribeStatusUseCase(Unit)
        emit(topics)
    }

    val getTopicWithSubscribedStatusLiveData get() = _getTopicWithSubscribeStatus

    fun subscribeTopic(topic: Topic) {
        viewModelScope.launch {
            when (val isSubscribeSuccess = subscribeToTopicUseCase(topic)) {
                is Resource.Success -> isSubscribeSuccess.data
            }
        }
    }

    fun unsubscribeTopic(topic: Topic) {

    }

}