package com.daya.shared.taha.domain.model

import com.google.firebase.firestore.ServerTimestamp
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


data class News(
    val senderId: String? = ""
//TODO completing this
)