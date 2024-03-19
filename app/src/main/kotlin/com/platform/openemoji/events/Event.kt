package com.platform.openemoji.events

import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val id: String,
    val title: String,
    val date: String,
    val webView: String,
    val url: String,
)
