package com.daya.shared.taha.data.news

import androidx.paging.LoadState
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.daya.shared.taha.domain.model.NewsNet
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.inject.Inject

class NewsPagingDataSource
@Inject
constructor(
    private val newsRef : CollectionReference
) : PagingSource<Date,NewsNet>(){

    override fun getRefreshKey(state: PagingState<Date, NewsNet>): Date? {
        val lastPosition = state.anchorPosition ?: return null
        val lastFetchedInfo = state.closestItemToPosition(lastPosition)
        return lastFetchedInfo?.broadcastRequested
    }

    override suspend fun load(params: LoadParams<Date>): LoadResult<Date, NewsNet> {
        try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: Date()

            val batch = newsRef
                .orderBy("broadcastRequested", Query.Direction.DESCENDING)
                .limit(10)
                .startAfter(nextPageNumber)
                .get()
                .await()

            val listInfoEntity = batch.documents.map {
                it.toObject(NewsNet::class.java).apply {
                    this ?: return@apply
                    senderId = it.id
                }
            }.filterNotNull()
            return LoadResult.Page(
                data = listInfoEntity,
                prevKey = null, // Only paging forward.
                nextKey = listInfoEntity.last().broadcastRequested
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}