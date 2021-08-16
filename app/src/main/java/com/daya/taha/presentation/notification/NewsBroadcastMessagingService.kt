package com.daya.taha.presentation.notification


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.os.bundleOf
import androidx.navigation.NavDeepLinkBuilder
import com.bumptech.glide.Glide
import com.daya.shared.taha.domain.model.News
import com.daya.shared.taha.domain.model.Topic
import com.daya.taha.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber
/*
known bug : app would not receive notification when in background or opened
* */
class NewsBroadcastMessagingService : FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Timber.i("token: $p0")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Timber.i("remote message \ntitle: ${remoteMessage.notification?.title} \nbody: ${remoteMessage.notification?.body}  \nimageUrl${remoteMessage.notification?.imageUrl} ")
        handleNotification(remoteMessage)
    }

    private fun handleNotification(remoteMessage: RemoteMessage) {
        val channelId = "news channel id"
        val channelName = "news"
        //TODO make pending intent to detail and visit link

        val news = News(
            senderId = remoteMessage.data["senderId"] ?: "",
            title = remoteMessage.notification?.title ?: "",
            description = remoteMessage.notification?.body ?: "",
            urlAccess = remoteMessage.data["urlAccess"] ?: "",
            urlImage = remoteMessage.notification?.imageUrl.toString() ?: "",
            status = "broadcasted",
            topics = sculptingTopic(remoteMessage.data["topics"])
        )

        val intentShare = Intent().let {
            it.action = Intent.ACTION_SEND
            it.putExtra(Intent.EXTRA_TEXT, news.urlAccess)
            it.type = "text/plain"
            Intent.createChooser(it, "share link")
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            PendingIntent.getActivity(applicationContext,1, it ,PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val intentToDetail = NavDeepLinkBuilder(applicationContext)
            .setGraph(R.navigation.nav_graph)
            .setDestination(R.id.detailFragment)
            .setArguments(
                bundleOf("news_arg" to news)
            )
            .createPendingIntent()


        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setContentTitle(news.title)
            .setContentText(news.description)
            .setSmallIcon(R.drawable.logo_ittp)
            .setAutoCancel(true)
            .setGroup(GROUP_KEY_NOTIFICATION)
            .setContentIntent(intentToDetail)
            .addAction(R.drawable.ic_baseline_remove_red_eye_24, "detail", intentToDetail)
            .addAction(R.drawable.ic_baseline_share_24,"share link",intentShare)
            .setPriority(NotificationCompat.PRIORITY_HIGH)//support for API level < 24

        if (remoteMessage.notification?.imageUrl != null && remoteMessage.notification?.imageUrl.toString().isNotEmpty()) {
            notificationBuilder.setStyle(NotificationCompat.BigPictureStyle()
                .bigPicture(
                    Glide.with(this)
                        .asBitmap()
                        .load(remoteMessage.notification?.imageUrl)
                        .submit()
                        .get()
                )
            )
        }

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId,channelId,NotificationManager.IMPORTANCE_HIGH).apply {
                name = channelName
            }
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(0, notificationBuilder.build())
    }

    override fun onDeletedMessages() {
        super.onDeletedMessages()
    }

    private fun sculptingTopic(listString: String?): List<Topic> {
        if (listString == null) {
            return emptyList()
        }
        return listString.split(",")
            .map {
                val text = it.replace("[", "")
                    .replace("]", "")
                Topic(
                    topicName = text,
                    topicId = ""
                )
            }
    }

    companion object{
        private const val GROUP_KEY_NOTIFICATION = "group_key_notification"
    }
}