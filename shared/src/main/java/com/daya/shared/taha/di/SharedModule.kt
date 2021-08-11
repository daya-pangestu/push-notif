package com.daya.shared.taha.di

import com.daya.shared.taha.data.auth.AuthDataSource
import com.daya.shared.taha.data.auth.AuthRepository
import com.daya.shared.taha.data.auth.FireBaseAuthDataSource
import com.daya.shared.taha.data.topic.FirebaseTopicDataSource
import com.daya.shared.taha.data.topic.TopicDataSource
import com.daya.shared.taha.data.topic.TopicRepository
import com.daya.shared.taha.domain.repository.IAuthRepository
import com.daya.shared.taha.domain.repository.ITopicRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
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
    fun provideAuthRepository(authDataSource: AuthDataSource): IAuthRepository {
        return AuthRepository(authDataSource)
    }

    @Singleton
    @Provides
    fun provideTopicRepository(topicDataSource: TopicDataSource): ITopicRepository {
        return TopicRepository(topicDataSource)
    }

    @Singleton
    @Provides
    fun provideTopicDataSource(firestore: FirebaseFirestore,messaging: FirebaseMessaging, firebaseApiService : FirebaseApiService) : TopicDataSource {
        return FirebaseTopicDataSource(firestore,messaging,firebaseApiService)
    }
    @Singleton
    @Provides
    fun provideAuthDataSource(auth : FirebaseAuth) : AuthDataSource {
        return FireBaseAuthDataSource(auth)
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


}