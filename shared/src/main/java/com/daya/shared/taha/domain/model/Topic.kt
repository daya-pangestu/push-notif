package com.daya.shared.taha.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Topic(
    val topicId: String,
    val topicName: String,
    val isUserSubscribe: Boolean = false,//TODO ini ter reference di user
    val isUnsubscribeAble: Boolean = false

) : Parcelable
