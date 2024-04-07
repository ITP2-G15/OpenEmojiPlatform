package com.platform.openemoji.game

import kotlinx.serialization.Serializable

@Serializable
data class Level(
    val emojiQuestion: String,
    val alternatives: List<String>,
    val correctAlternative: Int,
)
