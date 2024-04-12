package com.platform.openemoji.favorites

import kotlinx.serialization.Serializable

@Serializable
data class Favorite(
    val name: String,
    val emojiCodes: Array<String>,
)
