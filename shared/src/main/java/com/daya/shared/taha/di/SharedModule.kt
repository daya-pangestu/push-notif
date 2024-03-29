package com.daya.shared.taha.di

import android.content.Context
import com.daya.shared.taha.data.auth.AuthDataSource
import com.daya.shared.taha.data.auth.AuthRepository
import com.daya.shared.taha.data.auth.FireBaseAuthDataSource
import com.daya.shared.taha.data.broadcast.BroadCastDataSource
import com.daya.shared.taha.data.broadcast.BroadCastRepository
import com.daya.shared.taha.data.broadcast.FirebaseBroadCastDataSource
import com.daya.shared.taha.data.topic.FirebaseTopicDataSource
import com.daya.shared.taha.data.topic.TopicDataSource
import com.daya.shared.taha.data.topic.TopicRepository
import com.daya.shared.taha.domain.repository.IAuthRepository
import com.daya.shared.taha.domain.repository.ITopicRepository
import com.daya.shared.taha.domain.repository.IbroadCastRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun provideBroadCastDataSource(fireStore: FirebaseFirestore, imageRef : StorageReference, @ApplicationContext context : Context): BroadCastDataSource {
        return FirebaseBroadCastDataSource(fireStore,imageRef, context)
    }
    @Singleton
    @Provides
    fun provideBroadCastRepository(broadCastDataSource: BroadCastDataSource): IbroadCastRepository {
        return BroadCastRepository(broadCastDataSource)
    }
//    @Singleton
//    @Provides
//    fun provide() {
//
//    }


}