package com.daya.shared.taha.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Topic(
    val topicId: String,
    val topicName: String,
    val isUserSubscribe: Boolean = false,//TODO ini ter reference di user
    var isUnsubscribeAble: Boolean = false

) : Parcelable

data class TopicNet(
    val topicId : String,
    val topicName : String,
    var isUserSubscribed: Boolean = false,
    var isUnsubscribeAble : Boolean = false
)
