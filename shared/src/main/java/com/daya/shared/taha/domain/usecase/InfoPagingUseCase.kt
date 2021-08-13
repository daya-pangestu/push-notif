package com.daya.shared.taha.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.daya.shared.taha.data.news.NewsPagingRepository
import com.daya.shared.taha.domain.model.News
import com.daya.shared.taha.domain.model.Topic
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class InfoPagingUseCase
@Inject
constructor(
    private val repo : NewsPagingRepository
){

    operator fun invoke(): Flow<PagingData<News>> {
        return repo.newsPagingSource()
            .map { paging ->
                paging.map {
                    Timber.i("judul ${it.title}")
                    News(
                        senderId = it.senderId,
                        title = it.title,
                        urlAccess = it.urlAccess,
                        description = it.description,
                        urlImage = it.urlImage ?: "",
                        status = it.status,
                        topics = it.topics.map { topicName -> Topic(topicId = "",topicName = topicName)},
                        broadcastRequested = it.broadcastRequested
                    )
                }
            }
    }
}