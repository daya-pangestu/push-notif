package com.daya.shared.taha.di

import com.daya.shared.taha.data.auth.AuthDataSource
import com.daya.shared.taha.data.auth.AuthRepository
import com.daya.shared.taha.data.auth.FireBaseAuthDataSource
import com.daya.shared.taha.data.topic.FirebaseTopicDataSource
import com.daya.shared.taha.data.topic.TopicRepository
import com.daya.shared.taha.domain.repository.IAuthRepository
import com.daya.shared.taha.domain.repository.ITopicRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedModule {

    @Singleton
    @Provides
    fun provideAuthRepository(authDataSource: FireBaseAuthDataSource): IAuthRepository {
        return AuthRepository(authDataSource)
    }

    @Singleton
    @Provides
    fun provideTopicRepository(topicDataSource: FirebaseTopicDataSource): ITopicRepository {
        return TopicRepository(topicDataSource)
    }

//    @Singleton
//    @Provides
//    fun provide() {
//
//    }
//    @Singleton
//    @Provides
//    fun provide() {
//
//    }
//    @Singleton
//    @Provides
//    fun provide() {
//
//    }
//    @Singleton
//    @Provides
//    fun provide() {
//
//    }
//    @Singleton
//    @Provides
//    fun provide() {
//
//    }


}