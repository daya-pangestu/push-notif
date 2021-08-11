package com.daya.shared.taha.domain.repository

import com.daya.shared.taha.domain.model.NewsNet

interface IbroadCastRepository {
    suspend fun broadCastNews(newsNet: NewsNet): Void?
}