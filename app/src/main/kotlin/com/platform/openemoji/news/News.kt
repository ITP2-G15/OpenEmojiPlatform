package com.platform.openemoji.news

import kotlinx.serialization.Serializable

@Serializable
data class News(
    val title: String,
    val url: String,
    val img: String,
    val desc: String,
)
