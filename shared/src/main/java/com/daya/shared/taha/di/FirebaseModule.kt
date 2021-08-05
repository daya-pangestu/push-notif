package com.daya.shared.taha.di

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseApp(@ApplicationContext context : Context): FirebaseApp {
        val options = FirebaseOptions.Builder()
            .setProjectId("function-codelab")
            .setApplicationId("1:940399138392:android:d8579ee022305b09111c2d")
            .setApiKey("AIzaSyC1pcuE668UVJ9zI_bmyGEeaJzXkG8Ivy8")
            .build()

        return Firebase.initialize(context,options,"secondary")
    }

    @Provides
    @Singleton
    fun provideFireStore(firebaseApp: FirebaseApp): FirebaseFirestore {
        return Firebase.firestore(firebaseApp)
    }

    @Provides
    @Singleton
    fun provideFireBaseAuth(firebaseApp: FirebaseApp): FirebaseAuth {
        return Firebase.auth(firebaseApp)
    }
}