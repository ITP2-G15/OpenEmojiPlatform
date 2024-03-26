package com.platform.openemoji.events

import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val title: String,
    val date: String,
    val img: String,
    val url: String,
)
