package com.platform.openemoji.emoji

import kotlinx.serialization.Serializable

@Serializable
data class Emoji(
    val id: String,
    val title: String,
    val emojiCode: String,
    val category: String,
    val subCategory: String,
    val description: String,
    val imageUrl: String,
)
