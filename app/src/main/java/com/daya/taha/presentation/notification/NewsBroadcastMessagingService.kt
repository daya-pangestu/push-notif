package com.daya.taha.presentation.notification


import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber

class NewsBroadcastMessagingService : FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Timber.i(p0)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Timber.i("received remotemessage: $remoteMessage")
    }

    private fun handleNotification(remoteMessage: RemoteMessage) {
        val channelId = "news channel id"
        val channelName = "news"
        //TODO make pending intent to detail and visit link
    }
}