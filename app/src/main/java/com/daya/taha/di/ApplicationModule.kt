package com.daya.taha.di

import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import android.content.Context
import com.daya.taha.db.TahaDb
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context : Context) = Room.databaseBuilder(
        context,
        TahaDb::class.java,
        "TahaDataBase"
    )
        .build()
}