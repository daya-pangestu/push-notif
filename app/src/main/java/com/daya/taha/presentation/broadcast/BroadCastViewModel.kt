package com.daya.taha.presentation.broadcast

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.daya.shared.taha.data.Resource
import com.daya.shared.taha.domain.model.News
import com.daya.shared.taha.domain.model.Topic
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class BroadCastViewModel
@Inject constructor(

): ViewModel() {
    private val _broadcastNewsLiveData = MutableLiveData<News>()

    fun broadcastNews(news : News) {
        Timber.i("adding news soon to be broadcast : $news")
    }

    val broadcastingLiveData = _broadcastNewsLiveData

    //getting topic for broadcast
    private val _getAllDefaultTOpic =  MutableLiveData<Resource<List<Topic>>>()

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