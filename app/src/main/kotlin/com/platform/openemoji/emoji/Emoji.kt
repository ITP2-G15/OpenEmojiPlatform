package com.platform.openemoji.emoji

import kotlinx.serialization.Serializable

@Serializable
data class Emoji(
    val code: String,
    val unicodeCodePoints: String,
    val url: String,
    val name: String,
    val popularity: Int,
    val category: String,
    val description: String,
    val emojiVersion: Float,
    val unicodeVersion: Float?,
)
