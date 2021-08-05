package com.daya.taha

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.grpc.android.BuildConfig
import timber.log.Timber

@HiltAndroidApp
class TahaApplication :Application(){

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}