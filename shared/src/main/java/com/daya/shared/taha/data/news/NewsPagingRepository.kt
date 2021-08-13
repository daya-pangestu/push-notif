package com.daya.shared.taha.data.news

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.daya.shared.taha.domain.model.NewsNet
import com.daya.shared.taha.domain.repository.INewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsPagingRepository
@Inject
constructor(
    private val infoPagingDataSource: NewsPagingDataSource
) : INewsRepository{

    override fun newsPagingSource(): Flow<PagingData<NewsNet>> {
        return Pager(
            PagingConfig(10)
        ){
            infoPagingDataSource
        }.flow
    }

}