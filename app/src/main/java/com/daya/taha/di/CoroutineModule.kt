//package com.daya.taha.di
//
//import com.daya.shared.di.qualifier.DefaultDispatcher
//import com.daya.shared.di.qualifier.IoDispatcher
//import com.daya.shared.di.qualifier.MainDispatcher
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.components.ApplicationComponent
//import kotlinx.coroutines.Dispatchers
//
//@Module
//@InstallIn(ApplicationComponent::class)
//object CoroutineModule {
//
//    @Provides
//    @com.daya.shared.di.qualifier.IoDispatcher
//    fun provideIoDispatcher() = Dispatchers.IO
//
//    @Provides
//    @com.daya.shared.di.qualifier.MainDispatcher
//    fun provideMainDispatcher() = Dispatchers.Main
//
//    @Provides
//    @com.daya.shared.di.qualifier.DefaultDispatcher
//    fun provideDefaultDispatcher() = Dispatchers.Default
//
//}