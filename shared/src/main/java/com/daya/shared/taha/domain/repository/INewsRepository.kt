package com.daya.shared.taha.domain.repository

import androidx.paging.PagingData
import com.daya.shared.taha.domain.model.NewsNet
import kotlinx.coroutines.flow.Flow

interface INewsRepository {
    fun newsPagingSource() : Flow<PagingData<NewsNet>>
}