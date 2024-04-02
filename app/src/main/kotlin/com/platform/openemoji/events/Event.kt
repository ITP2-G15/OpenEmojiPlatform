package com.platform.openemoji.events

import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val name: String,
    val date: String,
    val image: String,
    val url: String,
)
