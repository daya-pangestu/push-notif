package com.daya.shared.taha.domain.model

import android.os.Parcelable
import com.google.firebase.firestore.ServerTimestamp
import kotlinx.parcelize.Parcelize
import java.util.*

data class NewsNet(
    var senderId: String? = "",
    val title: String  = "",
    val description: String = "",
    val urlAccess: String = "",
    var urlImage: String? = "",
    val status: String = "requested",
    val topics: List<String> = emptyList(), //topics in cloud was string, convert to topicNet if necessary
    @ServerTimestamp
    val broadcastRequested: Date? = null
)

@Parcelize
data class News(
    val senderId: String? = "",
    val title: String  = "",
    val description: String = "",
    val urlAccess: String = "",
    var urlImage: String = "",
    val status: String = "requested",
    val topics: List<Topic> = emptyList(),
    val broadcastRequested: Date? = null
) : Parcelable