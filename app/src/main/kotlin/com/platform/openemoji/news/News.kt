package com.platform.openemoji.news

import kotlinx.serialization.Serializable

@Serializable
data class News(
    val name: String,
    val url: String,
    val image: String,
    val description: String,
)
