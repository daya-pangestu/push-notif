package com.daya.taha.presentation.broadcast

import android.net.Uri
import androidx.lifecycle.*
import com.daya.shared.taha.data.Resource
import com.daya.shared.taha.domain.model.News
import com.daya.shared.taha.domain.model.Topic
import com.daya.shared.taha.domain.usecase.BroadCastNewsUseCase
import com.daya.shared.taha.domain.usecase.GetDefaultTopicUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

@HiltViewModel
class BroadCastViewModel
@Inject constructor(
    private val getDefaultTopicUseCase: GetDefaultTopicUseCase,
    private val broadCastNewsUseCase: BroadCastNewsUseCase
): ViewModel() {
    private val _broadcastNewsLiveData = MutableLiveData<News>()


    fun broadcastNews(news : News) {
        _broadcastNewsLiveData.value = news
    }

    val broadcastStatusLiveData = _broadcastNewsLiveData
        .switchMap {
            liveData {
                val status = broadCastNewsUseCase(it)
                emitSource(status.asLiveData())
            }
        }

    //getting topic for broadcast
    private val _getAllDefaultTOpic = liveData<Resource<List<Topic>>> {
        emit(Resource.loading())
        val resTopic = getDefaultTopicUseCase(Unit)
        emit(resTopic)
    }

    fun getTopic() = _getAllDefaultTOpic

    //save selected topic
    private val _chosenTopic = mutableSetOf<Topic>()
    fun pickTopic(topic: Topic) {
        _chosenTopic.add(topic)
    }

    fun removeTopic(topic: Topic) {
        _chosenTopic.remove(topic)
    }

    fun getChosenTopic() = _chosenTopic

    private val _uriImageLiveData = MutableLiveData<Uri?>()
    fun setUriImage(uriImage: Uri) {
        _uriImageLiveData.value = uriImage
    }

    fun getUriImage(): MutableLiveData<Uri?> {
        return _uriImageLiveData
    }
    fun deleteUriImage() {
        _uriImageLiveData.value = null
    }
}