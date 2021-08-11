package com.daya.shared.taha.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideIntercept(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer AAAA2vQoFlg:APA91bEAEd03iNr1EX2QZ9UvGSnjcG4FBZbNQggrU-3jYDdnBzFFCUuWL_pEnQWDnO0MC8Wfo0JM_R537hmE-zataf6m6XcFzyEmbqcubfdfeiTocAFSTG8rnTEX9rJEtwBgmY2SRDUE").build()
                chain.proceed(request)
            }.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(intercept : OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl("https://iid.googleapis.com/")
            .client(intercept)
            .build()

    @Singleton
    @Provides
    fun provideFirebaseService(retrofit: Retrofit): FirebaseApiService {
        return retrofit.create(FirebaseApiService::class.java)
    }
}

interface FirebaseApiService{
    @GET("iid/info/{token}")
    fun getlistSubscribedTopic(
        @Path("token") token : String,
        @Query("details") details : Boolean = true
    ) : Call<String>
}